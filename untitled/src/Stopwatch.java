import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch implements ActionListener {
    JFrame frame = new JFrame("Stopwatch");
    JLabel timeLabel = new JLabel();
    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");
    JButton getButton = new JButton("Record");
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            updateLabel();
        }
    });

    public Stopwatch() {
        frame.setSize(400, 300);

        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);


        timeLabel.setText(formatTime());
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        getButton.setFont(new Font("Arial", Font.BOLD, 16));

        startButton.setBackground(new Color(76, 175, 80));
        startButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(244, 67, 54));
        resetButton.setForeground(Color.WHITE);
        getButton.setBackground(new Color(33, 150, 243));
        getButton.setForeground(Color.WHITE);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(getButton);

        startButton.addActionListener(this);
        resetButton.addActionListener(this);
        getButton.addActionListener(this);

        frame.add(timeLabel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        updateLabel();
    }

    void updateLabel() {
        timeLabel.setText(formatTime());
    }

    String formatTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!started) {
                started = true;
                startButton.setText("Stop");
                start();
            } else {
                started = false;
                startButton.setText("Start");
                stop();
            }
        } else if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("Start");
            reset();
        } else if (e.getSource() == getButton) {
            System.out.println("Elapsed Time: " + formatTime());
        }
    }

}
