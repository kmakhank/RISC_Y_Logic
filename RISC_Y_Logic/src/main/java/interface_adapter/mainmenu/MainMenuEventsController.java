package interface_adapter.mainmenu;

import view.EventRecommendationView;

public class MainMenuEventsController implements ButtonController {
    public void click() {
        new EventRecommendationView();
    }
}
