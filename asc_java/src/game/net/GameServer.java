/**
 * 
 */
package game.net;

import game.GameWindow;
import game.entities.PlayerMP;
import game.net.packets.Packet;
import game.net.packets.Packet00Login;
import game.net.packets.Packet01Disconnect;
import game.net.packets.Packet02Move;
import game.net.packets.Packet.PacketTypes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=l1p21JWa_8s
 *   
 *   Packet sending tutorial taken from https://www.youtube.com/watch?v=7Qcg6Hvx_WU
 *   
 *   Multiplayer visibility tutorial taken from https://www.youtube.com/watch?v=npSs2ROWFTE
 *   
 *   Disconnect packet tutorial taken from https://www.youtube.com/watch?v=pfl8L-_3fQA
 *
 */
public class GameServer extends Thread {

	private DatagramSocket socket;
	private GameWindow gameWindow;
	
	private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	
	public GameServer(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		try {
			this.socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024]; // limit to 1K for now
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try { // receive packet
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
//			String message = new String(packet.getData());
//			System.out.println("CLIENT > [" + packet.getAddress().getHostAddress() + ":" + packet.getPort() + "]" +  message);
//			if(message.trim().equalsIgnoreCase("ping")) {
//				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
//			}
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(data);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Packet00Login) packet).getUserName() + " has connected...");
			PlayerMP player = new PlayerMP(gameWindow.level, 100, 100, ((Packet00Login) packet).getUserName(), address, port);
			this.addConnection(player, (Packet00Login) packet);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Packet01Disconnect) packet).getUserName() + " has left...");
//			PlayerMP player = new PlayerMP(gameWindow.level, 100, 100, ((Packet01Disconnect) packet).getUserName(), address, port);
			this.removeConnection((Packet01Disconnect) packet);
			break;
		case MOVE:
			packet = new Packet02Move(data);
			int moveDir = ((Packet02Move) packet).getMoveDir();
			String dir = "";
			switch (moveDir) {
			default:
			case 0:
				dir = "UP";
				break;
			case 1:
				dir = "DOWN";
				break;
			case 2:
				dir = "LEFT";
				break;
			case 3:
				dir = "RIGHT";
				break;
			}
			System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Packet02Move) packet).getUserName() + " is moving " + dir);
			break;
		}
	}

	public void addConnection(PlayerMP player, Packet00Login packet) {
		boolean alreadyConnected = false;
		for(PlayerMP p: this.connectedPlayers) {
			if(player.getUserName().equalsIgnoreCase(p.getUserName())) { // player already in game
				if(p.ipAddress == null) {
					p.ipAddress = player.ipAddress;
				}
				if(p.port == -1) {
					p.port = player.port;;
				}
				alreadyConnected = true;
			} else { // player NOT in game
//				Packet00Login loginPacket = new Packet00Login(p.getUserName());
				sendData(packet.getData(), p.ipAddress, p.port); // send this Client data to the server?
				packet = new Packet00Login(p.getUserName());
				sendData(packet.getData(), player.ipAddress, player.port); // send packet back to the connected Clients
			}
		}
		if(!alreadyConnected) {
			this.connectedPlayers.add(player);
//			packet.writeData(this);
		}
	}

	public void removeConnection(Packet01Disconnect packet) {
		this.connectedPlayers.remove(getPlayerMPIndex(packet.getUserName()));
		packet.writeData(this);
	}
	
	public PlayerMP getPlayerMP(String userName) {
		for(PlayerMP p: connectedPlayers) {
			if(p.getUserName().equalsIgnoreCase(userName)) {
				return p;
			}
		}
		// throw new Exception();
		return null;
	}
	
	public int getPlayerMPIndex(String userName) {
		int index = 0;
		for(PlayerMP p: connectedPlayers) {
			if(p.getUserName().equalsIgnoreCase(userName)) {
				return index;
			}
			index++;
		}
		// throw new Exception("Player not found!");
		return -1;
	}

	public void sendData(byte[] data, InetAddress ipAddress, int port) {
		if(!gameWindow.isApplet) {
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331); // choose a port above 1024
			try {
				this.socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendDataToAllClients(byte[] data) {
		for(PlayerMP p: connectedPlayers) {
			sendData(data, p.ipAddress, p.port);
		}
	}
	
}
