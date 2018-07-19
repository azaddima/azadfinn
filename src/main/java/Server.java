import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Server extends Observable implements Runnable {
	
	InetAddress ip;
	int port;
	boolean connection = true;
	ServerSocket serverSocket;
	Socket socket;
	OutputStream outputStream;
	ObjectOutputStream write;
	Timer timer;
	ArrayList<MyFormTemplate> forms;

	public Server() {}

	@Override
	public void run() {
		startServer(port, forms);
	}

	public void startServer(int port, ArrayList forms) {

		this.port = port;
		//this.forms = forms;

		String message;
		String versendeteMsg;

		try {

			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for " + port + ".");
			socket = serverSocket.accept();
			System.out.println("Successfully connected to " + socket.getInetAddress().getHostAddress() + ".");

			outputStream = socket.getOutputStream();
			write = new ObjectOutputStream(outputStream);
			timer = new Timer(10, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					//	write.writeObject(forms);
						write.flush();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			});


			while(connection == true) {

				timer.start();

			}
			timer.stop();
			socket.close();
			serverSocket.close();
			write.close();

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
