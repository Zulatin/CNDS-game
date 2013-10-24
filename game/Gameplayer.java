package game;

import java.util.ArrayList;

public class Gameplayer
{
	// Screen screen;
	ArrayList<Player> players;
	ScoreList scoreList;

	private String[][] level =
		{
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "e", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "w", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "w", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "w", "e", "e", "e", "w" },
			{ "w", "e", "w", "w", "e", "w", "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "e", "e", "e", "e", "w", "e", "e", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "w", "e", "w", "w", "w", "e", "e", "w", "e", "w", "e", "e", "w", "w", "e", "w", "w" },
			{ "w", "e", "w", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w", "e", "e", "e", "e", "e", "w", "w" },
			{ "w", "e", "e", "e", "w", "e", "e", "e", "w", "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "w" },
			{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w" },
		};
	public Gameplayer(ScoreList scoreList, ArrayList<Player> players)
	{
		this.scoreList = scoreList;
		this.players = players;
	}

	public String[][] getLevel()
	{
		return level;
	}
}