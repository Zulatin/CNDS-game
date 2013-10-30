package game;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Client extends JFrame
{
	private Panel panel;
	private JLabel lblIp;
	private JTextField txtIp;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JButton btnConnect;
	private Handler handler;



	public static void main(String[] args) throws Exception
	{
		Client c = new Client();
		c.setVisible(true);

	}

	public Client() throws Exception{
		setBounds(400, 400, 421, 66);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		handler = new Handler();

		panel = new Panel();
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblIp = new JLabel("IP");
		panel.add(lblIp);

		txtIp = new JTextField();
		panel.add(txtIp);
		txtIp.setColumns(10);

		lblUsername = new JLabel("Username");
		panel.add(lblUsername);

		txtUsername = new JTextField();
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(handler);
		panel.add(btnConnect);

	}

	private void login(String ip, String username) throws Exception{


		// Start connection to server
		Socket clientSocket = new Socket(ip, 7531);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		// Send add player to server
		outToServer.writeBytes("ADDPLAYER;" + username + '\n');
		// Get response from server
		String response = inFromServer.readLine();

		// If server is not full
		if (!response.equals("SERVERFULL"))
		{
			int index = 0;
			String[] settings = response.split(";");
			ArrayList<Player> players = new ArrayList<Player>();

			for (; index < settings.length; index += 6)
			{
				Player p = new Player(settings[index], Integer.parseInt(settings[index + 3]));
				p.setXpos(Integer.parseInt(settings[index + 1]));
				p.setYpos(Integer.parseInt(settings[index + 2]));
				p.setDirection(settings[index + 4]);
				p.setColor(settings[index + 5]);
				players.add(p);
			}

			// Create a scorelist
			ScoreList scoreList = new ScoreList(players);
			// scoreList.draw();
			scoreList.setVisible(true);

			// Gameplayet
			Gameplayer game = new Gameplayer(scoreList, players);
			Screen screen = new Screen(game.getLevel(), outToServer);
			screen.setVisible(true);

			// Draw players
			for (Player player : players)
			{
				screen.drawPlayer(player);
			}

			// Start sound
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("./Sound/sound.wav")));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();

			// IngoingClient needs gameplayer to make changes
			IngoingClient inThread = new IngoingClient(inFromServer, game, screen);

			// Shizzle on the thread
			inThread.start();
		}
		else
		{
			// Close connection
			clientSocket.close();

			System.out.println("The server is full... Please try again later.");
		}

	}


	private class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource().equals(btnConnect)){
				try {
					login(txtIp.getText(), txtUsername.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
}