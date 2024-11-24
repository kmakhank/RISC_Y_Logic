package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CourseSchedule implements ScheduleInterface {
    private final String courseName;
    private final Map<LocalDate, List<TimeSlot>> schedule;

    public CourseSchedule(String courseName, Map<LocalDate, List<TimeSlot>> schedule) {
        this.courseName = courseName;
        this.schedule = schedule;
    }

    @Override
    public String getInstanceName() {
        return courseName;
    }

    @Override
    public Map<LocalDate, List<TimeSlot>> getInstanceDateAndTimeSlot() {
        return schedule;
    }
}
