package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarView {

    Calendar calendar = Calendar.getInstance();
    final int[] year = {calendar.get(Calendar.YEAR)};
    final int[] month = {calendar.get(Calendar.MONTH)};
    JFrame frame = new JFrame("Calendar Page");
    JPanel headerPanel = new JPanel();
    JLabel monthLabel = new JLabel();
    JPanel calendarPanel = new JPanel();
    JButton previousButton = new JButton("<");
    JButton nextButton = new JButton(">");
    JButton addEventButton = new JButton("Add Event");
    JButton addCourseScheduleButton = new JButton("Add Course Schedule");
    JButton backButton = new JButton("Back");

    public CalendarView() {

        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置边距

        monthLabel.setHorizontalAlignment(JLabel.CENTER);
        monthLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        monthLabel.setForeground(Color.WHITE);

        calendarPanel.setLayout(new GridLayout(0, 7));
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        previousButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        previousButton.setBackground(new Color(255, 255, 255));
        previousButton.setFocusPainted(false);
        previousButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        month[0] = month[0] - 1;
                        if (month[0] < 0) {
                            month[0] = 11;
                            year[0] = year[0] - 1;
                        }
                        updateCalendar(year[0], month[0], monthLabel, calendarPanel);}
        });

        nextButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        nextButton.setBackground(new Color(255, 255, 255));
        nextButton.setFocusPainted(false);
        nextButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        month[0] = month[0] + 1;
                        if (month[0] >= 12) {
                            month[0] = 0;
                            year[0] = year[0] + 1;
                        }
                        updateCalendar(year[0], month[0], monthLabel, calendarPanel);}
                });

        styleButton(addEventButton);
        addEventButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );

        styleButton(addEventButton);
        addCourseScheduleButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );

        styleButton(addEventButton);

        headerPanel.setLayout(new BorderLayout());

        headerPanel.add(previousButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1, 5, 5));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightPanel.add(addEventButton);
        rightPanel.add(addCourseScheduleButton);
        rightPanel.add(backButton);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(calendarPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        updateCalendar(year[0], month[0], monthLabel, calendarPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setBackground(new Color(173, 216, 230));
        button.setFocusPainted(false);
    }

    private static void updateCalendar(int year, int month, JLabel monthLabel, JPanel calendarPanel) {
        calendarPanel.removeAll();

        Calendar calendar = new GregorianCalendar(year, month, 1);
        String monthName[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthLabel.setText(monthName[month] + " " + year);

        String days[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            dayLabel.setForeground(new Color(70, 132, 180));
            calendarPanel.add(dayLabel);
        }

        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i < startDay; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= numberOfDays; day++) {
            String key = String.valueOf(day);
            JLabel dayLabel = new JLabel(key, JLabel.CENTER);
            dayLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            if ((startDay + day - 2) % 7 == 0 || (startDay + day - 2) % 7 == 6) {
                dayLabel.setOpaque(true);
                dayLabel.setBackground(new Color(255, 228, 225));
            }

            calendarPanel.add(dayLabel);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
}
