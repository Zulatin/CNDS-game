package game;

import java.util.ArrayList;

public class InterpretPlayers
{
	private static ArrayList<Player> players;

	public static ArrayList<Player> interpret(String response)
	{
		players = null;
		String[] boardUpdate = response.split(";");
		for(int index = 0; index < boardUpdate.length-4; index += 5)
		{
			Player p = new Player(boardUpdate[index], Integer.parseInt(boardUpdate[index+3]));
			p.setXpos(Integer.parseInt(boardUpdate[index+1]));
			p.setYpos(Integer.parseInt(boardUpdate[index+2]));
			p.setDirection(boardUpdate[index+4]);
			players.add(p);
		}

		return players;
	}
}
