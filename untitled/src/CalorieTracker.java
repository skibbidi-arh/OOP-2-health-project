import File.WriteCal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalorieTracker {
    WriteCal wr = new WriteCal();
    private JFrame frame;
    private JTextField foodField;
    private JTextField calorieField;
    private JTextArea logArea;
    private JLabel totalCaloriesLabel;
    private static int totalCalories = 0;
    private ArrayList<String> foodLog = new ArrayList<>();
    LocalDate dt = LocalDate.now();

    public CalorieTracker() {
        frame = new JFrame("Calorie Tracker");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Calorie Tracker", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel foodLabel = new JLabel("Food Item:");
        foodField = new JTextField(15);
        JLabel calorieLabel = new JLabel("Calories:");
        calorieField = new JTextField(5);
        JButton addButton = new JButton("Add");
        JButton showButton = new JButton("Show Previous Records");

        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);
        showButton.setBackground(new Color(33, 150, 243));
        showButton.setForeground(Color.WHITE);

        inputPanel.add(foodLabel);
        inputPanel.add(foodField);
        inputPanel.add(calorieLabel);
        inputPanel.add(calorieField);
        inputPanel.add(addButton);
        inputPanel.add(showButton);

        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        logArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(logArea);
        totalCaloriesLabel = new JLabel("Total Calories: 0", JLabel.CENTER);
        totalCaloriesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalCaloriesLabel.setForeground(Color.RED);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCalorieEntry();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalorieDisplay();
            }
        });

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        frame.add(totalCaloriesLabel, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }

    private void addCalorieEntry() {
        String food = foodField.getText();
        String calorieText = calorieField.getText();

        if (food.isEmpty() || calorieText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both food and calorie values.");
            return;
        }

        try {
            int calories = Integer.parseInt(calorieText);
            wr.write(calories);
            totalCalories += calories;

            foodLog.add(food + " - " + calories + " kcal");
            updateLog();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number for calories.");
        }
    }

    private void updateLog() {
        logArea.setText(String.join("\n", foodLog));
        totalCaloriesLabel.setText("Total Calories: " + totalCalories);
        foodField.setText("");
        calorieField.setText("");
    }

    public static void main(String[] args) {
        new CalorieTracker();
        LocalDate dat = LocalDate.now();
        System.out.println(dat);
    }
}
