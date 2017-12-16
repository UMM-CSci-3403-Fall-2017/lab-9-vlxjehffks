package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.*;

public class Mainp {
	public static void main(String[] args){
		try{
			InetAddress ia = InetAddress.getByName("heartofgold.morris.umn.edu");
			int port = 6014;
			byte[] buffer = new byte[1028];
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, port);
			DatagramSocket ds = new DatagramSocket(port);
			ds.send(dp);
			while(true){
				DatagramPacket dpReceived = new DatagramPacket(buffer, buffer.length);
				ds.receive(dpReceived);
			}
		}catch(IOException e){
				e.printStackTrace();
		}
	}
}
//		
//		private boolean isHeader(DatagramPakcet dp){
//			boolean header;

