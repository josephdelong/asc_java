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
public class Packet00Login extends Packet {

	private String userName;
	
	public Packet00Login(byte[] data) {
		super(00);
		this.userName =  readData(data);
	}
	
	public Packet00Login(String userName) {
		super(00);
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
		return ("00" + this.userName).getBytes();
	}
	
	public String getUserName() {
		return userName;
	}

}
