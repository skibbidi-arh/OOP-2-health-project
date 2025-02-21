import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMIcalculator {
    private double bmi;  // Instance variable to store BMI value

    public void calculateBMI() {
        // Create the frame
        JFrame frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setLayout(new GridLayout(5, 2));

        // Create components
        JLabel heightFeetLabel = new JLabel("Height (feet):");
        JTextField heightFeetField = new JTextField();
        JLabel heightInchesLabel = new JLabel("Height (inches):");
        JTextField heightInchesField = new JTextField();
        JLabel weightLabel = new JLabel("Weight (kg):");
        JTextField weightField = new JTextField();
        JButton calculateButton = new JButton("Calculate BMI");
        JLabel resultLabel = new JLabel("Your BMI: ");
        JButton showpdf = new JButton("Get diet and exercise suggestions");

        // Add components to frame
        frame.add(heightFeetLabel);
        frame.add(heightFeetField);
        frame.add(heightInchesLabel);
        frame.add(heightInchesField);
        frame.add(weightLabel);
        frame.add(weightField);
        frame.add(calculateButton);
        frame.add(resultLabel);
        frame.add(showpdf);

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
