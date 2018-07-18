import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server {
	
	int port = 12345;
	String message;
	String versendeteMsg;
	
	public Server() {
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Waiting for " + port + ".");
			Socket socket = serverSocket.accept();
			System.out.println("Successfully connected to " + socket.getInetAddress().getHostAddress() + ".");
			
			socket.close();
			serverSocket.close();
			
		
		}
		
		catch (UnknownHostException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
}
