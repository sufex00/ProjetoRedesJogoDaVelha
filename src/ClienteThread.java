import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import States.StatesGerenciandoJogo;
import States.StatesInicio;
import States.StatesProcessando;
import States.StatesServidor;

public class ClienteThread implements Runnable
{
	
	private Socket connectionSocket;
	
	public ClienteThread(Socket s) 
	{
		this.connectionSocket = s;
	}
	
	StatesServidor states= new StatesInicio();

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
		String clientMensagem;
		String ServidorMensagem;
		BufferedReader inFromClient;
		DataOutputStream outToClient;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(
					connectionSocket.getInputStream()));

			outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());

			ServidorMensagem = inFromClient.readLine();
			if(ServidorMensagem.equals("INICIAR JOGO"))
			{
				states = new StatesInicio();
			}
			if(ServidorMensagem.equals("000000000"))
			{
				states = new StatesGerenciandoJogo();
			}
			if(ServidorMensagem.equals("FIM DE JOGO!"))
			{
				states = new StatesProcessando();
			}
			states.run();
			ServidorMensagem = ServidorMensagem.toUpperCase() + '\n';

			outToClient.writeBytes(ServidorMensagem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
