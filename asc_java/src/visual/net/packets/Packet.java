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
public abstract class Packet {

	//enums are constructed on-the-fly
	public static enum PacketTypes {
		INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02);
		
		private int packetId;
		
		private PacketTypes(int packetId) {
			this.packetId = packetId;
		}
		
		public int getId() {
			return packetId;
		}
	}
	
	public byte packetId;
	
	public Packet(int packetId) {
		this.packetId = (byte) packetId;
	}
	
	// send data from Server to this particular Client
	public abstract void writeData(GameClient client);
	
	// send data from Server to ALL Clients of the Server
	public abstract void writeData(GameServer server);
	
	public String readData(byte[] data) {
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public abstract byte[] getData();
	
	public static PacketTypes lookupPacket(String packetId) {
		try {
			return lookupPacket(Integer.parseInt(packetId));
		} catch (NumberFormatException e) {
			return PacketTypes.INVALID;
		}
	}
	
	public static PacketTypes lookupPacket(int id) {
		for(PacketTypes p: PacketTypes.values()) {
			if(p.getId() == id) {
				return p;
			}
		}
		return PacketTypes.INVALID;
	}
	
}
