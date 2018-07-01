import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	private JFrame frame;
	private JPanel panel;
	private JLabel word;
	private JLabel lives;
	private JLabel guessed;
	private int numLives;
	private int rounds;
	private int round;
	private String answer;
	private ArrayList<String> answers = new ArrayList<String>();

	public Hangman() {
		numLives = 9;
		round = 0;
		setup();
		createUI();
	}

	public void createUI() {
		frame = new JFrame();
		panel = new JPanel();
		word = new JLabel();
		lives = new JLabel();
		guessed = new JLabel();
		frame.add(panel);
		frame.addKeyListener(this);
		panel.add(word);
		panel.add(lives);
		panel.add(guessed);
		frame.setVisible(true);
		panel.setVisible(true);
		createRound();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public void createRound() {
		String charHolder = "";
		for (int i = 0; i < answers.get(round).length(); i++) {
			charHolder += "_ ";
		}
		word.setText(charHolder);
		numLives = 9;
		lives.setText("LIVES: " + numLives);
		guessed.setText("Guessed:");
		frame.pack();

	}

	public void setup() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/dictionary.txt"));
			ArrayList<String> holder = new ArrayList<String>();
			rounds = Integer.parseInt(JOptionPane.showInputDialog("How many rounds would you like to play?"));
			Random r = new Random();
			for (int i = 0; i < 3000; i++) {
			holder.add(br.readLine());	
			}
			for (int i = 0; i < rounds; i++) {
				answers.add(holder.get(r.nextInt(3000)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
		}

}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		boolean wordGuessed = true;
		StringBuilder editor = new StringBuilder(word.getText());
		if(answers.get(round).contains("" + e.getKeyChar())) {
			for (int i = 0; i < editor.length(); i++) {
				if(answers.get(round).charAt(i/2) == e.getKeyChar() && i % 2 == 0)
				editor.setCharAt(i, e.getKeyChar());
			}
		}
		else {
			numLives--;
			guessed.setText(guessed.getText() + " " + e.getKeyChar() + ",");
			frame.pack();
		}
		lives.setText("LIVES: " + numLives);
		word.setText(editor.toString());
		for (int i = 0; i < word.getText().length(); i++) {
			if(word.getText().charAt(i) == '_') {
				wordGuessed = false;
			}
		}
		if(wordGuessed) {
			if(round == rounds - 1) {
				frame.dispose();
				JOptionPane.showMessageDialog(null, "Congradulations, you win!");
				System.exit(0);
			}
			round++;
			createRound();
		}
		if(numLives <= 0) {
			frame.dispose();
			JOptionPane.showMessageDialog(null, "Sorry, you lost. The word was " + answers.get(round));
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
