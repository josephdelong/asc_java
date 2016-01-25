/**
 * 
 */
package visual.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import visual.GameWindow;
import visual.entities.PlayerMP;
import visual.net.packets.Packet;
import visual.net.packets.Packet00Login;
import visual.net.packets.Packet01Disconnect;
import visual.net.packets.Packet.PacketTypes;
import visual.net.packets.Packet02Move;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=l1p21JWa_8s
 *
 */
public class GameClient extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private GameWindow gameWindow;
	
	public GameClient(GameWindow gameWindow, String ipAddress) {
		this.gameWindow = gameWindow;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
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
//			System.out.println("SERVER > " +  message);
			
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
			System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Packet00Login) packet).getUserName() + " has joined the game...");
			PlayerMP player = new PlayerMP(gameWindow.level, 100, 100, ((Packet00Login) packet).getUserName(), address, port);
			gameWindow.level.addEntity(player);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Packet01Disconnect) packet).getUserName() + " has left the world...");
			gameWindow.level.removePlayerMP(((Packet01Disconnect)packet).getUserName());
			break;
		case MOVE:
			packet = new Packet02Move(data);
//			int moveDir = ((Packet02Move) packet).getMoveDir();
//			String dir = "";
//			switch (moveDir) {
//			default:
//			case 0:
//				dir = "UP";
//				break;
//			case 1:
//				dir = "DOWN";
//				break;
//			case 2:
//				dir = "LEFT";
//				break;
//			case 3:
//				dir = "RIGHT";
//				break;
//			}
//			System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Packet02Move) packet).getUserName() + " is moving " + dir);
			break;
		}
	}
	
	public void sendData(byte[] data) {
		if(!gameWindow.isApplet) {
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331); // choose a port above 1024
			try {
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
