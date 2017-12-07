package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.*;


public class Main {
    public static void main(String[] args) thrwos IOException {
	    int port = 6014;
	    byte[] buf = new byte[1024];
	    InetAddress address = InetAddress.getByName("localhost");
	    DatagramSocket socket = new DatagramSocket(6014);
	    DatagramPacket packet = new DataPacket(buf, 0, address, 6014);
	    socket.send(packet);


	    int f1Packet = Integer.MAX_VALUE;
	    int f2Packet = Integer.MAX_VALUE;
	    int f3Packet = Integer.MAX_VALUE;

	    ArrayList<Strin> fineNameArr = new ArrayList<String>();

	    ArrayList<gramFile> f1 = new ArrayList<gram>();
	    ArrayList<gramFile> f2 = new ArrayList<gram>();
	    ArrayList<gramFile> f3 = new ArrayList<gram>();
	    byte[] buffer = new byte[1028];

	    while(!f1.done || !f2.done || !f3.done) {
		    DatagramPacket receiver = new DatagramPakcet(buffer, buffer.length);
		    socket.receive(receiver);
//		    byte[]  = Arrays.copyOfRange(receiver.getData


		    buffer = receiver.getData();
		    byte[] adding = buffer.clone();

		    int condition = buffer[0];
		    int fileID = Math.abs(buffer[1]);

		    if(fileNumber(fileID) == 0) {
			    f1.add(makegramFile(condition, adding, receiver));
			    if(condition == 3) {
				    f1Packet = makePacketNumber(buffer) + 2;
			    }

			    f1Pakcet = m

        
    }

}


