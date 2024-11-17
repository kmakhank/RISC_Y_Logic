package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAndSignupView {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Log in or Sign up");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loginLabel.setBounds(150, 50, 100, 30);

        JLabel signUpLabel = new JLabel("Signup");
        signUpLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signUpLabel.setBounds(400, 50, 100, 30);

        JLabel username = new JLabel("Username:");
        username.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        username.setBounds(30, 100, 100, 30);

        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        password.setBounds(30, 150, 100, 30);

        JLabel newUsername = new JLabel("New Username:");
        newUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newUsername.setBounds(275, 100, 100, 30);

        JLabel newPassword = new JLabel("New Password:");
        newPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newPassword.setBounds(275, 150, 100, 30);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        usernameField.setBounds(100, 100, 150, 30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 150, 150, 30);

        JTextField newUsernameField = new JTextField();
        newUsernameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newUsernameField.setBounds(400, 100, 150, 30);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(400, 150, 150, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loginButton.setBounds(120, 200, 100, 50);

        JButton signUpButton = new JButton("Signup");
        signUpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signUpButton.setBounds(420, 200, 100, 50);

        frame.add(loginLabel);
        frame.add(signUpLabel);
        frame.add(username);
        frame.add(password);
        frame.add(newUsername);
        frame.add(newPassword);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(newUsernameField);
        frame.add(newPasswordField);
        frame.add(loginButton);
        frame.add(signUpButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
