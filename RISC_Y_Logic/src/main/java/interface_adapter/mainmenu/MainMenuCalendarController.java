package interface_adapter.mainmenu;

import interface_adapter.add_course_schedule.AddCourseScheduleController;
import interface_adapter.calendar.CalendarPresenter;
import use_case.add_course_schedule.AddCourseScheduleDataAccessInterface;
import use_case.calendar.CalendarInputBoundary;
import view.CalendarView;

public class MainMenuCalendarController{

    private final CalendarInputBoundary calendarInputBoundary;
    private final CalendarPresenter calendarPresenter;
    private final AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface;
    private final AddCourseScheduleController addCourseScheduleController;

    public MainMenuCalendarController(AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface,
                                      AddCourseScheduleController addCourseScheduleController,
                                      CalendarInputBoundary calendarInputBoundary,
                                      CalendarPresenter calendarPresenter) {

        this.addCourseScheduleDataAccessInterface = addCourseScheduleDataAccessInterface;
        this.addCourseScheduleController = addCourseScheduleController;
        this.calendarInputBoundary = calendarInputBoundary;
        this.calendarPresenter = calendarPresenter;
    }

    public void click() {
        new CalendarView(calendarInputBoundary, calendarPresenter, addCourseScheduleDataAccessInterface, addCourseScheduleController);
    }
}
