package app;

import view.AddEventView;
import view.EventRecommendationView;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private AddEventView addEventView;
    private EventRecommendationView eventRecommendationView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addAddEventView() {
        addEventView = new AddEventView();
        cardPanel.add(addEventView, addEventView.getViewName());
        return this;
    }

    public AppBuilder addEventRecommendationView() {
        eventRecommendationView = new EventRecommendationView();
        cardPanel.add(eventRecommendationView, eventRecommendationView.getViewName());
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.add(cardPanel);

        return application;
    }
}
