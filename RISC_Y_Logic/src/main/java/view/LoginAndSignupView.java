package view;

import interface_adapter.login.LoginController;
import interface_adapter.signup.SignupController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.signup.SignupPresenter;
import use_case.LoginInteractor;
import use_case.SignupInteractor;
import data_access.UserRepository;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
    JButton signUpButton = new JButton("Signup");

    JFrame frame = new JFrame("Log in or Sign up");
    public LoginAndSignupView() {

        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        loginLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loginLabel.setBounds(150, 50, 100, 30);

        username.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        username.setBounds(30, 100, 100, 30);

        password.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        password.setBounds(30, 150, 100, 30);

        usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        usernameField.setBounds(100, 100, 150, 30);

        passwordField.setBounds(100, 150, 150, 30);

        loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        loginButton.setBounds(120, 200, 100, 50);
        loginButton.setBackground(new Color(245, 245, 245));
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

        newUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newUsername.setBounds(275, 100, 100, 30);

        newPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newPassword.setBounds(275, 150, 100, 30);

        newUsernameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newUsernameField.setBounds(400, 100, 150, 30);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(400, 150, 150, 30);

        signUpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signUpButton.setBounds(420, 200, 100, 50);
        signUpButton.setBackground(new Color(245, 245, 245));
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
