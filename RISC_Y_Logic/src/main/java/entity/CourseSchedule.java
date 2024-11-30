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

    public String toString(LocalDate date) {
        String timeslots = schedule.get(date).get(0).toString();
        for(int i = 1; i < schedule.get(date).size(); i++) {
            timeslots += ", " + schedule.get(date).get(i).toString();
        }
        return "Course Name : " + courseName + "\nCourse Time : " + timeslots;
    }
}
