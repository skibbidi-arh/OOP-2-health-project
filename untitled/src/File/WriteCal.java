package File;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class WriteCal {
    private static final String FILE_NAME = "calories.txt";

    public void write(int cal) {
        LocalDate currentDate = LocalDate.now();
        Map<String, Integer> calorieData = readExistingData();

        calorieData.put(currentDate.toString(), calorieData.getOrDefault(currentDate.toString(), 0) + cal);

        writeToFile(calorieData);
    }

    private Map<String, Integer> readExistingData() {
        Map<String, Integer> calorieData = new LinkedHashMap<>();
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        calorieData.put(parts[0], Integer.parseInt(parts[1]));
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }

        return calorieData;
    }

    private void writeToFile(Map<String, Integer> calorieData) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Map.Entry<String, Integer> entry : calorieData.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


}

