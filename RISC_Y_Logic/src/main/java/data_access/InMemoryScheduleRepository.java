package data_access;

import entity.CourseSchedule;
import use_case.add_course_schedule.AddCourseScheduleDataAccessInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryScheduleRepository implements AddCourseScheduleDataAccessInterface {

    private final Map<LocalDate, List<CourseSchedule>> dateToScheduleMap =  new HashMap<>();
    private final Map<String, CourseSchedule> courseSchedules = new HashMap<>();

    @Override
    public void saveCourseSchedule(CourseSchedule courseSchedule, List<LocalDate> dates) {
        for (LocalDate date : dates) {
            dateToScheduleMap.putIfAbsent(date, new ArrayList<>());
            dateToScheduleMap.get(date).add(courseSchedule);
            courseSchedule.getInstanceDateAndTimeSlot().putIfAbsent(date, new ArrayList<>());
        }
        courseSchedules.put(courseSchedule.getInstanceName(), courseSchedule);
    }

    @Override
    public Map<LocalDate, List<CourseSchedule>> getDayToScheduleMap() {
        return dateToScheduleMap;
    }

    @Override
    public final Map<String, CourseSchedule> getCourseSchedules() {
        return courseSchedules;
    }
}

