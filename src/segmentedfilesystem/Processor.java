import java.io.*;
import java.net.*;
import java.util.*;

public class Processor {
	private DatagramSocket socket;
	private List<String> listQuotes = new ArrayList<String>();
	private Random random;

	public Processor(int port) throws SocketException {
		socket = new DatagramSocket(port);
		random = new Random();
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Syntax: Processor <file> <port>");
			return;
		}

		String smileFile = args[0];
		int port = Integer.parseInt(args[1]);

		try{
			Processor server = new Processor(port);
			server.loadSmileFile(smileFile);
			server.service();
		}
		catch (SocketException ex) {
			System.out.println("Socket error: " + ex.getMessage());
			}
		catch (IOException ex) {
			System.out.println("I/O error: " + ex.getMessage());
		}
	}

	private void service() throws IOException {
		while (true) {
			DatagramPacket request = new DatagramPacket(new byte[1], 1);
			socket.receive(request);

			String quote = getRandomQuote();
			byte[] buffer = quote.getBytes();

			InetAddress clientAddress = request.getAddress();
			int clientPort = request.getPort();

			DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
			socket.send(response);
		}
	}

	private void loadSmileFile(String smileFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(smileFile));
		String aQuote;

		while ((aQuote = reader.readLine() != null) {
			listQuotes.add(aQuote);
		}
		reader.close();
	}

	private String getRandomQuote() {
		int randomIndex = random.nextInt(listQuotes.size());
		String randomQuote = listQuotes.get(randomIndex);
		return randomQuote;
	}
}

