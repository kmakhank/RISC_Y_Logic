package use_case.add_event;

import entity.Event;

public class AddEventOutputData {
    private final Event newEvent;
    private final boolean useCaseFailed;

    public AddEventOutputData(Event newEvent, boolean useCaseFailed) {
        this.newEvent = newEvent;
        this.useCaseFailed = useCaseFailed;
    }

    public Event getNewEvent() {
        return newEvent;
    }
}
