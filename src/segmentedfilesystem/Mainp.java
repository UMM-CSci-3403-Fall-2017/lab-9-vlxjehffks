package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.*;

public class Mainp {
	public static void main(String[] args) throws IOException{
		String fileName = "";
		try{
			ArrayList<byte[]> Header = new ArrayList<byte[]>();
			ArrayList<byte[]> footer = new ArrayList<byte[]>();
			ArrayList<byte[]> dataObject = new ArrayList<byte[]>();

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

	writingFile(Header);
	writingFile(footer);
	writingFile(dataObject);

	
	private static byte getStatus(DatagramPacket dpReceived){
		byte[] status;
		status = dpReceived.getData();
		return status[0];
		}

	//make them get FileID so they can get sort the write files.
	private static byte setFileID(DatagramPacket dpReceived){
		byte[] fileID;
		setFileeID = dpReceived.setFileID();
		return fileID[0];
	}
	private static byte  setFileName(DatagramPacket dpReceived ){
		byte[] fileName;
		fileName = dpReceived.setFileName();
		return fileName[0];
	}
	private static byte getDataObject(DatagramPakcet dpReceived){
		byte[] dataObject;
		dataObject = dpReceived.getDataObject();
		return dataObject[0};
	}
	
	
	private static boolean statusByte(DatagramPacket dpReceived){
		if(status % 2 == 0){
			return heather;
		}else(status % 2 ==1){
			return footer;
		}
	}

	private static int arraysize(ArrayList<byte[]> sizes, byfeSetFileID) {
		int 


	private static void writingFiles(ArrayList<byte[]> arr, String FileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		byte[] fileObject;
		for(int i = 0; i < arr.size(); i++){
			fileObject = arr.get(i);
			for(int x = 4; x < byteh.length ; x++){
				if((int) fileObject[x] == 0){
					for(int y = 0; y < fileObject.length; y++){
				fos.write(fileObject(i)[x]);
			}
		}
		fos.flush();
		fos.close();
	}


	
	
	
	
		//Compare to sort byte arrays
    public static class compareMachine implements Comparator<byte[]> {
            @Override
            public int compare(byte[] p1, byte[] p2) {
                int x = ((p1[2] & 0xff) << 8) | (p1[3] & 0xff);
                int y = ((p2[2] & 0xff) << 8) | (p2[3] & 0xff);
                int result = 0;
                if(x < y) {
                    result = -1;
                    }
                else if(x > y) {
                    result = 1;
                    }
                return result;
                }
            }

    public static int f1Packet = Integer.MAX_VALUE;
    public static int f2Packet = Integer.MAX_VALUE;
    public static int f3Packet = Integer.MAX_VALUE;

	//private static boolean test(int a, int b){
		//return (a%2 == 0);
		//return (a<b);
		//if(a < b){
		//	return true;
		//} else {
		//	return false;
		//}
	}
}

