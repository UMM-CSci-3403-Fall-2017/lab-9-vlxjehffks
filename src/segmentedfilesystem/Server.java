package segmentedfilesystem;

import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args){
		DatagramSocket socket;
		DatagramPacket packet;
		int port = 6014;
		byte[] buf = new byte[1024];

		try{
			socket = new DatagramSocket(port);
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			String msg = new String(packet.getData());
			System.out.println("received Msg: " +msg);
			
			socket.close();
		} catch(SocketException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
