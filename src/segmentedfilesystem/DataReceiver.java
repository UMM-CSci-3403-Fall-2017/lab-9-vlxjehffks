package segementedfilesystem;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class DataReceiver {

	//create three files for segment data
	SegmentFile file1 = new SegmentFile();
	SegmentFile file2 = new SegmentFile();
	SegmentFile file3 = new SegmentFile();

	//files to place in specific location.
	ArrayList<SegmentFile> dataFiles = new ArrayList<>();
	HashMap<Byte, SegmentFile> dataMap = new HashMap<>();

	int last = 0;

	public void getFiles(){
		dataFiles.add(file1);
		dataFiles.add(file2);
		dataFiles.add(file3);
	}

	//create method to receive data packets 
	public void initiatePackets(DatagramSocket socket) throws IOException{

		getFiles();

		boolean bool = false;
		int lastPacketCounter = 0; //count all packets
		int counter = 0; //count receiving data or packets

		while(true){
			
			byte[] buffer = new byte[1028];
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			socket.receive(dp);

			int condition = fileCondition(dp.getData());
			if(condition == 1){
				firstPacket(dp);
				counter++;
			}else if(condition == 2){
				secondPacket(dp);
				counter++;
			}else if(condition == 3){
				lastPacket(dp);
				counter++;
				lastPacketCounter++;
			}else{
				System.out.println("ERRORS!");
				break;
			}

			if(lastPacketCounter == 3){
				bool = true;
			}

			if(bool){
				if(counter == last){
					break;
				}
			}
		}
		socket.close();

		file1.filePackets();
		file2.filePackets();
		file3.filePackets();
	}

	//public to check package
	public int fileCondition(byte[] data){
		if((data[0] & 1) ==0){
			return 1;
		}else{
			if(((data[0] >> 1) & 1) == 0){
			       return 2;
			}else{
				return 3;
			}
		}
	}

	public void firstPacket(DatagramPacket dp){
		byte fileID = dp.getData()[1];
		if(dataMap.containsKey(fileID)){
			byte[] fileNameData = new byte[dp.getLength() - 2];
			fileNameData = Arrays.copyOfRange(dp.getData(), 2, dp.getLength());
			dataMap.get(fileID).setFileName(new String(fileNameData));
		}else{
			dataMap.put(fileID, dataFiles.get(0));
			dataFiles.remove(0);
			byte[] fileNameData = new byte[dp.getLength() -2];
			fileNameData = Arrays.copyOfRange(dp.getData(), 2, dp.getLength());
			dataMap.get(fileID).setFileName(new String(fileNameData));
		}
	}

	public void secondPacket(DatagramPacket dp){
		byte fileID = dp.getData()[1];

		if(dataMap.containsKey(fileID)){
			dataMap.get(fileID).insert(dp.getData());
		}else{
			dataMap.put(fileID, dataFiles.get(0));
			dataFiles.remove(0);
			dataMap.get(fileID).insert(dp.getData());
		}
	}

	public void lastPacket(DatagramPacket dp){
		byte fileID = dp.getData()[1];

		if(dataMap.containsKey(fileID)){
			int length = dp.getLength();
			byte[] data = new byte[length];
			data = Arrays.copyOfRange(dp.getData(), 0, length);
			dataMap.get(fileID).insert(data);
			int val = ((dp.getData()[2] & 0xff) << 8) | (dp.getData()[3] & 0xff);
			last += (val + 2);
		}else{
			dataMap.put(fileID, dataFiles.get(0));
			dataFiles.remove(0);
			int length = dp.getLength();
			byte[] data = new byte[length];
			data = Arrays.copyOfRange(dp.getData(), 0, length);
			dataMap.get(fileID).insert(data);
			int val = ((dp.getData()[2] & 0xff) << 8) | (dp.getData()[3] & 0xff);
			last += (val + 2);
		}
	}
}


