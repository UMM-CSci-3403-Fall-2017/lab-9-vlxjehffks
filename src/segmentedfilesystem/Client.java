package segmentedfilesystem;

import java.io.*;
import java.net.*;


public class Client {
	public static void main(String[] args) {
		DatagramSocket socket;
		DatagramPacket packet;
		String text = "hello socketProgramming";
		byte[] buf = text.getBytes();
		String serverIp = "146.57.33.55";
		int port = 6014;

		try{
			socket = new DatagramSocket();
			packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(serverIp), port);

			socket.send(packet);

			System.out.println("send Msg : "+text);

			socket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

