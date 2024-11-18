package view;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView {
    JFrame frame = new JFrame("Main Menu");
    JButton calendarButton = new JButton("Calendar");
    JButton eventsButton = new JButton("Events");
    JButton backButton = new JButton("Back");

    public MainMenuView() {
        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        calendarButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        calendarButton.setBounds(25, 50, 150, 80);
        calendarButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new CalendarView();
                    }
        });

        eventsButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        eventsButton.setBounds(175, 50, 150, 80);

        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        backButton.setBounds(105, 150, 150, 80);
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new LoginAndSignupView();
                    }
                });

        frame.add(calendarButton);
        frame.add(eventsButton);
        frame.add(backButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
