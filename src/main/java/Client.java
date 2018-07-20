import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

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

	public void connectToServer() {

		try {

			System.out.println("Trying to connect to port " + port + "and host " + host + ".");
			socket = new Socket(host, port);
			System.out.println("Client is connected to" + socket.getInetAddress().getHostAddress() + ".");

			read = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			//read = new ObjectInputStream(socket.getInputStream());


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
			read.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void recieveData() {

		try {
			Object data = read.readObject();

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

	public void clientStarted(boolean clientStarted) {
		this.clientIsRunning = clientIsRunning;
	}
}
