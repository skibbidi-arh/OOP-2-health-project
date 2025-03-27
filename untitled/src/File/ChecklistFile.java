package File;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.ArrayList;
import File.*;

public class ChecklistFile {
    private static final String FILE_NAME = "tasks.txt";

    public void saveTasks(DefaultListModel<String> taskListModel, ArrayList<Boolean> taskStatus) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < taskListModel.getSize(); i++) {
                writer.write((taskStatus.get(i) ? "1," : "0,") + taskListModel.getElementAt(i).substring(3));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTasks(DefaultListModel<String> taskListModel, ArrayList<Boolean> taskStatus) {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", 2);
                    if (parts.length == 2) {
                        boolean status = parts[0].equals("1");
                        taskStatus.add(status);
                        taskListModel.addElement((status ? "[✔] " : "[ ] ") + parts[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

