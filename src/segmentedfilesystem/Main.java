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
	    socket.receive(packet);

	    ArrayList<gram> f1 = new ArrayList<gram>();
	    ArrayList<gram> f2 = new ArrayList<gram>();
	    ArrayList<gram> f3 = new ArrayList<gram>();



        
    }

}
