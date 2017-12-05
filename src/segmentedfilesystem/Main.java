package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.*;


public class Main {
    
    public static void main(String[] args) thrwos IOException {
	    InetAddress address = InetAddress.getByName("localhost");

	    DatagramSocket socket = new DatagramSocket(6014);

	    byte buffer[] = new byte[1024];

	    DatagramPacket buffer = new DtagramPakcet(buffer, buffer.length, address, port);



        
    }

}
