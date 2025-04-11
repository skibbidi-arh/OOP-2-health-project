package medicine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class MedicineReminderGUI extends JFrame {
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> medicineList = new JList<>(listModel);
    private final MedicineService service;

    public MedicineReminderGUI(MedicineService service) {
        this.service = service;

        setTitle("💊 Medicine Reminder");
        setSize(600, 450);

        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 10));
        getContentPane().setBackground(new Color(245, 250, 255));

        initStyles();
        updateList();

        // Create and style buttons
        JButton addButton = createStyledButton("➕ Add Medicine");
        JButton removeButton = createStyledButton("🗑 Remove Selected");

        addButton.addActionListener(e -> addMedicine());
        removeButton.addActionListener(e -> removeSelected());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(245, 250, 255));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        JScrollPane scrollPane = new JScrollPane(medicineList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Your Medicines"));

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initStyles() {
        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        medicineList.setFont(font);
        medicineList.setSelectionBackground(new Color(0, 120, 215));
        medicineList.setSelectionForeground(Color.WHITE);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 80, 180)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 105, 230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255));
            }
        });
        return button;
    }

    private void addMedicine() {
        String name = JOptionPane.showInputDialog(this, "Enter medicine name:");
        if (name == null || name.trim().isEmpty()) return;

        if (service.findMedicineByName(name) != null) {
            JOptionPane.showMessageDialog(this, "Medicine already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String timesInput = JOptionPane.showInputDialog(this, "Enter times (comma-separated):\ne.g., 08:00 AM, 02:00 PM");
        if (timesInput == null || timesInput.trim().isEmpty()) return;

        List<String> times = Arrays.asList(timesInput.split("\\s*,\\s*"));
        boolean added = service.addMedicine(name, times);
        if (added) updateList();
    }

    private void removeSelected() {
        int index = medicineList.getSelectedIndex();
        if (index >= 0 && service.removeMedicine(index)) {
            updateList();
        }
    }

    private void updateList() {
        listModel.clear();
        for (Medicine med : service.getAllMedicines()) {
            listModel.addElement("💊 " + med.toDisplayString());
        }
    }

    public static void main(String[] args) {

            MedicineRepository repository = new MedicineRepository();
            MedicineService service = new MedicineService(repository);
            new MedicineReminderGUI(service).setVisible(true);

    }
}
