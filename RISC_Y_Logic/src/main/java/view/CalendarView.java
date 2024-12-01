package view;

import entity.CalendarDay;
import entity.CourseSchedule;
import interface_adapter.add_course_schedule.AddCourseScheduleController;
import interface_adapter.add_event.AddRecommendEventController;
import interface_adapter.add_event.AddUserEventController;
import interface_adapter.calendar.CalendarNextButtonController;
import interface_adapter.calendar.CalendarPresenter;
import interface_adapter.calendar.CalendarPreviousButtonController;
import use_case.add_course_schedule.AddCourseScheduleDataAccessInterface;
import use_case.add_event.AddEventDataAccessInterface;
import use_case.calendar.CalendarInputBoundary;
import use_case.calendar.CalendarInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Calendar;
import java.util.Map;

public class CalendarView {

    private final CalendarInputBoundary calendarInteractor;
    private final CalendarPresenter calendarPresenter;
    private final AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface;
    private final AddCourseScheduleController addCourseScheduleController;
    private final AddEventDataAccessInterface addEventDataAccessInterface;
    private final AddUserEventController addUserEventController;
    private final AddRecommendEventController addRecommendEventController;

    private final CalendarPreviousButtonController calendarPreviousButtonController;
    private final CalendarNextButtonController calendarNextButtonController;

    private final int[] year = {Calendar.getInstance().get(Calendar.YEAR)};
    private final int[] month = {Calendar.getInstance().get(Calendar.MONTH)};
    private final JFrame frame = new JFrame("Calendar Page");
    private final JPanel headerPanel = new JPanel();
    private final JLabel monthLabel = CalendarUIComponentFactory.createLabel("", 24, Color.WHITE);
    private final JPanel calendarPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JButton previousButton = CalendarUIComponentFactory.createButton("<", 18, Color.WHITE);
    private final JButton nextButton = CalendarUIComponentFactory.createButton(">", 18, Color.WHITE);
    JButton addEventButton = new StyleButton("Add Event");
    JButton addCourseScheduleButton = new StyleButton("Add Course Schedule");

    public CalendarView(CalendarInputBoundary calendarInputBoundary,
                        CalendarPresenter calendarPresenter,
                        AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface,
                        AddCourseScheduleController addCourseScheduleController,
                        AddEventDataAccessInterface addEventDataAccessInterface,
                        AddUserEventController addUserEventController,
                        AddRecommendEventController addRecommendEventController) {

        this.calendarInteractor = calendarInputBoundary;
        this.calendarPresenter = calendarPresenter;
        this.addCourseScheduleDataAccessInterface = addCourseScheduleDataAccessInterface;
        this.addCourseScheduleController = addCourseScheduleController;
        this.addEventDataAccessInterface = addEventDataAccessInterface;
        this.addUserEventController = addUserEventController;
        this.addRecommendEventController = addRecommendEventController;

        this.calendarPreviousButtonController = new CalendarPreviousButtonController(calendarInputBoundary, year, month);
        this.calendarNextButtonController = new CalendarNextButtonController(calendarInputBoundary, year, month);

        initializeUI();
    }

    private void initializeUI() {
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());

        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        previousButton.addActionListener(e -> {
            calendarPreviousButtonController.handlePreviousButtonClick();
            updateCalendar(year[0], month[0]);
        });

        nextButton.addActionListener(e -> {
            calendarNextButtonController.handleNextButtonClick();
            updateCalendar(year[0], month[0]);
        });

        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(previousButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        calendarPanel.setLayout(new GridLayout(0, 7));
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addEventButton.addActionListener(e -> new AddUserEventView(addUserEventController));

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
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        monthLabel.setText(monthNames[month] + " " + year);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);

        calendarInteractor.generate(new CalendarInputData(year, month));

        List<CalendarDay> calendarDays = calendarPresenter.getCalendarDays();
        renderCalendarWithOffset(calendarDays);
    }

    private void renderCalendarWithOffset(List<CalendarDay> calendarDays) {
        calendarPanel.removeAll();

        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (String day : daysOfWeek) {
            JLabel dayLabel = CalendarUIComponentFactory.createLabel(day, 16, new Color(70, 132, 180));
            calendarPanel.add(dayLabel);
        }

        for (CalendarDay day : calendarDays) {
            JLabel dayLabel = createDayLabel(day);
            calendarPanel.add(dayLabel);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private JLabel createDayLabel(CalendarDay day) {
        JLabel dayLabel = CalendarUIComponentFactory.createLabel(
                day.isEmpty() ? "" : String.valueOf(day.getDay().getDayOfMonth()),
                16, Color.BLACK
        );

        if (!day.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(day.getDay().getYear(), day.getDay().getMonthValue() - 1, day.getDay().getDayOfMonth());
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                dayLabel.setOpaque(true);
                dayLabel.setBackground(new Color(255, 228, 225));
            }
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }

        if (!day.isEmpty()) {
            dayLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showSchedules(day);
                }
            });
        }

        return dayLabel;
    }

    private void showSchedules(CalendarDay day) {
        Map<LocalDate, List<CourseSchedule>> dayToScheduleMap = addCourseScheduleDataAccessInterface.getDayToScheduleMap();
        if (dayToScheduleMap.containsKey(day.getDay())) {
            StringBuilder message = new StringBuilder("Courses and Events on " + day.getDay() + ":\n");
            List<CourseSchedule> courseScheduleList = dayToScheduleMap.get(day.getDay());
            courseScheduleList.forEach(schedule -> {
                message.append("Course Name: ").append(schedule.getInstanceName()).append("\n");
                schedule.getInstanceDateAndTimeSlot().get(day.getDay()).forEach(slot -> {
                    message.append("Start Time: ").append(slot.getStartTime())
                            .append(", End Time: ").append(slot.getEndTime()).append("\n");
                });
            });
            JOptionPane.showMessageDialog(frame, message.toString());
        } else {
            JOptionPane.showMessageDialog(frame, "No courses or events on this date.");
        }
    }
}

