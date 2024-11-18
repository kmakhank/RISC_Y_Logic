package view;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AddCourseScheduleView {
    JFrame frame = new JFrame("Add Course Schedule Page");
    JButton uploadCourseScheduleButton = new JButton("Upload Course Schedule");
    JButton backButton = new JButton("Back");

    public AddCourseScheduleView() {
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        uploadCourseScheduleButton.setBounds(210, 50, 100, 80);

        backButton.setBounds(210, 200, 100, 80);

        frame.add(uploadCourseScheduleButton);
        frame.add(backButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
