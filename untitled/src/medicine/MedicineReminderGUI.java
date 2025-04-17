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
    private final JComboBox<String> weekdaySelector = new JComboBox<>(
            new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"});

    public MedicineReminderGUI(MedicineService service) {
        this.service = service;

        setTitle("💊 Medicine Reminder");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 10));
        getContentPane().setBackground(new Color(245, 250, 255));

        initStyles();
        updateList();

        JButton addButton = createStyledButton("➕ Add Medicine");
        JButton removeButton = createStyledButton("🗑 Remove Selected");

        addButton.addActionListener(e -> addMedicine());
        removeButton.addActionListener(e -> removeSelected());

        weekdaySelector.addActionListener(e -> updateList());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(245, 250, 255));
        topPanel.add(new JLabel("Filter by Day: "));
        topPanel.add(weekdaySelector);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(245, 250, 255));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        JScrollPane scrollPane = new JScrollPane(medicineList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Your Medicines"));

        add(topPanel, BorderLayout.NORTH);
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
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(0, 105, 230));
            }

            public void mouseExited(MouseEvent evt) {
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

        JPanel panel = new JPanel(new GridLayout(3, 1));
        JTextField timesField = new JTextField();
        JTextField daysField = new JTextField();
        panel.add(new JLabel("Enter times (comma-separated): e.g., 08:00 AM, 02:00 PM"));
        panel.add(timesField);
        panel.add(new JLabel("Enter days (comma-separated): e.g., Monday, Wednesday, Friday"));
        panel.add(daysField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Medicine Details", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        List<String> times = Arrays.asList(timesField.getText().split("\\s*,\\s*"));
        Set<String> days = new HashSet<>(Arrays.asList(daysField.getText().split("\\s*,\\s*")));

        boolean added = service.addMedicine(name, times, days);
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
        String selectedDay = weekdaySelector.getSelectedItem().toString();
        for (Medicine med : service.getAllMedicines()) {
            if (med.getWeekdays().contains(selectedDay)) {
                listModel.addElement( med.toDisplayString());
            }
        }
    }

    public static void main(String[] args) {
        MedicineRepository repository = new MedicineRepository();
        MedicineService service = new MedicineService(repository);
        new MedicineReminderGUI(service).setVisible(true);
    }
}
