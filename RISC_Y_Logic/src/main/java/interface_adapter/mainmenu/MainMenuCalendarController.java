package interface_adapter.mainmenu;

import interface_adapter.add_course_schedule.AddCourseScheduleController;
import use_case.add_course_schedule.AddCourseScheduleDataAccessInterface;
import view.CalendarView;

public class MainMenuCalendarController{

    private final AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface;
    private final AddCourseScheduleController addCourseScheduleController;

    public MainMenuCalendarController(AddCourseScheduleDataAccessInterface addCourseScheduleDataAccessInterface,
                                      AddCourseScheduleController addCourseScheduleController) {

        this.addCourseScheduleDataAccessInterface = addCourseScheduleDataAccessInterface;
        this.addCourseScheduleController = addCourseScheduleController;
    }

    public void click() {
        new CalendarView(addCourseScheduleDataAccessInterface, addCourseScheduleController);
    }
}
