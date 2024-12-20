package entity;

import java.time.LocalTime;

public class TimeSlot {
    private final LocalTime startTime;
    private final LocalTime endTime;

    public TimeSlot(LocalTime startTime, LocalTime endTime)
            throws IllegalAccessException {
        if (endTime.isBefore(startTime)) {
            throw new IllegalAccessException(
                    "End time must be after start time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String toString() {
        return startTime + "~" + endTime;
    }
}
