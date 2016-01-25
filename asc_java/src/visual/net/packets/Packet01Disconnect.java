/**
 * 
 */
package visual.net.packets;

import visual.net.GameClient;
import visual.net.GameServer;

/**
 * @author Joseph DeLong
 *   Tutorial by vanZeben taken from https://www.youtube.com/watch?v=pfl8L-_3fQA
 *
 */
public class Packet01Disconnect extends Packet {

	private String userName;
	
	public Packet01Disconnect(byte[] data) {
		super(01);
		this.userName =  readData(data);
	}
	
	public Packet01Disconnect(String userName) {
		super(01);
		this.userName =  userName;
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
		return ("01" + this.userName).getBytes();
	}
	
	public String getUserName() {
		return userName;
	}

}
