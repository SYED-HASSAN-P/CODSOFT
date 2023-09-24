import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGameGUI extends JFrame {
    private int targetNumber;
    private int attemptsLeft;
    private int roundsPlayed;
    private int roundsWon;

    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel roundsLabel;
    private JLabel roundsWonLabel;

    public NumberGameGUI() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeGame();

        createUI();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeGame() {
        targetNumber = generateRandomNumber(1, 100);
        attemptsLeft = 10; // You can change the number of attempts as per your preference
        roundsPlayed = 0;
        roundsWon = 0;
    }

    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private void createUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel guessLabel = new JLabel("Enter Your Guess:");
        guessField = new JTextField();
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("");
        attemptsLabel = new JLabel("Attempts Left: " + attemptsLeft);
        roundsLabel = new JLabel("Rounds Played: " + roundsPlayed);
        roundsWonLabel = new JLabel("Rounds Won: " + roundsWon);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userGuess;
                try {
                    userGuess = Integer.parseInt(guessField.getText());
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Invalid input. Please enter a number.");
                    return;
                }

                if (userGuess < 1 || userGuess > 100) {
                    feedbackLabel.setText("Please enter a number between 1 and 100.");
                } else {
                    checkGuess(userGuess);
                }
            }
        });

        panel.add(guessLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(feedbackLabel);
        panel.add(attemptsLabel);
        panel.add(roundsLabel);
        panel.add(roundsWonLabel);

        add(panel);
    }

    private void checkGuess(int userGuess) {
        attemptsLeft--;

        if (userGuess == targetNumber) {
            feedbackLabel.setText("Congratulations! You guessed the number.");
            roundsWon++;
            initializeGame();
        } else if (userGuess < targetNumber) {
            feedbackLabel.setText("Too low. Try again.");
        } else {
            feedbackLabel.setText("Too high. Try again.");
        }

        attemptsLabel.setText("Attempts Left: " + attemptsLeft);
        roundsLabel.setText("Rounds Played: " + (++roundsPlayed));
        roundsWonLabel.setText("Rounds Won: " + roundsWon);

        if (attemptsLeft == 0) {
            feedbackLabel.setText("Out of attempts. The number was " + targetNumber + ".");
            initializeGame();
        }

        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGameGUI::new);
    }
}
