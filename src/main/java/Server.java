import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Observable;


//This class handles the connection from the drawer/server to the watcher/client
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

	//Thread that is handling the connection and sending the data
	@Override
	public void run() {
		startServer();
		while(threadIsRunning) {

			try {
				sendData();
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stopServer();
	}

	//Method to start the server and wait for a connector
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

	//Method to stop the connection to the watcher
	public void stopServer() {

		try {
			threadIsRunning = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				write.close();
				outputStream.close();
				socket.close();
				System.out.println(socket.isClosed());
				System.out.println("Ist geschlosen");
			} catch (IOException e) {
				e.printStackTrace();
			}
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

	//Method to update the watcher
	public void sendData() {
		try {

			write.writeObject(paintAreaForms);
			write.flush();
			System.out.println("Data was sent: " + paintAreaForms.size());
			write.reset();
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
