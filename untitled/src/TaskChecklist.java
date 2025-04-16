import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import File.*;

public class TaskChecklist {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private ArrayList<Boolean> taskStatus;
    private ChecklistFile checklistFile;

    public TaskChecklist() {
        JFrame frame = new JFrame("Task Checklist");
        frame.setSize(500, 500);

        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255));

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskStatus = new ArrayList<>();
        checklistFile = new ChecklistFile();

        checklistFile.loadTasks(taskListModel, taskStatus);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(220, 230, 250));

        JTextField taskField = new JTextField(20);
        taskField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton addButton = new JButton("➕ Add");
        JButton removeButton = new JButton("❌ Remove");
        JButton toggleButton = new JButton("✔ Mark Done");

        customizeButton(addButton);
        customizeButton(removeButton);
        customizeButton(toggleButton);

        panel.add(taskField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(toggleButton);

        frame.add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText().trim();
                if (!task.isEmpty()) {
                    taskListModel.addElement("[ ] " + task);
                    taskStatus.add(false);
                    checklistFile.saveTasks(taskListModel, taskStatus);
                    taskField.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index != -1) {
                    taskListModel.remove(index);
                    taskStatus.remove(index);
                    checklistFile.saveTasks(taskListModel, taskStatus);
                }
            }
        });

        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index != -1) {
                    boolean status = taskStatus.get(index);
                    String task = taskListModel.getElementAt(index).substring(3);
                    taskStatus.set(index, !status);
                    taskListModel.set(index, (status ? "[ ] " : "[✔] ") + task);
                    checklistFile.saveTasks(taskListModel, taskStatus);
                }
            }
        });

        frame.setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    public static void main(String[] args) {

        new TaskChecklist();
    }
}
