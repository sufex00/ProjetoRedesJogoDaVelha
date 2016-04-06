import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
	public static void main(String argv[]) throws Exception {
		String sentence="" ;
		int porta=6789;
		String ip= "localhost";
		String modifiedSentence;
		System.out.println("CLIENTE INICIADO, DIGITE UM TEXTO: ");

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));

		

		while(!sentence.equals("finalizar"))
		{
			Socket clientSocket = new Socket(ip,  porta);
			
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

		sentence = inFromUser.readLine();

		outToServer.writeBytes(sentence + '\n');

		modifiedSentence = inFromServer.readLine();

		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
		}
	}
}
