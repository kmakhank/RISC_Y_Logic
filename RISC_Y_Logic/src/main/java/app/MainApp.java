package app;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                //.addAddEventView()
                .addEventRecommendationView()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
