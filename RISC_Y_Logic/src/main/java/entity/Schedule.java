package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Schedule {
    private final UUID userId;
    private final Map<LocalDate, List<TimeSlot>> weeklySchedule;
    private final List<TimeSlot> unavailableTimes;

    public Schedule(UUID userId, Map<LocalDate, List<TimeSlot>> weeklySchedule, List<TimeSlot> unavailableTimes) {
        this.userId = userId;
        this.weeklySchedule = weeklySchedule;
        this.unavailableTimes = unavailableTimes;
    }

    public UUID getUserId() {
        return userId;
    }

    public Map<LocalDate, List<TimeSlot>> getWeeklySchedule() {
        return weeklySchedule;
    }

    public List<TimeSlot> getUnavailableTimes() {
        return unavailableTimes;
    }
}