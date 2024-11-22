package interface_adapter.mainmenu;

import view.CalendarView;

public class MainMenuCalendarController implements ButtonController {
    public void click() {
        new CalendarView();
    }
}
