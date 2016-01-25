/**
 * 
 */
package visual.net.packets;

import visual.net.GameClient;
import visual.net.GameServer;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=7Qcg6Hvx_WU
 *
 */
public class Packet02Move extends Packet {

	private String userName;
	private int moveDir;
	
	public Packet02Move(byte[] data) {
		super(02);
		this.userName =  readData(data);
	}
	
	public Packet02Move(String userName, int moveDir) {
		super(02);
		this.userName =  userName;
		this.moveDir = moveDir;
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData() {
		return ("02" + this.userName + this.moveDir).getBytes();
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getMoveDir() {
		return moveDir;
	}

}
