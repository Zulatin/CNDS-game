package game;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen extends JFrame
{
	private JLabel[][] labels = new JLabel[20][20];
	private String[][] level;
	private DataOutputStream dos;
	Keylistener keyListener;

	public Screen(String[][] level, DataOutputStream dos)
	{
		super("Bubble Shooter");
		this.level = level;
		this.dos = dos;

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(200, 50);
		this.setSize(700, 700);
		this.setResizable(true);
		this.setVisible(true);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		this.keyListener = new Keylistener();
		this.addKeyListener(this.keyListener);
		this.draw();
		this.setAlwaysOnTop(true);
	}

	public void draw()
	{
		for (int j = 0; j < 20; j++)
		{
			for (int i = 0; i < 20; i++)
			{
				if (this.level[i][j].equalsIgnoreCase("w"))
				{
					JLabel l = new JLabel(new ImageIcon("./Image/BubbleWall.png"));
					l.setSize(50, 50);
					this.add(l);
					this.labels[i][j] = l;
				}
				else if (this.level[i][j].equalsIgnoreCase("e"))
				{
					JLabel l = new JLabel(new ImageIcon("./Image/BubbleFloor.png"));
					l.setSize(50, 50);
					this.add(l);
					this.labels[i][j] = l;
				}
			}
		}
	}

	public void drawPlayer(Player player)
	{
		String playerDirection = player.getDirection(), playerImage = "", color = player.getColor();

		if (playerDirection.equals("right"))
		{
			playerImage = "./Image/bubble/" + color + "/bubble_right.png";
		}
		if (playerDirection.equals("left"))
		{
			playerImage = "./Image/bubble/" + color + "/bubble_left.png";
		}
		if (playerDirection.equals("up"))
		{
			playerImage = "./Image/bubble/" + color + "/bubble_up.png";
		}
		if (playerDirection.equals("down"))
		{
			playerImage = "./Image/bubble/" + color + "/bubble_down.png";
		}

		this.labels[player.getXpos()][player.getYpos()].setIcon(new ImageIcon(playerImage));
	}

	public void undrawPlayer(Player player)
	{
		this.labels[player.getXpos()][player.getYpos()].setIcon(new ImageIcon("./Image/BubbleFloor.png"));
	}

	public void connectionClosed(boolean closed)
	{
		this.keyListener.connectionClosed(closed);
	}

	public class Keylistener implements KeyListener
	{
		private boolean closed = false;

		public void connectionClosed(boolean closed)
		{
			this.closed = true;
		}

		public void keyPressed(KeyEvent theKeyEvent)
		{
			String type = null, command = null;
			if (theKeyEvent.getKeyCode() == KeyEvent.VK_UP)
			{
				command = "MOVEPLAYER";
				type = "up";
			}
			else if (theKeyEvent.getKeyCode() == KeyEvent.VK_DOWN)
			{
				command = "MOVEPLAYER";
				type = "down";
			}
			else if (theKeyEvent.getKeyCode() == KeyEvent.VK_LEFT)
			{
				command = "MOVEPLAYER";
				type = "left";
			}
			else if (theKeyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				command = "MOVEPLAYER";
				type = "right";
			}
			else if (theKeyEvent.getKeyCode() == KeyEvent.VK_SPACE)
			{
				command = "SHOOT";
			}

			// Press
			try
			{
				if (command != null && this.closed == false)
					Screen.this.dos.writeBytes(command + ";" + type + '\n');
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		public void keyReleased(KeyEvent ke)
		{
		}

		public void keyTyped(KeyEvent arg0)
		{
		}
	}
}