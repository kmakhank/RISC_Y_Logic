package view;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarView {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        final int[] year = {calendar.get(Calendar.YEAR)};
        final int[] month = {calendar.get(Calendar.MONTH)};

        JFrame frame = new JFrame("Calendar Page");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        JButton previousButton = new JButton("<");
        JButton nextButton = new JButton(">");
        JButton addEventButton = new JButton("Add Event");
        JButton addCourseScheduleButton = new JButton("Add Course Schedule");
        JButton backButton = new JButton("Back");

        JLabel monthLabel = new JLabel();
        monthLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7));

        previousButton.addActionListener(e -> {
            month[0] = month[0] - 1;
            if (month[0] < 0) {
                month[0] = 11;
                year[0] = year[0] - 1;
            }
            updateCalendar(year[0], month[0], monthLabel, calendarPanel);
        });

        nextButton.addActionListener(e -> {
            month[0] = month[0] + 1;
            if (month[0] >= 12) {
                month[0] = 0;
                year[0] = year[0] + 1;
            }
            updateCalendar(year[0], month[0], monthLabel, calendarPanel);
        });

        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(previousButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1, 5, 5));
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

    private static void updateCalendar(int year, int month, JLabel monthLabel, JPanel calendarPanel) {
        calendarPanel.removeAll();

        Calendar calendar = new GregorianCalendar(year, month, 1);
        String monthName[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthLabel.setText(monthName[month] + " " + year);

        String days[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
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
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            calendarPanel.add(dayLabel);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
}
