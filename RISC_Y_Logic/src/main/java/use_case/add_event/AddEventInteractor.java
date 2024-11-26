package use_case.add_event;

import entity.EventFactory;
import interface_adapter.add_event.AddEventPersenter;

public class AddEventInteractor {
    private final AddEventUserDataAccessInterface userDataAccessObject;
    private final AddEventPersenter newEventPersenter;
    private final EventFactory eventFactory;

    public AddEventInteractor(AddEventUserDataAccessInterface userDataAccessObject,
                              AddEventPersenter newEventPersenter,
                              EventFactory eventFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.newEventPersenter = newEventPersenter;
        this.eventFactory = eventFactory;
    }

    public void execute(AddEventInputData newEventInputData) {
        final String eventName = newEventInputData.getEventName();
        final String eventDate = newEventInputData.getEventDate();
        final String eventTime = newEventInputData.getEventTime();
        final String eventDescription = newEventInputData.getEventDescription();

    }

    public void switchToCalendarView() {
        newEventPersenter.switchtoCalendarView();
    }
}
