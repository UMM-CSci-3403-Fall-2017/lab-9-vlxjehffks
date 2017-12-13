
import java.io.*;
import java.net.*;
import java.util.*;

public class Main3 {
	public static void main(String[] args) {
		int port = 6014;
		byte [] buffer = new byte[1028];
		String filename = "small.txt";

		long fileSize;
		long totalReadBytes=0;

		
		try {
			int nReadSize = 0;
			System.out.println("Waiting . . . .");

			DatagramSocket ds = new DatagramSocket(port);
			FileOutputStream fos = null;
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			String str = new String(dp.getData()).trim();

			if (str.equals("small.txt")){
				System.out.println(str);
				dp = new DatagramPAcket(buffer, buffer.length);
				ds.receive(dp);
				str = new String(dp.getData()).trim();
				fileSize = Long.parseLong(str);
				double startTime = System.currentTimeMillis();
				while (true) {
					ds.receive(dp);
						str = new String(dp.getData()).trim();
						nReadSize = dp.getLength();
					fos.write(dp.getData(), 0 nReadSize);
					totalReadBytes+=nReadSize;
					System.out.println("In progress: " + totalReadBytes + "/" 
							+ fileSize + "Byte(s) ("
							+ (totalReadBytes * 100 / fileSize) + "%)");
					if(totalReadBytes>=fileSize)
						break;
				}
				System.out.println("File transfer completed");
				Thread.sleep(10000);
				fos.close();
				ds.close();
			}
			else {
				System.out.println("Start Error");
				fos.close();
				ds.close();
			}
		}
		catch (Exception e) {}
		System.out.println("Process Close");
	}
}
