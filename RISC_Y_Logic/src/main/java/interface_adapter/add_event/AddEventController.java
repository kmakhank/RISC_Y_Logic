package interface_adapter.add_event;

import use_case.add_event.AddEventInputData;
import use_case.add_event.AddEventInteractor;

import java.util.Set;

public class AddEventController {

    private final AddEventInteractor addEventUseCaseInteractor;

    public AddEventController(AddEventInteractor interactor) {
        this.addEventUseCaseInteractor = interactor;
    }

    public void execute(String eventName, String eventDate, String eventTime, String eventDescription, Set<String> tags) {
        final AddEventInputData inputData = new AddEventInputData(
                eventName, eventDate, eventTime, eventDescription, tags);
        addEventUseCaseInteractor.execute(inputData);
    }

    public void switchToCalendarView() {
        addEventUseCaseInteractor.switchToCalendarView();
    }
}
