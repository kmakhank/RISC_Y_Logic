package view;

import javax.swing.*;

public class EventRecommendationView extends JPanel {
    private final String viewName = "Event Recommendation";

    private final FiltersView filters = new FiltersView();
    private final SearchResultsView searchResults = new SearchResultsView();

    public EventRecommendationView() {
        this.add(filters);
        this.add(searchResults);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public String getViewName() {
        return viewName;
    }
}
