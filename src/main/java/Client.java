import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	int port;
	String host; //Localhost
	
	public Client() {
		
	}

	public void connectToServer(int port, String host) {

		this.port = port;
		this.host = host;

		try {

			System.out.println("Trying to connect to port " + port + "and host " + host + ".");

			Socket socket = new Socket(host, port);

			System.out.println("Client is connected to" + socket.getInetAddress().getHostAddress() + ".");

			socket.close();

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
