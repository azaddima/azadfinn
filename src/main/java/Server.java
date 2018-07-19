import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Server extends Observable {
	
	InetAddress ip;
	int port;
	
	public Server() {
		

	}

	public void startServer(int port) {

		this.port = port;

		String message;
		String versendeteMsg;

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

	public void getIpAddress() {
		try {
			ip = InetAddress.getLocalHost();
			setChanged();
			notifyObservers(ip);
			System.out.println(ip.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
}
