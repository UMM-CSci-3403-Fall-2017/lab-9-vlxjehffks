package segmentedfilesystem;

import java.io.*;
import java.net.*;

public class FileServer extends Thread {
	private ServerSocket ss;

	public FileServer(int port) {
		try {
			ss = new ServerSocket(port);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				Socket clientSock = ss.accept();
				saveFile(clientSock);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveFile(Socket clientSock) throws IOException {
		 DataInputStream dis = new DataInputStream(clientSock.getInputStream());
		 FileOutputStream fos = new FileOutputStream("small.txt");
		 byte[] buffer = new byte[1028];

		 int filesize = 15123;
		 int read = 0;
		 int totalRead = 0;
		 int remaining = filesize;
		 while((read=dis.read(buffer, 0, Math.min(buffer.length, remaining))) >0) {
			 totalRead += read;
			 remaining -= read;
			 System.out.println("read " + totalRead + " bytes.");
			 fos.write(buffer, 0, read);
		 }
		 fos.close();
		 dis.close();
	}
	public static void main(String[] args) {
		FileServer fs = new FileServer(6014);
		fs.start();
	}
}
