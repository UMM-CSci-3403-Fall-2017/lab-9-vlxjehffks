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
				System.out.println(getStatus(dpReceived));
				
				catch(IOException e){
					e.printStackTrace();
		}
	}
	
	private boolean isHeader(DatagramPacket dp){
		boolean header;
		status = dp.getStatus();
		return status;
	}
	/ /make getStatus to 
	private static byte getStatus(DatagramPacket dpReceived){
		byte[] status;
		status = dpReceived.getData();
		return status[0];
		//System.out.println(new String(status));

	}
	//make them get FileID so they can get sort the write files.
	private static byte setFileID(DatagramPacket dpReceived){
		byte[] fileID;
		fileID = dpReceived.setFileID();
		return fileID[0];
	}
	private static byte  setFileName(DatagramPacket dpReceived ){
		byte[] fileName;
		fileName = dpReceived.setFileName();
		return fileName[0];
	}
	private static byte getDataObject(DatagramPakcet dpreceived){
		byte[] dataObject;
		dataObject = dpReceived.getDataObject();
		return dataObject[0];
	}

	private static void writingFiles(ArrayList<byte[]> ){

	
	}

	private static boolean test(int a, int b){
		return (a%2 == 0);
		//return (a<b);
		//if(a < b){
		//	return true;
		//} else {
		//	return false;
		//}
	}
}
