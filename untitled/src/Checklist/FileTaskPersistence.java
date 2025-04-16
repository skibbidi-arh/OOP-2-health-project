package Checklist;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTaskPersistence implements TaskPersistence {
    private static final String FILE_NAME = "tasks.txt";

    @Override
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean completed = line.startsWith("[✔]");
                String description = line.substring(4);
                tasks.add(new Task(description, completed));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    @Override
    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toDisplayString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving tasks: " + e.getMessage());
        }
    }
}
