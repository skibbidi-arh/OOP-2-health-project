package Gemini;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeminiGUI {
    public void chat() {

        JFrame frame = new JFrame("Gemini Chat");
        frame.setSize(600, 400);

        frame.setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 248, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setContentPane(panel);


        JLabel promptLabel = new JLabel("Enter your prompt:");
        promptLabel.setBounds(20, 20, 200, 25);
        promptLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(promptLabel);


        JTextField promptField = new JTextField();
        promptField.setBounds(20, 50, 540, 35);
        promptField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        promptField.setBorder(new LineBorder(Color.GRAY, 1, true));
        panel.add(promptField);


        JButton sendButton = new JButton("Ask Gemini");
        sendButton.setBounds(430, 95, 130, 35);
        sendButton.setFocusPainted(false);
        sendButton.setBackground(new Color(0, 120, 215));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(sendButton);


        JTextArea responseArea = new JTextArea();
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        responseArea.setEditable(false);
        responseArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        responseArea.setBorder(new LineBorder(Color.GRAY, 1, true));
        responseArea.setBackground(Color.WHITE);


        JScrollPane scrollPane = new JScrollPane(responseArea);
        scrollPane.setBounds(20, 145, 540, 190);
        scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        panel.add(scrollPane);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prompt = promptField.getText().trim();
                if (!prompt.isEmpty()) {
                    responseArea.setText("Thinking...");
                    SwingUtilities.invokeLater(() -> {
                        String response = GeminiApi.getResponseFromGemini(prompt);
                        responseArea.setText(response);
                    });
                } else {
                    responseArea.setText("Please enter a prompt.");
                }
            }
        });


        frame.setVisible(true);
    }
}
