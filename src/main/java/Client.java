import jdk.internal.util.xml.impl.Input;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client extends Thread {
	
	int port;
	String host; //Localhost
	Socket socket;
	InputStream inputStream;
	ObjectInputStream read;
	PaintArea paintArea;
	ArrayList<MyFormTemplate> paintAreaForms;
	boolean threadIsRunning = true;
	
	public Client(PaintArea paintArea) {
		this.paintArea = paintArea;
	}


	@Override
	public void run() {
		super.run();
		connectToServer();
		while(threadIsRunning) {
			try {
				recieveData();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void connectToServer() {

		try {

			System.out.println("Trying to connect to port " + port + "and host " + host + ".");
			socket = new Socket(host, port);
			System.out.println("Client is connected to" + socket.getInetAddress().getHostAddress() + ".");
			inputStream = socket.getInputStream();
			read = new ObjectInputStream(inputStream);

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

	public void stopConnection() {

		try {
			if(inputStream != null) {
				inputStream.close();
			}
			if (read != null) {
				read.close();
			}
			if(socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void recieveData() {

		try {
			paintAreaForms = (ArrayList<MyFormTemplate>) read.readObject();
			paintArea.setForms(paintAreaForms);
			paintArea.repaint();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setThreadIsRunning(boolean threadIsRunning) {
		this.threadIsRunning = threadIsRunning;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
