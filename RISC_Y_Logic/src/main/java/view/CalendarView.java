package view;

import entity.CourseSchedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class CalendarView {

    Calendar calendar = Calendar.getInstance();
    final int[] year = {calendar.get(Calendar.YEAR)};
    final int[] month = {calendar.get(Calendar.MONTH)};
    JFrame frame = new JFrame("Calendar Page");
    JPanel headerPanel = new JPanel();
    JLabel monthLabel = new JLabel();
    JPanel calendarPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JButton previousButton = new JButton("<");
    JButton nextButton = new JButton(">");
    JButton addEventButton = new StyleButton("Add Event");
    JButton addCourseScheduleButton = new StyleButton("Add Course Schedule");

    Map<LocalDate, List<CourseSchedule>> dateToScheduleMap = new HashMap<>();
    Map<String, CourseSchedule> courseSchedules = new HashMap<>();

    public CalendarView() {

        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());

        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        monthLabel.setHorizontalAlignment(JLabel.CENTER);
        monthLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        monthLabel.setForeground(Color.WHITE);

        calendarPanel.setLayout(new GridLayout(0, 7));
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        previousButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        previousButton.setBackground(new Color(255, 255, 255));
        previousButton.setFocusPainted(false);
        previousButton.addActionListener(
                e -> {
                    month[0] = month[0] - 1;
                    if (month[0] < 0) {
                        month[0] = 11;
                        year[0] = year[0] - 1;
                    }
                    updateCalendar(year[0], month[0], monthLabel, calendarPanel, dateToScheduleMap);
                });

        nextButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        nextButton.setBackground(new Color(255, 255, 255));
        nextButton.setFocusPainted(false);
        nextButton.addActionListener(
                e -> {
                    month[0] = month[0] + 1;
                    if (month[0] >= 12) {
                        month[0] = 0;
                        year[0] = year[0] + 1;
                    }
                    updateCalendar(year[0], month[0], monthLabel, calendarPanel, dateToScheduleMap);
                });

        addEventButton.addActionListener(e -> new AddUserEventView());

        addCourseScheduleButton.addActionListener(e -> new AddCourseScheduleView(dateToScheduleMap, courseSchedules));

        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(previousButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        rightPanel.setLayout(new GridLayout(2, 1, 5, 5));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightPanel.add(addEventButton);
        rightPanel.add(addCourseScheduleButton);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(calendarPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        updateCalendar(year[0], month[0], monthLabel, calendarPanel, dateToScheduleMap);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void updateCalendar(int year, int month, JLabel monthLabel, JPanel calendarPanel, Map<LocalDate, List<CourseSchedule>> dateToScheduleMap) {
        calendarPanel.removeAll();
        Calendar calendar = new GregorianCalendar(year, month, 1);

        String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthLabel.setText(monthName[month] + " " + year);

        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
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
            LocalDate currentDate = LocalDate.of(year, month + 1, day);
            JLabel dayLabel = new JLabel(String.valueOf(day), JLabel.CENTER);
            dayLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            if ((startDay + day - 2) % 7 == 0 || (startDay + day - 2) % 7 == 6) {
                dayLabel.setOpaque(true);
                dayLabel.setBackground(new Color(255, 228, 225));
            }

            dayLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    List<CourseSchedule> schedules = dateToScheduleMap.get(currentDate);
                    if (schedules != null && !schedules.isEmpty()) {
                        StringBuilder message = new StringBuilder();
                        message.append("Courses and Events on ").append(currentDate).append(":\n");
                        schedules.forEach(schedule -> {
                            message.append("Course Name: ").append(schedule.getInstanceName()).append("\n");
                            schedule.getInstanceDateAndTimeSlot().get(currentDate).forEach(slot -> {
                                message.append("Start Time: ").append(slot.getStartTime())
                                        .append(", End Time: ").append(slot.getEndTime()).append("\n");
                            });
                        });
                        JOptionPane.showMessageDialog(dayLabel, message.toString());
                    } else {
                        JOptionPane.showMessageDialog(dayLabel, "No courses or events on this date.");
                    }
                }
            });

            calendarPanel.add(dayLabel);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    public static void main(String[] args) {
        new CalendarView();
    }
}