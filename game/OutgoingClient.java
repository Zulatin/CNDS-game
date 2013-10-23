package game;

public class OutgoingClient extends Thread
{
	private int y;
	private int x;
	private String request;

	public OutgoingClient()
	{

	}

	public void run()
	{
		boolean connected = true;
		while (connected)
		{

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
}