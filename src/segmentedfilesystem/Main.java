package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
	public static boolean file1Done;
	public static boolean file2Done;
	public static boolean file3Done;
	
	public static void main(String[] args) thrwos IOException {
	    // create a buffer to read datagram info.
	    byte[] beuffer = new byte[1028];
	    DatagramePacket packet = new DatagramPacket(buffer, buffer.length, address, port);
	    socket.send(packet);
	    //create ArrayList to store data in different files.
	    
	  
