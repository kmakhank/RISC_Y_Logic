package view;

import interface_adapter.login.LoginController;
import interface_adapter.signup.SignupController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.signup.SignupPresenter;
import use_case.login.LoginInteractor;
import use_case.signup.SignupInteractor;
import data_access.UserRepository;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAndSignupView {
    UserRepository userRepository = new UserRepository();
    LoginInteractor loginInteractor = new LoginInteractor(userRepository);
    SignupInteractor signupInteractor = new SignupInteractor(userRepository);
    LoginController loginController = new LoginController(loginInteractor);
    SignupController signupController = new SignupController(signupInteractor);
    LoginPresenter loginPresenter = new LoginPresenter();
    SignupPresenter signupPresenter = new SignupPresenter();
    JLabel loginLabel = new JLabel("Login");
    JLabel username = new JLabel("Username:");
    JLabel password = new JLabel("Password:");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JLabel signUpLabel = new JLabel("Signup");
    JLabel newUsername = new JLabel("New Username:");
    JLabel newPassword = new JLabel("New Password:");
    JTextField newUsernameField = new JTextField();
    JPasswordField newPasswordField = new JPasswordField();
    JButton signUpButton = new JButton("Signup");
    JPanel panel = new JPanel();

    JFrame frame = new JFrame("Log in or Sign up");
    public LoginAndSignupView() {

        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));
        panel.setPreferredSize(new Dimension(600, 300));

        loginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loginLabel.setBounds(150, 50, 100, 30);
        loginLabel.setForeground(new Color(50, 50, 50));

        username.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        username.setBounds(30, 100, 100, 30);
        username.setForeground(new Color(50, 50, 50));

        password.setBounds(30, 150, 100, 30);
        password.setForeground(new Color(50, 50, 50));

        usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        usernameField.setBounds(100, 100, 150, 30);
        usernameField.setBackground(Color.WHITE);
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordField.setBounds(100, 150, 150, 30);
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loginButton.setBounds(120, 200, 100, 50);
        loginButton.setForeground(new Color(70, 130, 180));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String loginUsername = usernameField.getText();
                        String loginPassword = passwordField.getText();
                        if (loginController.login(loginUsername, loginPassword)) {
                            JOptionPane.showMessageDialog(frame, loginPresenter.prepareSuccessMessage());
                            frame.dispose();
                            new MainMenuView();
                        } else {
                            JOptionPane.showMessageDialog(frame, loginPresenter.prepareFailureMessage());
                        }
                    }
                });

        signUpLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signUpLabel.setBounds(400, 50, 100, 30);
        signUpLabel.setForeground(new Color(50, 50, 50));

        newUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newUsername.setBounds(275, 100, 100, 30);
        newUsername.setForeground(new Color(50, 50, 50));

        newPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newPassword.setBounds(275, 150, 100, 30);
        newPassword.setForeground(new Color(50, 50, 50));

        newUsernameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newUsernameField.setBounds(400, 100, 150, 30);
        newUsernameField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        newPasswordField.setBounds(400, 150, 150, 30);
        newPasswordField.setBackground(Color.WHITE);
        newPasswordField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        signUpButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        signUpButton.setBounds(420, 200, 100, 50);
        signUpButton.setForeground(new Color(70, 130, 180));

        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String signupUsername = newUsernameField.getText();
                        String signupPassword = newPasswordField.getText();
                        if (signupController.signup(signupUsername, signupPassword)) {
                            JOptionPane.showMessageDialog(frame, signupPresenter.prepareSuccessMessage());
                            frame.dispose();
                            new MainMenuView();
                        } else {
                            JOptionPane.showMessageDialog(frame, signupPresenter.prepareFailureMessage());
                        }
                    }
        });

        panel.add(loginLabel);
        panel.add(signUpLabel);
        panel.add(username);
        panel.add(password);
        panel.add(newUsername);
        panel.add(newPassword);
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(newUsernameField);
        panel.add(newPasswordField);
        panel.add(loginButton);
        panel.add(signUpButton);

        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
