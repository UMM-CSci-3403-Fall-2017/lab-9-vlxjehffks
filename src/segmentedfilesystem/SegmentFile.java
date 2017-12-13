package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SegmentFile {
	ArrayList<byte[]> packit = new ArrayList<>();
	String packitName = "";


	// insert data into array
	public void insert(byte[] data){
		packit.add(data);
	}

	public void insertPackitName(String packitName){
		this.packitName = packitName;
	}

	public void filePackets() throws IOException{
		byte[][] packetArray = new byte[file.size()][];
		for(int i = 0; i < packetArray.length; i++){
			packetArray[i] = file.get(i);
		}
		Arrays.sort(packetArray, new Sorting());
		FileOutputStream Fos = new FileOutputStream("./" + packitName);

		for(int i = 0; i < packetArray.length; i++){
			Fos.write(packetArray[i], 4, packetArray[i].length - 4);
		}
		Fos.flush();
		Fos.close();
	}

	public static class Sorting implements Comparator<byte[]>{
		@Override
		public int compare (byte[] a1, byte[] a2){
			int integer1 = getPacketNumber(a1);
			int integer2 = getPacketNumber(a2);
			int result = 0;
			if(integer1 < integer2) {
				result = -1;
			}else if (integer1 > integer2) {
				result = 1;
			}
			return result;
		}

		public int getPacketNumber(byte[] data){
			int integer = ((data[2] & 0xff) << 8) | (data[3] & 0xff);
			return integer;
		}
	}
}
