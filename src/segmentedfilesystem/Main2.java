package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.*;
import java.math.*;

public class Main2 {
    private static boolean f1Done = false;
    private static boolean f2Done = false;
    private static boolean f3Done = false;
    public static byte[] header;
    public static String fileName;
    public static byte[] packet;
    public static ArrayList<byte[]> allFiles;
    public static int counter = 0;
    
    //set file name; using for header packet                               
    public static void setFileName(){
        String noname = "";
        for(int i = 2; i < header.length; i++){
            if(header[i] == 0){
                break;
                }
            noname = noname + (char) header[i];
            }
        fileName = noname;
        }
    
    //Compare to sort byte arrays
    public static class compareMachine implements Comparator<byte[]> {
            @Override
            public int compare(byte[] p1, byte[] p2) {
                int sum = ((p1[2] & 0xff) << 8) | (p1[3] & 0xff);
                int y = ((p2[2] & 0xff) << 8) | (p2[3] & 0xff);
                int result = 0;
                if(sum < y) {
                    result = -1;
                    }
                else if(sum < y) {
                    result = 1;
                    }
                return result;
                }
            }

    public static int f1Packet = Integer.MAX_VALUE;
    public static int f2Packet = Integer.MAX_VALUE;
    public static int f3Packet = Integer.MAX_VALUE;
    
    //complete checker for status of packets
    public static void completeChecker(ArrayList<byte[]> fi1, ArrayList<byte[]> fi2, ArrayList<byte[]> fi3){
        if(fi1.size() == f1Packet+2) {
                f1Done = true;
                }
        if(fi2.size() == f2Packet+2) {
                f2Done = true;
                }
        if(fi3.size() == f3Packet+2) {
                f3Done = true;
                }
            }
    //add method;from three packets into one arrayList
    public static void addData(ArrayList<byte[]> fi1, ArrayList<byte[]> fi2, ArrayList<byte[]> fi3, byte[] b) {
        byte[] c1 = null;
        byte[] c2 = null;
        byte[] c3 = null;

        if(fi1.isEmpty() || c1[1] == b[1]){
            fi1.add(b);
            if(b[0]%2 == 1){
                if((b[0]%4)==3){
                    int sum = ((Math.abs(b[2]<<8))+((int) 3) & 0xff);
                    f1Packet = sum;
                    }
                }
            }
        else if(fi2.isEmpty() || c2[1] == b[1]){
            fi2.add(b);
            if(b[0]%2 == 1){
                if((b[0]%4) == 3) {
                    int sum = ((Math.abs(b[2]<<8))+((int) 3) & 0xff);
		    f2Packet = sum;
                    }
                }
            }
        else if(fi3.isEmpty() || c3[1] == b[1]){
            fi3.add(b);
            if(b[0]%2 == 1){
                if((b[0]%4) == 3) {
                    int sum = ((Math.abs(b[2]<<8))+((int) 3) & 0xff);
		    //setFileName();
		    f3Packet = sum;
                    }
                }
            }
        completeChecker(fi1, fi2, fi3);
        }

    //writing method for sorted array of bytes
     public static void writeFile(ArrayList<byte[]> arr) throws IOException{
       File file = new File(fileName);
       FileOutputStream fos = new FileOutputStream(file);
       int cC;
       Collections.sort(arr, new compareMachine());
       byte[] byteh = arr.get(0);

       //System.out.println(file);
 
       for(int i = 0; i < arr.size(); i++){
         byteh = arr.get(i);
	 if(byteh[0]%2 == 0){
		 arr.remove(i);
		 break;
	 }
       }
       
       for(int i = 2; i < byteh.length; i++){
	       cC = byteh[i];
	       if(cC != 0){
		       fileName += new Character((char)cC).toString();
	       }
       }
       for(int i = 0; i < arr.size(); i++){
	       byteh = arr.get(i);
	       //counter++;
	       for(int d = 4; d < byteh.length; d++){
		       if((int) byteh[d] != 0){
			       for(int y = 0; y < counter; y++){
				       fos.write((byte) 0);
			       }
			       counter = 0;
			       fos.write(byteh[d]);
		       }else{
			       counter++;
		       }
	       }
       }
       fos.close();
     }


/**     System.out.println(byteh[i]);
       System.out.println("im here" + i);
       
       //Arrays.sort(arr, new compareMachine());
    
       for(int x = 4; x < byteh.length ; x++){
        
	       System.out.println("gets here");
	       if((int) byteh[x] == 0){
         
	       System.out.println("adsfaf");
	       	 for(int y=0;y< byteh.length;y++){
             fos.write(byteh[y]);
	     System.out.println("im here " + y );
           }
           counter = 0;

         fos.write(byteh[x]);
       }
       else{
         counter++;
       }
       
       //fos.flush();
       //fos.close();
     }
     }
     }
     */
     //main method
   public static void main(String[] args) throws IOException {
     try{
        int port = 6014;
        byte[] emptyb = new byte[1];
        byte[] buffer = new byte[1028];


        ArrayList<byte[]> f1 = new ArrayList<byte[]>();
        ArrayList<byte[]> f2 = new ArrayList<byte[]>();
        ArrayList<byte[]> f3 = new ArrayList<byte[]>();

        DatagramSocket ds = new DatagramSocket(port);
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket dp = new DatagramPacket(emptyb, 0, address, port);
        ds.send(dp);
    
        while(true){
            //DatagramPacket dpReceived = new DatagramPacket(buffer, buffer.length);
	    byte[] receivedStructure = new byte[32768];
	    DatagramPacket dpReceived = new DatagramPacket(buffer, buffer.length);
            ds.receive(dpReceived);
            //byte[] receivedStructure = new byte[32768];
            receivedStructure = dpReceived.getData();

            addData(f1, f2, f3, receivedStructure);
   

            if(!f1Done || !f2Done || !f3Done){
                Collections.sort(f1, new compareMachine());
                Collections.sort(f2, new compareMachine());
                Collections.sort(f3, new compareMachine());
                  
                writeFile(f1);
                writeFile(f2);
                writeFile(f3);

                

                break;
                }
            }ds.close();
            

     }
     catch(IOException e){
       e.printStackTrace();
     }
            }
   
        }



