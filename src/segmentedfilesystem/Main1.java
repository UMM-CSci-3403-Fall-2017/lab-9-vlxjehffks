package segmentedfilesystem;

import java.io.*;
import java.net.*;

public class Main1 {
	public static void main(String[] args) throws IOException {
		int port = 6014;
		InetAddress address = InetAddress.getByName("localhost");
		DatagramSocket socket = new DatagramSocket();
		byte[]buf = new byte[1028];
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		socket.send(buf);

		SenderPacket client = new SenderPakcet();
		client.startReceivingPakcet(socket);
	}
}
