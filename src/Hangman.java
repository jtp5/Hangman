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
	private int numLives;
	private int rounds;
	private int round;
	private String answer;
	private ArrayList<String> answers = new ArrayList<String>();

	public Hangman() {
		setup();
		createUI();
		numLives = 9;
		round = 0;
	}

	public void createUI() {
		frame = new JFrame();
		panel = new JPanel();
		word = new JLabel();
		lives = new JLabel();
		frame.add(panel);
		frame.addKeyListener(this);
		panel.add(word);
		panel.add(lives);
		frame.setVisible(true);
		panel.setVisible(true);
		String charHolder = "";
		for (int i = 0; i < answers.get(round).length(); i++) {
			charHolder += "_ ";
		}
		word.setText(charHolder);
		lives.setText("LIVES: " + numLives);
		frame.pack();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
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
		StringBuilder editor = new StringBuilder(word.getText());
		if(answers.get(round).contains("" + e.getKeyChar())) {
			editor.setCharAt(answers.get(round).indexOf(e.getKeyChar()) * 2, e.getKeyChar());
			word.setText(editor.toString());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
