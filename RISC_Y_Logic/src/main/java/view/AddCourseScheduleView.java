package view;

import entity.TimeSlot;
import entity.CourseSchedule;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;

public class AddCourseScheduleView {
    JFrame frame = new JFrame("Add Course Schedule Page");
    JButton uploadCourseScheduleButton = new JButton("Upload Course Schedule");
    JButton viewScheduleButton = new JButton("View Course Schedule");
    JButton backButton = new JButton("Back");
    JTextPane textPane = new JTextPane();
    JScrollPane scrollPane = new JScrollPane(textPane);
    List<HashMap<String, String>> courseInformation = new ArrayList<>();

    String username = "";
    Map<String, CourseSchedule> courseSchedules = new HashMap<>();

    public AddCourseScheduleView() {
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        uploadCourseScheduleButton.setBounds(50, 30, 200, 40);
        uploadCourseScheduleButton.addActionListener(e -> {
            CalendarView calendarView = new CalendarView();
            handleAddCourseSchedule(calendarView);
        });
        viewScheduleButton.setBounds(340, 30, 200, 40);
        viewScheduleButton.addActionListener(e -> {
            displayCourseSchedule();
        });

        backButton.setBounds(200, 90, 200, 40);

        scrollPane.setBounds(50, 150, 500, 300);
        textPane.setEditable(false);

        frame.add(uploadCourseScheduleButton);
        frame.add(viewScheduleButton);
        frame.add(backButton);
        frame.add(scrollPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void handleAddCourseSchedule(CalendarView calendarView) {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        panel.add(new JLabel("Day of the Week (e.g., Monday):"));
        JTextField dayField = new JTextField();
        panel.add(dayField);

        panel.add(new JLabel("Course Name:"));
        JTextField courseNameField = new JTextField();
        panel.add(courseNameField);

        panel.add(new JLabel("Start Time (HH:mm):"));
        JTextField startTimeField = new JTextField();
        panel.add(startTimeField);

        panel.add(new JLabel("End Time (HH:mm):"));
        JTextField endTimeField = new JTextField();
        panel.add(endTimeField);

        panel.add(new JLabel("Semester:"));
        String[] semester = {"Fall", "Winter", "Summer"};
        JComboBox<String> semesterComboBox = new JComboBox<>(semester);
        panel.add(semesterComboBox);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Add Course Schedule", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            HashMap<String, String> subCourseInformation = new HashMap<>();
            try {
                String dayInput = dayField.getText().trim();
                if (dayInput.isEmpty()) {
                    throw new IllegalArgumentException("Day of the week cannot be empty.");
                }
                else {
                    subCourseInformation.put("day", dayInput);
                }

                LocalDate date = mapDayToDate(dayInput);
                if (date == null) {
                    throw new IllegalArgumentException("Invalid day of the week.");
                }
                else {
                    subCourseInformation.put("date", date.toString());
                }

                String courseNameInput = courseNameField.getText().trim();
                if (courseNameInput.isEmpty()) {
                    throw new IllegalArgumentException("Course name cannot be empty.");
                }
                else {
                    subCourseInformation.put("courseName", courseNameInput);
                }

                String startTimeInput = startTimeField.getText().trim();
                String endTimeInput = endTimeField.getText().trim();
                if (startTimeInput.isEmpty() || endTimeInput.isEmpty()) {
                    throw new IllegalArgumentException("Start time and end time cannot be empty.");
                }

                subCourseInformation.put("startTime", startTimeInput);
                subCourseInformation.put("endTime", endTimeInput);
                LocalTime startTime = LocalTime.parse(startTimeInput);
                LocalTime endTime = LocalTime.parse(endTimeInput);

                TimeSlot timeSlot = new TimeSlot(startTime, endTime);

                courseSchedules.putIfAbsent(courseNameInput, new CourseSchedule(username, courseNameInput, new HashMap<>()));
                CourseSchedule currentSchedule = courseSchedules.get(courseNameInput);

                currentSchedule.getInstanceDateAndTimeSlot().putIfAbsent(date, new ArrayList<>());
                currentSchedule.getInstanceDateAndTimeSlot().get(date).add(timeSlot);

                courseInformation.add(subCourseInformation);

                JOptionPane.showMessageDialog(frame, "Course schedule added successfully!");

                System.out.println(courseInformation);

            } catch (IllegalArgumentException | DateTimeParseException | IllegalAccessException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void displayCourseSchedule() {
        if (courseSchedules.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No course schedule found");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Username: ").append(username).append("\n\n");

        // CHANGE 4: Iterate over all courses and their schedules
        courseSchedules.forEach((courseName, schedule) -> {
            stringBuilder.append("Course Name: ").append(courseName).append("\n");
            schedule.getInstanceDateAndTimeSlot().forEach((day, timeSlots) -> {
                stringBuilder.append("  Day: ").append(day).append("\n");
                timeSlots.forEach(slot -> {
                    stringBuilder.append("    Start Time: ").append(slot.getStartTime())
                            .append(", End Time: ").append(slot.getEndTime()).append("\n");
                });
                stringBuilder.append("\n");
            });
        });

        textPane.setText(stringBuilder.toString());

    }

    public LocalDate mapDayToDate(String dayInput) {
        switch (dayInput.toLowerCase()) {
            case "monday":
                return LocalDate.now().with(DayOfWeek.MONDAY);
            case "tuesday":
                return LocalDate.now().with(DayOfWeek.TUESDAY);
            case "wednesday":
                return LocalDate.now().with(DayOfWeek.WEDNESDAY);
            case "thursday":
                return LocalDate.now().with(DayOfWeek.THURSDAY);
            case "friday":
                return LocalDate.now().with(DayOfWeek.FRIDAY);
            case "saturday":
                return LocalDate.now().with(DayOfWeek.SATURDAY);
            case "sunday":
                return LocalDate.now().with(DayOfWeek.SUNDAY);
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        new AddCourseScheduleView();
    }
}
