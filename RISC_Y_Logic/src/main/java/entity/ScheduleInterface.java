package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ScheduleInterface {

    String getInstanceName();

    Map<LocalDate, List<TimeSlot>> getInstanceDateAndTimeSlot();

}
