import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;

public class Server extends Observable implements Runnable {
	
	InetAddress ip;
	int port;
	boolean threadIsRunning = true;
	ServerSocket serverSocket;
	Socket socket;
	OutputStream outputStream;
	ObjectOutputStream write;
	ArrayList<MyFormTemplate> paintAreaForms;


	public Server(ArrayList<MyFormTemplate> paintAreaForms) {
		this.paintAreaForms = paintAreaForms;
	}

	@Override
	public void run() {
		startServer();
		while(threadIsRunning) {

			try {
				sendData();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void startServer() {

		try {

			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for " + port + ".");
			socket = serverSocket.accept();
			System.out.println("Successfully connected to " + socket.getInetAddress().getHostAddress() + ".");

			outputStream = socket.getOutputStream();
			write = new ObjectOutputStream(outputStream);

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

	public void stopServer() {

		try {
			if(socket != null) {
				socket.close();
			}
			if(write != null) {
				write.close();
			}
			serverSocket.close();

			threadIsRunning = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//Get IP address for IP address Label in Server Panel
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

	public void sendData() {

		try {
			write.writeObject(paintAreaForms);
			write.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setThreadIsRunning(boolean threadIsRunning) {
		this.threadIsRunning = threadIsRunning;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
