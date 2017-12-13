package segmentedfilesystems;


import java.io.*;
import java.net.*;

public class Main2{
	public static void main(String[] args){
		int port = 6014;
	try{
		InetAddress = address = InetAddress.getByName("localhost");
		DatagramSocket socket = new DatagramSocket();
	
		while (true) {
			byte[] one = new byte[1];
			DatagramPacket request = new DatagramPacket(one, 1, address, port);
			socket.send(request);

			byte[] buffer = new byte[1028];
			DatagramPacket response = new DatagramPacket(buffer, buffer.length);
			socket.receive(response);

			
			String quote = new String(buffer, 0, response.getLength());
			System.out.println(quote);
			System.out.println();

			Thread.sleep(10000);
		}}
		catch (SocketTimeoutException ex) {
			System.out.println("Timeout error: " + ex.getMesseage());
			ex.printStackTrace();
		}
		catch (IOException ex) {
			System.out.println("Client error: " + ex.getMesseage());
			ex.printStackTrace();
		}
		catch (InterruptedException ex){
			ex.printStackTrace();
		}
	
	}

		}
		
