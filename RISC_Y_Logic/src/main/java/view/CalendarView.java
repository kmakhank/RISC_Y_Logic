package view;

import interface_adapter.add_course_schedule.AddCourseScheduleController;
import entity.CourseSchedule;
import use_case.add_course_schedule.AddCourseScheduleDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class CalendarView {

    private final AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface;
    private final AddCourseScheduleController addCourseScheduleController;

    private final Calendar calendar = Calendar.getInstance();
    private final int[] year = {calendar.get(Calendar.YEAR)};
    private final int[] month = {calendar.get(Calendar.MONTH)};
    private final JFrame frame = new JFrame("Calendar Page");
    private final JPanel headerPanel = new JPanel();
    private final JLabel monthLabel = new JLabel();
    private final JPanel calendarPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JButton previousButton = new JButton("<");
    private final JButton nextButton = new JButton(">");
    JButton addEventButton = new StyleButton("Add Event");
    JButton addCourseScheduleButton = new StyleButton("Add Course Schedule");

    public CalendarView(AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface, AddCourseScheduleController addCourseScheduleController) {
        this.addCourseScheduleDataAccessInterface = addCourseScheduleDataAccessInterface;
        this.addCourseScheduleController = addCourseScheduleController;

        initializeUI();
    }

    private void initializeUI() {
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());

        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        monthLabel.setHorizontalAlignment(JLabel.CENTER);
        monthLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        monthLabel.setForeground(Color.WHITE);

        previousButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        previousButton.setBackground(Color.WHITE);
        previousButton.setFocusPainted(false);
        previousButton.addActionListener(e -> {
            month[0] = month[0] - 1;
            if (month[0] < 0) {
                month[0] = 11;
                year[0] = year[0] - 1;
            }
            updateCalendar(year[0], month[0]);
        });

        nextButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        nextButton.setBackground(new Color(255, 255, 255));
        nextButton.setFocusPainted(false);
        nextButton.addActionListener(e -> {
            month[0] = month[0] + 1;
            if (month[0] >= 12) {
                month[0] = 0;
                year[0] = year[0] + 1;
            }
            updateCalendar(year[0], month[0]);
        });

        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(previousButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        calendarPanel.setLayout(new GridLayout(0, 7));

        calendarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addEventButton.addActionListener(e -> new AddUserEventView());

        addCourseScheduleButton.addActionListener(e -> new AddCourseScheduleView(addCourseScheduleDataAccessInterface, addCourseScheduleController));

        rightPanel.setLayout(new GridLayout(2, 1, 5, 5));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightPanel.add(addEventButton);
        rightPanel.add(addCourseScheduleButton);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(calendarPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        updateCalendar(year[0], month[0]);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateCalendar(int year, int month) {
        calendarPanel.removeAll();

        Calendar calendar = new GregorianCalendar(year, month, 1);
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthLabel.setText(monthNames[month] + " " + year);

        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (String day : daysOfWeek) {
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
                    Map<LocalDate, List<CourseSchedule>> dateToScheduleMap = addCourseScheduleDataAccessInterface.getDayToScheduleMap();
                    List<CourseSchedule> schedules = dateToScheduleMap.get(currentDate);
                    if (schedules != null && !schedules.isEmpty()) {
                        StringBuilder message = new StringBuilder();
                        message.append("Courses and Events on ").append(currentDate).append(":\n");
                        for (CourseSchedule schedule : schedules) {
                            message.append("Course Name: ").append(schedule.getInstanceName()).append("\n");
                            schedule.getInstanceDateAndTimeSlot().get(currentDate).forEach(slot -> {
                                message.append("Start Time: ").append(slot.getStartTime())
                                        .append(", End Time: ").append(slot.getEndTime()).append("\n");
                            });
                        }
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
}
