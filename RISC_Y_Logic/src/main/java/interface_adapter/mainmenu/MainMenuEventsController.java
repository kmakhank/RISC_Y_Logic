package interface_adapter.mainmenu;

import view.AddEventView;

public class MainMenuEventsController implements ButtonController {
    public void click() {
        new AddEventView();
    }
}
