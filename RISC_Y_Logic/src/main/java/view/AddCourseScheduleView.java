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

    Map<String, CourseSchedule> courseSchedules;
    Map<LocalDate, List<CourseSchedule>> dateToScheduleMap;

    public AddCourseScheduleView(Map<LocalDate, List<CourseSchedule>> dateToScheduleMap, Map<String, CourseSchedule> courseSchedules) {
        this.dateToScheduleMap = dateToScheduleMap;
        this.courseSchedules = courseSchedules;

        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        uploadCourseScheduleButton.setBounds(50, 30, 200, 40);
        uploadCourseScheduleButton.addActionListener(e -> handleAddCourseSchedule());

        viewScheduleButton.setBounds(340, 30, 200, 40);
        viewScheduleButton.addActionListener(e -> displayCourseSchedule());

        backButton.setBounds(200, 90, 200, 40);
        backButton.addActionListener(e -> frame.dispose());

        scrollPane.setBounds(50, 150, 500, 300);
        textPane.setEditable(false);

        frame.add(uploadCourseScheduleButton);
        frame.add(viewScheduleButton);
        frame.add(backButton);
        frame.add(scrollPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void handleAddCourseSchedule() {
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
            try {
                String dayInput = dayField.getText().trim();
                String courseNameInput = courseNameField.getText().trim();
                String startTimeInput = startTimeField.getText().trim();
                String endTimeInput = endTimeField.getText().trim();
                String semesterInput = (String) semesterComboBox.getSelectedItem();

                if (dayInput.isEmpty() || courseNameInput.isEmpty() || startTimeInput.isEmpty() || endTimeInput.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled");
                }

                LocalTime startTime = LocalTime.parse(startTimeInput);
                LocalTime endTime = LocalTime.parse(endTimeInput);
                TimeSlot timeSlot = new TimeSlot(startTime, endTime);

                List<LocalDate> localDates = mapDayToDates(dayInput, semesterInput);

                CourseSchedule courseSchedule = new CourseSchedule(courseNameInput, new TreeMap<>());

                for (LocalDate localDate : localDates) {
                    courseSchedule.getInstanceDateAndTimeSlot().putIfAbsent(localDate, new ArrayList<>());
                    courseSchedule.getInstanceDateAndTimeSlot().get(localDate).add(timeSlot);

                    dateToScheduleMap.putIfAbsent(localDate, new ArrayList<>());
                    dateToScheduleMap.get(localDate).add(courseSchedule);
                }

                courseSchedules.put(courseNameInput, courseSchedule);

                JOptionPane.showMessageDialog(frame, "Course schedule successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (IllegalArgumentException | DateTimeParseException | IllegalAccessException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void displayCourseSchedule() {
        if (courseSchedules.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No course schedule found", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();

        courseSchedules.forEach((courseName, schedule) -> {
            stringBuilder.append("Course Name: ").append(courseName).append("\n");
            schedule.getInstanceDateAndTimeSlot().forEach((day, timeSlots) -> {
                stringBuilder.append("Day: ").append(day).append("\n");
                timeSlots.forEach(slot -> {
                    stringBuilder.append("Start Time: ").append(slot.getStartTime())
                            .append(", End Time: ").append(slot.getEndTime()).append("\n");
                });
                stringBuilder.append("\n");
            });
        });

        textPane.setText(stringBuilder.toString());
        JOptionPane.showMessageDialog(frame, "Course schedules updated in the view!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public List<LocalDate> mapDayToDates(String dayInput, String semesterInput) {
        LocalDate startDate;
        LocalDate endDate;
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayInput.toUpperCase());
        List<LocalDate> localDates = new ArrayList<>();

        switch (semesterInput) {
            case "Fall":
                startDate = LocalDate.of(LocalDate.now().getYear(), 9, 1);
                endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);
                break;
            case "Winter":
                startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                endDate = LocalDate.of(LocalDate.now().getYear(), 4, 30);
                break;
            case "Summer":
                startDate = LocalDate.of(LocalDate.now().getYear(), 5, 1);
                endDate = LocalDate.of(LocalDate.now().getYear(), 8, 31);
                break;
            default:
                throw new IllegalArgumentException("Invalid Semester");
        }

        LocalDate date = startDate;
        while (date.isBefore(endDate) || date.isEqual(endDate)) {
            if (date.getDayOfWeek() == dayOfWeek) {
                localDates.add(date);
            }
            date = date.plusDays(1);
        }

        return localDates;
    }

    public static void main(String[] args) {
        new CalendarView();
    }
}