
package Checklist;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskChecklistUI {
    private final TaskManager taskManager;
    private final DefaultListModel<String> taskListModel;
    private final JList<String> taskList;
    private final JTextField taskField;

    public TaskChecklistUI(TaskManager taskManager) {
        this.taskManager = taskManager;
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskField = new JTextField(20);

        createUI();
        loadTasks();
    }

    private void createUI() {
        JFrame frame = new JFrame("Task Checklist");
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255));

        taskList.setFont(new Font("Arial", Font.PLAIN, 16));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(220, 230, 250));

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

        addButton.addActionListener(e -> addTask());
        removeButton.addActionListener(e -> removeTask());
        toggleButton.addActionListener(e -> toggleTask());

        frame.setVisible(true);
    }

    private void loadTasks() {
        for (Task task : taskManager.getTasks()) {
            taskListModel.addElement(task.toDisplayString());
        }
    }

    private void addTask() {
        String text = taskField.getText().trim();
        if (!text.isEmpty()) {
            taskManager.addTask(text);
            taskListModel.addElement("[ ] " + text);
            taskField.setText("");
        }
    }

    private void removeTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskManager.removeTask(index);
            taskListModel.remove(index);
        }
    }

    private void toggleTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskManager.toggleTask(index);
            taskListModel.set(index, taskManager.getTasks().get(index).toDisplayString());
        }
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }
}
