package interface_adapter.calendar;

import view.MainMenuView;

public class CalendarBackButtonController implements ButtonController {
    public void click() {
        new MainMenuView();
    }
}
