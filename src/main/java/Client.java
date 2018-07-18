import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	int port = 12345;
	String host = "127.0.0.1"; //Localhost
	
	public Client() {
		
		try {
			
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
