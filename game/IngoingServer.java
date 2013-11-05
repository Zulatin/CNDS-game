package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

public class IngoingServer extends Thread
{
	private Player player;
	private String direction = "left", wall = "w", command = "";
	private Timer timer;
	private boolean permissionToShoot;
	private Random random;

	public IngoingServer(Player player)
	{
		this.player = player;
		this.permissionToShoot = true;
		this.random = new Random();
	}

	public void run()
	{
		while (true)
		{

			String getInFromClient = null;
			try
			{
				getInFromClient = this.player.getInFromClient().readLine();
			} catch (IOException e1)
			{
			}

			// Check if getInFromClient
			if (getInFromClient == null)
			{

				// Close thread
				Thread.currentThread().interrupt();
				Server.players.remove(this.player);
				return;

			} else
			{

				// Split string from client
				String[] message = getInFromClient.split(";");

				// Get players positions
				int x = this.player.getXpos();
				int y = this.player.getYpos();

				// Get board
				String[][] board = this.player.getBoard().getLevel();

				// Start players
				ArrayList<Player> players = new ArrayList<Player>(this.player.getBoard().getPlayers());

				// Find direction and command
				this.command = message[0];

				// Go in here if command is moveplayer
				if (this.command.equals("MOVEPLAYER"))
				{
					this.direction = message[1];
					if (this.direction != null)
					{
						this.player.setDirection(this.direction);
						x = this.player.getXpos();
						y = this.player.getYpos();

						if (this.direction.equals("right"))
						{
							x = this.player.getXpos() + 1;
						}
						if (this.direction.equals("left"))
						{
							x = this.player.getXpos() - 1;
						}
						if (this.direction.equals("up"))
						{
							y = this.player.getYpos() - 1;
						}
						if (this.direction.equals("down"))
						{
							y = this.player.getYpos() + 1;
						}

						String[][] level = this.player.getBoard().getLevel();
						if (level[x][y].equals(this.wall))
						{

							/**
							 * Well, this worked, until we changed the rules
							 * So bye bye birdie
							 */

							// Take a point from player
							// player.subOnePoint();

							// Update scoreboard
							// Server.scoreList.updateScoreOnScreenAll();

						} else
						{

							// Give a point to player
							// player.addOnePoint();

							// Update scoreboard
							// Server.scoreList.updateScoreOnScreenAll();

							// Set player positions
							this.player.setXpos(x);
							this.player.setYpos(y);

						}

						// Write to clients
						this.writeToClient();
					}
				}

				// REMOVEPLAYER
				/*
				 * if (this.command.equals("REMOVEPLAYER"))
				 * {
				 * }
				 */

				// Shoot
				if (this.command.equals("SHOOT"))
				{
					if (this.permissionToShoot)
					{
						try
						{
							this.ShootSound();
						} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1)
						{
							e1.printStackTrace();
						}

						this.direction = this.player.getDirection();

						// check x for other players, if found check if that
						// players
						// y coord is SMALLER
						if (this.direction.equals("up"))
						{
							y--;
							boolean found = false;

							while (!found)
							{
								int counter = 0;
								if (!board[x][y].equals("w"))
								{
									boolean playerFound = false;
									while (!playerFound && counter < players.size())
									{
										Player playerToShoot = players.get(counter);
										if (playerToShoot.getYpos() == y && playerToShoot.getXpos() == x)
										{
											playerFound = true;
											found = true;
											this.player.shotPlayer();
											int ranX = this.random.nextInt(20);
											int ranY = this.random.nextInt(20);
											String[][] level = this.player.getBoard().getLevel();
											while (level[ranX][ranY].equals("w"))
											{
												ranX = this.random.nextInt(20);
												ranY = this.random.nextInt(20);

												try
												{
													dieShoud();
												} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
												{
													e.printStackTrace();
												}
											}
											playerToShoot.setXpos(ranX);
											playerToShoot.setYpos(ranY);
											this.writeToClient();
										} else
										{
											counter++;
										}
									}
									y = y - 1;
								} else
								{
									found = true;
								}
							}
						}

						// check x for other players, if found check if that
						// players
						// y coord is BIGGER
						if (this.direction.equals("down"))
						{
							// y+1
							y++;
							boolean found = false;

							while (!found)
							{
								int counter = 0;
								if (!board[x][y].equals("w"))
								{

									boolean playerFound = false;

									while (!playerFound && counter < players.size())
									{
										Player playerToShoot = players.get(counter);
										if (playerToShoot.getYpos() == y && playerToShoot.getXpos() == x)
										{
											playerFound = true;
											found = true;
											this.player.shotPlayer();
											int ranX = this.random.nextInt(20);
											int ranY = this.random.nextInt(20);
											String[][] level = this.player.getBoard().getLevel();
											while (level[ranX][ranY].equals("w"))
											{
												ranX = this.random.nextInt(20);
												ranY = this.random.nextInt(20);
												try
												{
													dieShoud();
												} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
												{
													e.printStackTrace();
												}
											}
											playerToShoot.setXpos(ranX);
											playerToShoot.setYpos(ranY);
											this.writeToClient();
										} else
										{
											counter++;
										}

									}
									y = y + 1;
								} else
								{
									found = true;
								}
							}
						}

						// check y for other players, if found check if that
						// players
						// x coord is SMALLER
						if (this.direction.equals("left"))
						{
							// x-1
							x--;
							boolean found = false;

							while (!found)
							{
								int counter = 0;
								if (!board[x][y].equals("w"))
								{
									boolean playerFound = false;

									while (!playerFound && counter < players.size())
									{
										Player playerToShoot = players.get(counter);
										if (playerToShoot.getYpos() == y && playerToShoot.getXpos() == x)
										{
											playerFound = true;
											found = true;
											this.player.shotPlayer();
											int ranX = this.random.nextInt(20);
											int ranY = this.random.nextInt(20);
											String[][] level = this.player.getBoard().getLevel();
											while (level[ranX][ranY].equals("w"))
											{
												ranX = this.random.nextInt(20);
												ranY = this.random.nextInt(20);
												try
												{
													dieShoud();
												} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
												{
													e.printStackTrace();
												}
											}
											playerToShoot.setXpos(ranX);
											playerToShoot.setYpos(ranY);
											this.writeToClient();
										} else
										{
											counter++;
										}

									}
									x = x - 1;
								} else
								{
									found = true;
								}
							}
						}

						// check y for other players, if found check if that
						// players
						// y coord is BIGGER
						if (this.direction.equals("right"))
						{
							// x+1
							x++;
							boolean found = false;

							while (!found)
							{
								int counter = 0;
								if (!board[x][y].equals("w"))
								{
									boolean playerFound = false;

									while (!playerFound && counter < players.size())
									{
										Player playerToShoot = players.get(counter);
										if (playerToShoot.getYpos() == y && playerToShoot.getXpos() == x)
										{
											playerFound = true;
											found = true;
											this.player.shotPlayer();
											int ranX = this.random.nextInt(20);
											int ranY = this.random.nextInt(20);
											String[][] level = this.player.getBoard().getLevel();
											while (level[ranX][ranY].equals("w"))
											{
												ranX = this.random.nextInt(20);
												ranY = this.random.nextInt(20);
												try
												{
													dieShoud();
												} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
												{
													e.printStackTrace();
												}
											}
											playerToShoot.setXpos(ranX);
											playerToShoot.setYpos(ranY);
											this.writeToClient();
										} else
										{
											counter++;
										}

									}
									x = x + 1;
								} else
								{
									found = true;
								}
							}
						}

						int delay = 1000;
						this.permissionToShoot = false;

						ActionListener taskPerform = new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent arg0)
							{
								IngoingServer.this.permissionToShoot = true;
							}
						};

						this.timer = new Timer(delay, taskPerform);
						this.timer.setRepeats(false);
						this.timer.start();
					}
				}

				// Update scoreboard
				Server.scoreList.setPlayers(players);
				Server.scoreList.updateScoreOnScreenAll();

			}
		}
	}

	private void ShootSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException
	{
		Clip clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(new File("./Sound/gunShot.wav")));
		clip.start();
	}

	private void dieShoud() throws LineUnavailableException, IOException, UnsupportedAudioFileException
	{
		Clip clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(new File("./Sound/Combowhore.wav")));
		clip.start();
	}

	public void writeToClient()
	{
		// Create toClient string
		String toClient = "";

		for (Player getPlayer : Server.players)
		{
			toClient += getPlayer.getName() + ";" + getPlayer.getXpos() + ";" + getPlayer.getYpos() + ";" + getPlayer.getPoint() + ";" + getPlayer.getDirection() + ";" + getPlayer.getColor() + ";";
		}

		try
		{
			// For-each players and write players to them
			for (Player getPlayers : Server.players)
				getPlayers.output(toClient);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
