import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

//This class contains everything that is used by the watcher to connect himself to the server

public class Client extends Thread {
	
	int port;
	String host; //Localhost
	PaintArea paintArea;
	ObjectInputStream read;
	Socket socket;
	boolean clientIsRunning = false;
	boolean threadIsRunning = true;
	
	public Client(PaintArea paintArea) {
		this.paintArea = paintArea;
	}


	//Thread that takes care of transfering the data from server to client
	@Override
	public void run() {
		super.run();
		connectToServer();
		while(threadIsRunning) {
			try {
				recieveData();

				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	//Method to connect the client to the server
	public void connectToServer() {

		try {

			System.out.println("Trying to connect to port " + port + "and host " + host + ".");
			socket = new Socket(host, port);
			System.out.println("Client is connected to" + socket.getInetAddress().getHostAddress() + ".");
			read = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
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

	//Method to stop the connection to the server
	public void stopConnection() {

		try {
			read.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Method to
	public void recieveData() {

		try {
			//Reading the data here
			Object data = read.readObject();

			//Check if data is an instance of the painted objects
			if(data instanceof ArrayList) {

				if (data != null) {

					ArrayList<MyFormTemplate> paintAreaForms = (ArrayList<MyFormTemplate>) data;
					System.out.println("Data recieved:" + ((ArrayList<MyFormTemplate>) data).size());
					paintArea.setForms((ArrayList<MyFormTemplate>) data);
					paintArea.repaint();


				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
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

	public boolean IsClientStarted() {
		return clientIsRunning;
	}

	public void setClientIsRunning(boolean clientIsRunning) {
		this.clientIsRunning = clientIsRunning;
	}
}
