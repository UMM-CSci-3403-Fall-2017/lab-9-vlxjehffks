package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.*;

public class SenderPacket{
	File file1 = new File();
	File file2 = new File();
	File file3 = new File();

	ArrayList<File> threeFiles = new ArrayList<>();
	HashMap<Byte, File> fileList = new HashMap<>();
	int zero = 0;

	public void receivingFile(){
		threeFiles.add(file1);
		threeFiles.add(file2);
		threeFiles.add(file3);
	}
	public void ReceingFiles(DatagramSocket socket) throws IOException{
		receivingFile();
		boolean header = false;
		int lastPakcetCounter = 0;
		int counter = 0;

		while(true){
			byte[] buffer = new byte[1028];
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			socket.receive(dp);

			int condition = FileCondition(dp.getData());

			if(condition == 1){
				firstHeaderPakcet(dp);
				counter++;
			}else if(condition == 2) {
				secondHeaderPakcet(dp);
				counter++;
			}else if(condition == 3) {
				lastHeaderPacket(dp);
				counter++;
				lastPacketCounter++;
			}else{
				System.out.println("Errors!");
				break;
			}
			if(lastPacketCounter == 3){
				header = ture;
			}
			if(header){
				if(counter == zero) {
					break;
				}
			}
		}
		socket.close();

		file1.headerPackets();
		file2.headerPackets();
		file3.headerPackets();
	}

	public int FileCondition(byte[] data){
		if((data[0] & 1) == 0) {
			return 1;
		}else{
			if(((data[0] >> 1) & 1) == 0){
				return 2;
			}else{
				return 3;
			}
		}
	}

	public void firstHeaderPacket(DatagramPacket dp){
		byte fileID = dp.getData()[1];

		if(fileList.containskey(fileID)){
			byte[] fileNameData = new byte[dp.getLength() -2];
			fileNameData = Arrays.copyOfRange(dp.getData(), 2, dp.getLength());
			fileList.get(fileID).setFileName(new String(fileNameData));
		}else {
			fileList.put(fileID, threeFiles.get(0));
			threeFiles.remove(0);
			byte[] fileNameData = new byte[dp.getLength() -2];
			fileNameData = Arrays.copyOfRange(dp.getData(), 2, dp.getLength());
			fileList.get(fileID).setFileName(new String(fileNameData));
		}
	}

	public void secondHeaderPacket(DatagramPacket dp){
		byte fileID = dp.getData()[1];
		if(fileList.containsKey(fileID)){
			fileList.get(fileID).insert(dp.getData());
		}else {
			fileList.put(fileID, threeFiles.get(0));
			threeFiles.remove(0);
			fileList.get(fileID).insert(dp.getData());
		}
	}

	public void lastHeaderPacket(DatagramPacket dp){
		byte fileID = dp.getData()[1];
		if(fileList.containsKey(fileID)){
			int length = dp.getLength();
			byte[] data = new byte[length];
			data = Arrays.copyOfRange(dp.getData(), 0, length);
			fileList.get(fileID).insert(data);
			int val = ((dp.getData()[2] & 0xff) << 8) | (dp.getData()[3] & 0xff);
			zero += (val + 2);
		}else{
			fileList.put(fileID, threeFiles.get(0));
			threeFiles.remove(0);
			int length = dp.getLength();
			byte[] data = new byte[length];
			data = Arrays.copyOfRange(dp.getData(), 0, length);
			threeFiles.get(fileID).insert(data);
			int val = ((dp.getData()[2] & 0xff) << 8) | (dp.getData()[3] & 0xff);
			zero += (val +2);
		}
	}
}

