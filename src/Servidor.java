
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		int porta=6789;
		String ip= "localhost";
		System.out.println("Servidor inicializado:");
		System.out.println("Porta: " + porta);
		System.out.println("Ip:" + ip);
		
		ServerSocket welcomeSocket = new ServerSocket(porta);
		
		ClienteThread thread;
		
		while(true)
		{
			Socket connectionSocket = welcomeSocket.accept();
			thread = new ClienteThread(connectionSocket);
			Thread t = new Thread(thread);
			t.start();
		}

	}

}
