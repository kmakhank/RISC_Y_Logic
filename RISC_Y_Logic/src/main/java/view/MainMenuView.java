package view;

import data_access.InMemoryScheduleRepository;
import interface_adapter.add_course_schedule.AddCourseScheduleController;
import interface_adapter.add_course_schedule.AddCourseSchedulePresenter;
import interface_adapter.mainmenu.MainMenuBackButtonController;
import interface_adapter.mainmenu.MainMenuCalendarController;
import interface_adapter.mainmenu.MainMenuEventsController;
import use_case.add_course_schedule.AddCourseScheduleInteractor;

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

        InMemoryScheduleRepository repository = new InMemoryScheduleRepository();
        AddCourseSchedulePresenter presenter = new AddCourseSchedulePresenter();
        AddCourseScheduleInteractor interactor = new AddCourseScheduleInteractor(repository, presenter);
        AddCourseScheduleController courseScheduleController = new AddCourseScheduleController(interactor);


        MainMenuCalendarController calendarController = new MainMenuCalendarController(repository, courseScheduleController);

        calendarButton.setPreferredSize(new Dimension(120, 50));
        calendarButton.setBackground(new Color(70, 130, 180));
        calendarButton.setForeground(Color.BLACK);
        calendarButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {calendarController.click();
                    }
                });

        centrePanel.add(calendarButton);

        MainMenuEventsController mainMenuEventsController = new MainMenuEventsController();
        eventsButton.setPreferredSize(new Dimension(120, 50));
        eventsButton.setBackground(new Color(70, 130, 180));
        eventsButton.setForeground(Color.BLACK);
        eventsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mainMenuEventsController.click();
                    }
                });

        centrePanel.add(eventsButton);

        JPanel bottomPanel = new JPanel();

        MainMenuBackButtonController mainMenuBackButtonController = new MainMenuBackButtonController();
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.BLACK);
        backButton.setPreferredSize(new Dimension(120, 50));
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mainMenuBackButtonController.click();
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
