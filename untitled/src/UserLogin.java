import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLogin {

     static final String CSV_FILE = "users.csv";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public LoginFrame() {
        setTitle("User Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        messageLabel = new JLabel("", SwingConstants.CENTER);

        add(loginButton);
        add(registerButton);
        add(messageLabel);

        loginButton.addActionListener(e -> authenticateUser());
        registerButton.addActionListener(e -> registerUser());

        setVisible(true);
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (BufferedReader br = new BufferedReader(new FileReader(UserLogin.CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    messageLabel.setText("Login successful!");
                    return;
                }
            }
        } catch (IOException e) {
            messageLabel.setText("Error reading file");
        }
        messageLabel.setText("Invalid username or password.");
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Fields cannot be empty");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(UserLogin.CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username)) {
                    messageLabel.setText("Username already exists");
                    return;
                }
            }
        } catch (IOException e) {
            messageLabel.setText("Error reading file");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(UserLogin.CSV_FILE, true))) {
            bw.write(username + "," + password);
            bw.newLine();
            messageLabel.setText("User registered successfully!");
        } catch (IOException e) {
            messageLabel.setText("Error writing to file");
        }
    }
}