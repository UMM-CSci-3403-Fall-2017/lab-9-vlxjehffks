package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    public static void main(String[] args) thrwos IOException {
	    int port = 6014;
	    byte[] buffer = new byte[1024];
	    InetAddress address = InetAddress.getByName("localhost");
	    DatagramSocket socket = new DatagramSocket(6014);
	    DatagramPacket packet = new DataPacket(buferf, buffer.length, address, 6014);
	    socket.send(packet);

	    //create ArrayList to store data in different files.
	    
	    int f1Packet = Integer.MAX_VALUE;
	    int f2Packet = Integer.MAX_VALUE;
	    int f3Packet = Integer.MAX_VALUE;

	    ArrayList<Datafile> F1 = new ArrayList<Datafile>();
	    ArrayList<Datafile> F2 = new ArrayList<Datafile>();
	    ArrayList<Datafile> F3 = new ArrayList<Datafile>();
	  //  byte[] buffer = new byte[1028];

	    while(f1Packet != F1.size() || f2Packet != F2.size() || f3Packet != F3.size()) {
		    byte[] buffed = new byte[1028];
		    DatagramPacket receiver = new DatagramPakcet(buffer, 0, buffed.length);
		    socket.receive(receiver);
		    buffed = receiver.getData();
		    byte[] adding = buffed.clone();
		    int condition = buffer[0];
		    int fileID = Math.abs(buffer[1]);

		    if(fileNumber(fileID) == 0) {
			    F1.add(makeDatafile(condition, adding, receiver));
			    if(condition == 3) {
				    f1Packet = makePacketNumber(buffer) + 2;
			    }

		    } else if (fileNumber(fileID) == 1) {
			    F1.add(makeDatafile(condition, adding, receiver));

			    if(condition == 3) {
				    f2Packet = makePakcetNumber(buffed) + 2;
			    }

		    } else if (fileNumber(fileID) == 2) {
			    F2.add(makeDatafile(condition, adding, receiver));
			    
			    if(condition == 3) {
				    f2Packet = makePacketNumber(buffed) +2;
			    }
		    }
	    }

	    Collections.sort(F1);
	    Collections.sort(F2);
	    Collections.sort(F3);

	    


