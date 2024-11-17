package view;

import javax.swing.*;

public class AddCourseScheduleView {
    public AddCourseScheduleView() {
        JFrame frame = new JFrame("Add Course Schedule Page");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton uploadCourseScheduleButton = new JButton("Upload Course Schedule");
        uploadCourseScheduleButton.setBounds(210, 50, 100, 80);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 200, 100, 80);

        frame.add(uploadCourseScheduleButton);
        frame.add(backButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
