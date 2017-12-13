package segmentedfilesystem;

import java.io.*;
import java.util.*;

public class File {

	ArrayList<byte[]> file = new ArrayList<>();

	String Name = "";

	public void insert(byte[] data){
		file.add(data);
	}

	public void FileNames(String Name){
		this.Name = Name;
	}

	public void FileNumber() throws IOException{

		byte[][] listFile = new byte[file.size()][];
		for( int i = 0; i <listFile.length; i++){
			listFile[i] = file.get(i);
		}
		Arrays.sort(listFile, new sortingFile());
		FileOutputStream File = new FileOutputStream("./" + Name);

		for(int i = 0; i < listFile.length; i++){
			File.write(listFile[i], 4, listFile[i].length - 4);
		}

		File.flush();
		File.close();
	}

	public static class sortingFile implements Comparator<byte[]>{
		@Override
		public int compare (byte[] b1, byte[] b2){
			int number1 = getPacketNumber(b1);
			int number2 = getPacketNumber(b2);

		//	int filenumber2 = getPacketNumber(b2);
			int result = 0;
			if(number1 < number2){
				result = -1;
			}else if (number1 > number2){
				result = 1;
			}
			return result;
		}

		public void getPacketNumber(byte[] data){
			int number = ((data[2] & 0xff) << 8) | (data[3] & 0xff);
					return number;
		}
	}
}
