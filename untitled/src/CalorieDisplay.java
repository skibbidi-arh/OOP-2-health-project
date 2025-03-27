import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class CalorieDisplay {
    private static final String FILE_NAME = "calories.txt";

    public CalorieDisplay() {
        JFrame frame = new JFrame("Calorie Intake Log");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Calorie Intake Log", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        logArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(logArea);

        String logData = readCalorieData();
        logArea.setText(logData);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private String readCalorieData() {
        StringBuilder data = new StringBuilder();
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        data.append("Date: ").append(parts[0])
                                .append("  Calorie intake: ").append(parts[1]).append(" kcal\n");
                    }
                }
            } catch (IOException e) {
                data.append("Error reading file.");
            }
        } else {
            data.append("No data available.");
        }

        return data.toString();
    }
}
