package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Main Menu");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);

        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        centrePanel.setBackground(new Color(245, 245, 245));
        calendarButton.setPreferredSize(new Dimension(120, 50));
        calendarButton.setBackground(new Color(70, 130, 180));
        calendarButton.setForeground(Color.BLACK);
        calendarButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new CalendarView();
                    }
        });
        centrePanel.add(calendarButton);

        eventsButton.setPreferredSize(new Dimension(120, 50));
        eventsButton.setBackground(new Color(70, 130, 180));
        eventsButton.setForeground(Color.BLACK);

        centrePanel.add(eventsButton);

        JPanel bottomPanel = new JPanel();
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.BLACK);
        backButton.setPreferredSize(new Dimension(120, 50));
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new LoginAndSignupView();
                    }
                });

        bottomPanel.add(backButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centrePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
