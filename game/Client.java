package game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args)throws Exception {
		
	}

	public static void main(String[] args) throws IOException{
		System.out.println("Input player name");
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("192.168.1.1", 7531);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		String sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		String response = inFromServer.readLine();
		if (!response.equals("Server full")){
			
			IngoingClient inThread = new IngoingClient();
			OutgoingClient outThread = new OutgoingClient();
			
			inThread.start();
			outThread.start();
		}
		else {
			System.out.println("Server is full");
		}
		
		
		clientSocket.close();
	}
}
