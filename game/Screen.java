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

	public Screen(String[][] level, DataOutputStream dos)
	{
		super("TKgame v. 1.0");
		this.level = level;
		this.dos = dos;

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(500, 500);
		this.setResizable(true);
		this.setVisible(true);
		this.setLayout(new GridLayout(20, 20, 0, 0));
		this.addKeyListener(new Keylistener());
		draw();
		this.setAlwaysOnTop(true);
	}


	public void draw()
	{
		for (int j = 0; j < 20; j++)
		{
			for (int i = 0; i < 20; i++)
			{
				if (level[i][j].equalsIgnoreCase("w")) {
					JLabel l = new JLabel(new ImageIcon("./Image/mur1.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				} else if (level[i][j].equalsIgnoreCase("e")) {
					JLabel l = new JLabel(new ImageIcon("./Image/gulv2.png"));
					l.setSize(50, 50);
					this.add(l);
					labels[i][j] = l;
				}
			}
		}
	}


	public void drawPlayer(Player player)
	{
		String playerDirection = player.getDirection();
		String playerImage = "";

		if (playerDirection.equals("right")) {
			playerImage = "./Image/Helthoejre.png";
		}
		if (playerDirection.equals("left")) {
			playerImage = "./Image/Heltvenstre.png";
		}
		if (playerDirection.equals("up")) {
			playerImage = "./Image/HeltOp.png";
		}
		if (playerDirection.equals("down")) {
			playerImage = "./Image/HeltNed.png";
		}
		labels[player.getXpos()][player.getYpos()].setIcon(new ImageIcon(playerImage));
	}


	public void undrawPlayer(Player player)
	{
		labels[player.getXpos()][player.getYpos()].setIcon(new ImageIcon("./Image/gulv2.png"));
	}


	public class Keylistener implements KeyListener
	{
		public void keyPressed(KeyEvent theKeyEvent)
		{
			String type = null;
			if (theKeyEvent.getKeyCode() == KeyEvent.VK_UP)
			{
				type = "up";
			}
			if (theKeyEvent.getKeyCode() == KeyEvent.VK_DOWN)
			{
				type = "down";
			}
			if (theKeyEvent.getKeyCode() == KeyEvent.VK_LEFT)
			{
				type = "left";
			}
			if (theKeyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				type = "right";
			}

			// Press
			if(type != null)
				try {
					dos.writeBytes("MOVEPLAYER;" + type + '\n');
				} catch (IOException e) {}
		}

		public void keyReleased(KeyEvent ke) {}
		public void keyTyped(KeyEvent arg0) {}
	}
}