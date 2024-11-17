package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchResultsView extends JPanel {
    private final String viewName = "Search Results";

    private final JButton addEvent = new JButton("Add Event");

    public SearchResultsView() {
        final JLabel title = new JLabel("Search Results");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JScrollPane searchResults = new JScrollPane();
        searchResults.setSize(100, 300);

        final JPanel buttons = new JPanel();
        buttons.add(addEvent);

        addEvent.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(addEvent)) {
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(searchResults);
        this.add(buttons);
    }

    public String getViewName() {
        return viewName;
    }
}
