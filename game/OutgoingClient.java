package game;

import java.io.DataOutputStream;
import java.io.IOException;

public class OutgoingClient extends Thread
{
	private DataOutputStream outToServer;
	private String request;

	public OutgoingClient(DataOutputStream outToServer)
	{
		this.outToServer = outToServer;
	}

	public void run()
	{
		System.out.println("test");
		boolean connected = true;
		while (connected)
		{
			if(request.length() > 0)
			{
				try {
					this.outToServer.writeBytes(this.getRequest() + '\n');
					this.clearRequest();
				} catch (IOException e) {}
			}
		}
	}

	/**
	 * Get request content
	 */

	public String getRequest()
	{
		return this.request;
	}

	/**
	 * Clear request content
	 * Actually not needed - but good to have
	 */

	public void clearRequest()
	{
		this.request = "";
	}


	/**
	 * Format output to server
	 */

	private void formatRequest(String type, String content)
	{
		String request;

		request = type + ";";
		request += content;

		this.request = request;
	}


	/**
	 * Method to tell server player wants to move
	 * @return
	 */

	public void movePlayer(Player me, String direction)
	{
		// Format request
		formatRequest("MOVEPLAYER", direction);
	}


	/**
	 * Add player
	 */

	public void addPlayer(String name)
	{
		// First strip spaces from name
		name = name.replaceAll("\\s", "");

		// Format request
		formatRequest("ADDPlAYER", name);
	}


	/**
	 * Remove player
	 */

	public void removePlayer(String name)
	{
		// First strip spaces from name
		name = name.replaceAll("\\s", "");

		// Format request
		formatRequest("REMOVEPLAYER", name);
	}


	/**
	 * Send key
	 */

	public void sendKey(String key)
	{
		// Format request
		formatRequest("KEY", key);
	}
}