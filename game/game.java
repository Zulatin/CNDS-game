package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class game
{
	public static ArrayList<Player> players;
	public static Player me;
	//Test commit!
	 public static void main(String[] args) throws Exception
	{
		System.out.println("Indtast dit spillernavn");
		BufferedReader bName = new BufferedReader(new InputStreamReader(System.in));

		// Make player list
		players = new ArrayList<Player>();

		// Create you
		me = new Player(bName.readLine());

		// Add players to play list
		players.add(me);
		players.add(new Player("FUP"));

		// Create a scorelist
		ScoreList scoreList = new ScoreList(players);
		scoreList.setVisible(true);

		// Spawn new player - thats you
		new gameplayer(me, scoreList, players);
	}
}