
package entity;

import java.time.LocalDate;
import java.util.Set;

public class EventSchedule {
    private final String name;
    private final LocalDate date;
    private final TimeSlot timeSlot;
    private final String description;
    private final Set<String> tags;
    private final String source;

    public EventSchedule(String title, LocalDate date, TimeSlot timeSlot, String description, Set<String> tags, String source) {
        this.name = title;
        this.date = date;
        this.timeSlot = timeSlot;
        this.description = description;
        this.tags = tags;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getSource() {
        return source;
    }

    public String toString() {
        return "Event Name: " + name + "\n  Event Time:\n    " + timeSlot.toString();
    }
}
