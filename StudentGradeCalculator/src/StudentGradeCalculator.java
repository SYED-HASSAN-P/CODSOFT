import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class StudentGradeCalculator extends JFrame {
    private JTextField[] marksFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public StudentGradeCalculator(int numSubjects) {
        setTitle("Student Grade Calculator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(numSubjects);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUI(int numSubjects) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(numSubjects + 3, 2));

        marksFields = new JTextField[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            JLabel subjectLabel = new JLabel("Subject " + (i + 1) + " Marks:");
            marksFields[i] = new JTextField();
            panel.add(subjectLabel);
            panel.add(marksFields[i]);
        }

        JButton calculateButton = new JButton("Calculate");
        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });

        panel.add(calculateButton);
        panel.add(new JLabel()); // Empty cell
        panel.add(totalMarksLabel);
        panel.add(new JLabel()); // Empty cell
        panel.add(averagePercentageLabel);
        panel.add(new JLabel()); // Empty cell
        panel.add(gradeLabel);

        add(panel);
    }

    private void calculateResults() {
        int totalMarks = 0;
        int numSubjects = marksFields.length;

        for (int i = 0; i < numSubjects; i++) {
            try {
                int marks = Integer.parseInt(marksFields[i].getText());
                totalMarks += marks;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid marks for all subjects.");
                return;
            }
        }

        double averagePercentage = (double) totalMarks / numSubjects;

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + new DecimalFormat("0.00").format(averagePercentage) + "%");

        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else {
            grade = "F";
        }

        gradeLabel.setText("Grade: " + grade);
    }

    public static void main(String[] args) {
        int numSubjects = 5; // You can change the number of subjects as needed
        SwingUtilities.invokeLater(() -> new StudentGradeCalculator(numSubjects));
    }
}
