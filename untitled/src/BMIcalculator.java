import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMIcalculator {
    private double bmi;  // Instance variable to store BMI value

    public void calculateBMI() {

        JFrame frame = new JFrame("BMI Calculator");

        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);


        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel heightFeetLabel = new JLabel("Height (feet):");
        JTextField heightFeetField = new JTextField();
        JLabel heightInchesLabel = new JLabel("Height (inches):");
        JTextField heightInchesField = new JTextField();
        JLabel weightLabel = new JLabel("Weight (kg):");
        JTextField weightField = new JTextField();
        JButton calculateButton = new JButton("Calculate BMI");
        JLabel resultLabel = new JLabel("Your BMI: ");
        JButton showpdf = new JButton("Get diet and exercise suggestions");

        // Style components
        Font font = new Font("Arial", Font.BOLD, 14);
        heightFeetLabel.setFont(font);
        heightInchesLabel.setFont(font);
        weightLabel.setFont(font);
        resultLabel.setFont(font);

        calculateButton.setBackground(new Color(76, 175, 80));
        calculateButton.setForeground(Color.WHITE);
        showpdf.setBackground(new Color(33, 150, 243));
        showpdf.setForeground(Color.WHITE);

        // Add components to input panel
        inputPanel.add(heightFeetLabel);
        inputPanel.add(heightFeetField);
        inputPanel.add(heightInchesLabel);
        inputPanel.add(heightInchesField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(calculateButton);
        inputPanel.add(resultLabel);

        // Create a panel for the suggestion button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showpdf);

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double heightFeet = Double.parseDouble(heightFeetField.getText());
                    double heightInches = Double.parseDouble(heightInchesField.getText());
                    double weightKg = Double.parseDouble(weightField.getText());

                    double heightMeters = (heightFeet * 0.3048) + (heightInches * 0.0254);
                    bmi = weightKg / (heightMeters * heightMeters);  // Store BMI in instance variable

                    resultLabel.setText(String.format("Your BMI: %.2f", bmi));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showpdf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DietSuggestions ds = new DietSuggestions();
                ds.pdf(bmi);
            }
        });

        frame.setVisible(true);
    }

    // Method to retrieve the stored BMI value
    public double getBMI() {
        return bmi;
    }
}
