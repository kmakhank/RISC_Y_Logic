package entity;

import java.time.LocalDate;
import java.util.List;

public class CalendarDay {
    private final LocalDate day;
    private final List<CourseSchedule> schedules;

    public CalendarDay(LocalDate day, List<CourseSchedule> schedules) {
        this.day = day;
        this.schedules = schedules;
    }

    public LocalDate getDay() {
        return day;
    }

    public List<CourseSchedule> getSchedules() {
        return schedules;
    }

    public boolean isEmpty() {
        return day == null;
    }
}
