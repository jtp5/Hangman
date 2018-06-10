import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman {
	private JFrame frame;
	private JPanel panel;
	private JLabel word;
	private JLabel lives;
	private int numLives;
	private int rounds;
	private int round;

	public Hangman() {
		createUI();
		setup();
		numLives = 9;
		rounds = Integer.parseInt(JOptionPane.showInputDialog("How many rounds would you like to play?"));
		round = 0;
	}

	public void createUI() {
		frame = new JFrame();
		panel = new JPanel();
		word = new JLabel();
		lives = new JLabel();
		frame.add(panel);
		panel.add(word);
		panel.add(lives);
		frame.setVisible(true);
		panel.setVisible(true);
		word.setText("_ _ _ _ _ _ _ _ ");
		lives.setText("LIVES: " + numLives);
		frame.pack();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	public void setup() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/dictionary.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
		}
	}

}
