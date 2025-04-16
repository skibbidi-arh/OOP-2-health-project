import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import medicine.*;
import Checklist.*;
import Gemini.*;

public class maintGUI {
    public void show() {
        JFrame frame = new JFrame("Health Project");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Health Project", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 150, 243));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBackground(new Color(220, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btn1 = new JButton("BMI Calculator");
        JButton btn2 = new JButton("Check List");
        JButton btn3 = new JButton("Exercise Timer");
        JButton btn4 = new JButton("Chatbot");
        JButton btn5 = new JButton("Calorie Tracker");
        JButton btn6 = new JButton("Medicine List");
        JButton btn7 = new JButton("Sleep Tracker");

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        btn1.setFont(buttonFont);
        btn2.setFont(buttonFont);
        btn3.setFont(buttonFont);
        btn4.setFont(buttonFont);
        btn5.setFont(buttonFont);
        btn6.setFont(buttonFont);
        btn7.setFont(buttonFont);

        btn1.setBackground(new Color(76, 175, 80));
        btn1.setForeground(Color.WHITE);
        btn2.setBackground(new Color(255, 152, 0));
        btn2.setForeground(Color.WHITE);
        btn3.setBackground(new Color(211, 35, 20));
        btn3.setForeground(Color.WHITE);
        btn4.setBackground(new Color(221, 149, 143));
        btn4.setForeground(Color.WHITE);
        btn5.setBackground(new Color(33, 150, 243));
        btn5.setForeground(Color.WHITE);
        btn6.setBackground(new Color(156, 39, 176));
        btn6.setForeground(Color.WHITE);
        btn7.setBackground(new Color(255, 87, 34));
        btn7.setForeground(Color.WHITE);

        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);
        //panel.add(btn7);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BMIcalculator cal = new BMIcalculator();
                cal.calculateBMI();
            }
        });

        btn2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                CheckList ck = new CheckList();
                ck.lst();
            }
        });

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Stopwatch stopwatch = new Stopwatch();
            }
        });


        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GeminiGUI g = new GeminiGUI();
                g.chat();
            }
        });

        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalorieTracker cr = new CalorieTracker();

            }
        });


        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MedicineRepository repository = new MedicineRepository();
                MedicineService service = new MedicineService(repository);
                new MedicineReminderGUI(service).setVisible(true);

            }
        });
    }
}
