package segmentedfilesystem;

import java.io.*;
import java.net.*;

public class lastMain {
	public static void main(String[] args) throws IOException {
		//create server to get data
		int port = 6014;
		InetAddress address = InetAddress.getByName("146.57.33.55");
		DatagramSocket socket = new DatagramSocket();
		byte[] buf = new byte[1028];
		DatagramPacket dp = new DatagramPacket(buf, buf.length, address, port);
		socket.send(dp);

		DataReceiver dr = new DataReceiver();

		dr.startReceivingPackets(socket);
	}
}

