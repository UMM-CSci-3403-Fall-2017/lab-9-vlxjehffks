package segmentedfilesystem;


import java.io.*;
import java.net.*;
  
public class Sender{
        public static void main(String[] args){
                if (args.length < 2) {
                        System.out.println("Syntax: Main <hostname> <port>");
                        return;
                }
                int port = 6014;
        try{
                InetAddress address = InetAddress.getByName("localhost");
                DatagramSocket socket = new DatagramSocket();

                while (true) {
                        byte[] one = new byte[1];
                        DatagramPacket request = new DatagramPacket(one, 1, address, port);
                        socket.send(request);

                        byte[] buffer = new byte[1028];
                        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                        socket.receive(response);


                        String quote = new String(buffer, 0, response.getLength());
                        System.out.println(quote);
                        System.out.println();

                        Thread.sleep(10000);
                }}
                catch (SocketTimeoutException ex) {
                        System.out.println("Timeout error: " + ex.getMessage());
                        ex.printStackTrace();
                }
                catch (IOException ex) {
                        System.out.println("Client error: " + ex.getMessage());
                        ex.printStackTrace();
                }
                catch (InterruptedException ex){
                        ex.printStackTrace();
                }

        }

                }

