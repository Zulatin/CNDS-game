package game;

import java.io.IOException;
import java.util.ArrayList;

public class IngoingServer extends Thread {
	private Player player;
	private String direction = "left", wall = "w", command = "";

	public IngoingServer(Player player) {
		this.player = player;
	}

	public void run() {
		while (true) {
			String getInFromClient = null;
			try {
				getInFromClient = player.getInFromClient().readLine();
			} catch (IOException e1) {
			}

			// Check if getInFromClient
			if (getInFromClient == null) {

				// Close thread
				Thread.currentThread().interrupt();
				Server.players.remove(player);
				return;

			} else {

				// Split string from client
				String[] message = getInFromClient.split(";");

				int x = player.getXpos();
				int y = player.getYpos();
				String[][] board = player.getBoard().getLevel();
				ArrayList<Player> players = new ArrayList<Player>(player
						.getBoard().getPlayers());

				// Find direction and command

				command = message[0];
			

				if (command.equals("MOVEPLAYER")) {
					direction = message[1];
					if (direction != null) {
						player.setDirection(direction);
						x = player.getXpos();
						y = player.getYpos();

						if (direction.equals("right")) {
							x = player.getXpos() + 1;
						}
						if (direction.equals("left")) {
							x = player.getXpos() - 1;
						}
						if (direction.equals("up")) {
							y = player.getYpos() - 1;
						}
						if (direction.equals("down")) {
							y = player.getYpos() + 1;
						}

						String[][] level = player.getBoard().getLevel();
						if (level[x][y].equals(wall)) {

							// Take a point from player
							player.subOnePoint();
							// Update scoreboard
							Server.scoreList.updateScoreOnScreenAll();

						} else {

							// Give a point to player
							player.addOnePoint();
							// Update scoreboard
							Server.scoreList.updateScoreOnScreenAll();

							// Set player positions
							player.setXpos(x);
							player.setYpos(y);

						}

						writeToClient();
					}
				}
				if (command.equals("REMOVEPLAYER")) {

				}
				if (command.equals("SHOOT")) {
					System.out.println("command = shoot!");
					direction = player.getDirection();

					// check x for other players, if found check if that
					// players
					// y coord is SMALLER

					if (direction.equals("up")) {
						y--;
						boolean found = false;
						int counter = 0;

						while (!found) {
							if (!board[x][y].equals("w")) {
								boolean playerFound = false;
								Player playerToShoot = players.get(counter);
								while (!playerFound && counter < players.size()) {
									if (playerToShoot.getYpos() == y
											&& playerToShoot.getXpos() == x) {
										playerFound = true;
										found = true;
										playerToShoot.shotPlayer();
										playerToShoot.setXpos(5);
										playerToShoot.setYpos(7);
										writeToClient();
									} else {
										counter++;
									}
									counter = 0;
									y = y - 1;
								}
							} else {
								found = true;
							}
						}

					}

					// check x for other players, if found check if that
					// players
					// y coord is BIGGER
					if (direction.equals("down")) {
						// y+1
						y++;
						boolean found = false;
						int counter = 0;

						while (!found) {
							if (!board[x][y].equals("w")) {
								boolean playerFound = false;
								Player playerToShoot = players.get(counter);
								while (!playerFound && counter < players.size()) {
									if (playerToShoot.getYpos() == y
											&& playerToShoot.getXpos() == x) {
										playerFound = true;
										found = true;
										playerToShoot.shotPlayer();
										playerToShoot.setXpos(5);
										playerToShoot.setYpos(7);
										writeToClient();
									} else {
										counter++;
									}
									counter = 0;
									y = y + 1;
								}
							} else {
								found = true;
							}
						}

					}
					// check y for other players, if found check if that
					// players
					// x coord is SMALLER
					if (direction.equals("left")) {
						// x-1
						x--;
						boolean found = false;
						int counter = 0;

						while (!found) {
							if (!board[x][y].equals("w")) {
								boolean playerFound = false;
								Player playerToShoot = players.get(counter);
								while (!playerFound && counter < players.size()) {
									if (playerToShoot.getYpos() == y
											&& playerToShoot.getXpos() == x) {
										playerFound = true;
										found = true;
										playerToShoot.shotPlayer();
										playerToShoot.setXpos(5);
										playerToShoot.setYpos(7);
										writeToClient();
									} else {
										counter++;
									}
									counter = 0;
									x = x - 1;
								}
							} else {
								found = true;
							}
						}
					}
					// check y for other players, if found check if that
					// players
					// y coord is BIGGER
					if (direction.equals("right")) {
						// x+1
						x++;
						boolean found = false;
						int counter = 0;

						while (!found) {
							if (!board[x][y].equals("w")) {
								boolean playerFound = false;
								Player playerToShoot = players.get(counter);
								while (!playerFound && counter < players.size()) {
									if (playerToShoot.getYpos() == y
											&& playerToShoot.getXpos() == x) {
										playerFound = true;
										found = true;
										playerToShoot.shotPlayer();
										playerToShoot.setXpos(5);
										playerToShoot.setYpos(7);
										writeToClient();
									} else {
										counter++;
									}
									counter = 0;
									x = x + 1;
								}
							} else {
								found = true;
							}
						}

					}

				}
			}
		}
	}

	public void writeToClient() {
		// Create toClient string
		String toClient = "";

		for (Player getPlayer : Server.players) {
			toClient += getPlayer.getName() + ";" + getPlayer.getXpos() + ";"
					+ getPlayer.getYpos() + ";" + getPlayer.getPoint() + ";"
					+ getPlayer.getDirection() + ";" + getPlayer.getColor()
					+ ";";
		}

		try {

			// For-each players and write players to them
			for (Player getPlayers : Server.players)
				getPlayers.output(toClient);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
