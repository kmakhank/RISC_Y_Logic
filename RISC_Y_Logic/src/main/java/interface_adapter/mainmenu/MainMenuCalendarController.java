package interface_adapter.mainmenu;

import interface_adapter.add_course_schedule.AddCourseScheduleController;
import interface_adapter.add_event.AddRecommendEventController;
import interface_adapter.add_event.AddUserEventController;
import interface_adapter.calendar.CalendarPresenter;
import use_case.add_course_schedule.AddCourseScheduleDataAccessInterface;
import use_case.add_event.AddEventDataAccessInterface;
import use_case.calendar.CalendarInputBoundary;
import view.CalendarView;

public class MainMenuCalendarController{

    private final CalendarInputBoundary calendarInputBoundary;
    private final CalendarPresenter calendarPresenter;
    private final AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface;
    private final AddCourseScheduleController addCourseScheduleController;
    private final AddEventDataAccessInterface addEventDataAccessInterface;
    private final AddUserEventController addUserEventController;
    private final AddRecommendEventController addRecommendEventController;

    public MainMenuCalendarController(CalendarInputBoundary calendarInputBoundary,
                                      CalendarPresenter calendarPresenter,
                                      AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface,
                                      AddCourseScheduleController addCourseScheduleController,
                                      AddEventDataAccessInterface addEventDataAccessInterface,
                                      AddUserEventController addUserEventController,
                                      AddRecommendEventController addRecommendEventController) {

        this.calendarInputBoundary = calendarInputBoundary;
        this.calendarPresenter = calendarPresenter;
        this.addCourseScheduleDataAccessInterface = addCourseScheduleDataAccessInterface;
        this.addCourseScheduleController = addCourseScheduleController;
        this.addEventDataAccessInterface = addEventDataAccessInterface;
        this.addUserEventController = addUserEventController;
        this.addRecommendEventController = addRecommendEventController;
    }

    public void click() {
        new CalendarView(calendarInputBoundary, calendarPresenter, addCourseScheduleDataAccessInterface, addCourseScheduleController, addEventDataAccessInterface, addUserEventController, addRecommendEventController);
    }
}
