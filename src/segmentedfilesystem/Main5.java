package segmentedfilesystem;

import java.io.*;
import java.net.*;
import java.util.*;
import java.math.*;

public class Main5 {
    private static boolean f1Done = false;
    private static boolean f2Done = false;
    private static boolean f3Done = false;
    public static byte[] header;
    public static String fileName;
    public static byte[] packet;
    public static ArrayList<byte[]> allFiles;
    public static int counter = 0;
    
                                    
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

    public static class compareMachine implements Comparator<byte[]> {
            @Override
            public int compare(byte[] p1, byte[] p2) {
                int x = ((p1[2] & 0xff) << 8) | (p1[3] & 0xff);
                int y = ((p2[2] & 0xff) << 8) | (p2[3] & 0xff);
                int result = 0;
                if(x < y) {
                    result = -1;
                    }
                else if(x > y) {
                    result = 1;
                    }
                return result;
                }
            }

    public static int f1Packet = Integer.MAX_VALUE;
    public static int f2Packet = Integer.MAX_VALUE;
    public static int f3Packet = Integer.MAX_VALUE;
    
    public static void completeChecker(ArrayList<byte[]> f1, ArrayList<byte[]> f2, ArrayList<byte[]> f3){
        if(f1.size() == f1Packet+2) {
                f1Done = true;
                }
        if(f2.size() == f2Packet+2) {
                f2Done = true;
                }
        if(f3.size() == f3Packet+2) {
                f3Done = true;
                }
            }
    public static void addData(ArrayList<byte[]> f1, ArrayList<byte[]> f2, ArrayList<byte[]> f3, byte[] b) {
        byte[] c1 = null;
        byte[] c2 = null;
        byte[] c3 = null;

        if(f1.isEmpty() || c1[1] == b[1]){
            f1.add(b);
            if(b[0]%2 == 1){
                if((b[0]%4)==3){
                    f1Packet = ((Math.abs(b[2]<<8))+((int) 3) & 0xff);
                    setFileName();
                    }
                }
            }
        else if(f2.isEmpty() || c2[1] == b[1]){
            f2.add(b);
            if(b[0]%2 == 1){
                if((b[0]%4) == 3) {
                    f2Packet = ((Math.abs(b[2]<<8))+((int) 3) & 0xff);
                    }
                }
            }
        else if(f3.isEmpty() || c3[1] == b[1]){
            f3.add(b);
            if(b[0]%2 == 1){
                if((b[0]%4) == 3) {
                    f3Packet = ((Math.abs(b[2]<<8))+((int) 3) & 0xff);
                    }
                }
            }
        completeChecker(f1, f2, f3);
        }

     public static void writeFile(ArrayList<byte[]> arr) throws IOException{
       File file = new File(fileName);
       FileOutputStream fos = new FileOutputStream(file);
       byte[] byteh;
 
       for(int i = 0; i < arr.size(); i++){
         byteh = arr.get(i);
       
       
       //Arrays.sort(arr, new compareMachine());
    
       for(int x = 4; x < byteh.length ; x++){
         if((int) byteh[x] != 0){
           for(int y=0;y<counter;y++){
             fos.write((byte) 0);
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
            DatagramPacket dpReceived = new DatagramPacket(buffer, buffer.length);
            ds.receive(dpReceived);
            byte[] receivedStructure = new byte[32768];
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
