package use_case.add_event;

import java.util.Set;

public class AddEventInputData {
    private final String eventName;
    private final String eventDate;
    private final String eventTime;
    private final String eventDescription;
    private final Set<String> tags;

    public AddEventInputData(String eventName, String eventDate, String eventTime, String eventDescription, Set<String> tags) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
        this.tags = tags;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public Set<String> getTags() {
        return tags;
    }
}
