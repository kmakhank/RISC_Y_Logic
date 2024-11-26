package view;

import interface_adapter.add_event.AddEventController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.*;

public class AddUserEventView {
    private final JFrame frame = new JFrame("Add Your Own Event");

    private final JTextField eventNameInputField = new JTextField(15);
    private final JTextField eventDescriptionInputField = new JTextField(15);
    private final JTextField eventDateInputField = new JTextField(15);
    private final JTextField eventTimeInputField = new JTextField(15);

    private final JList<String> tagsInputField = new JList<>();

    private AddEventController addEventController;

    private final JButton addEvent = new JButton("Add Event");

    public AddUserEventView() {
        final JPanel view = new JPanel();
        final JLabel title = new JLabel("Add Event");

        final JPanel eventNameInfo = new LabelTextPanel(
                new JLabel("Event Name: "), eventNameInputField);
        final JPanel eventDateInfo = new LabelTextPanel(
                new JLabel("Event Date: "), eventDateInputField);
        final JPanel eventTimeInfo = new LabelTextPanel(
                new JLabel("Event Time: "), eventTimeInputField);
        final JPanel eventDescriptionInfo = new LabelTextPanel(
                new JLabel("Event Description: "), eventDescriptionInputField);
        final JPanel tags = new LabelListPanel(
                new JLabel("Tags: "), tagsInputField);
        tagsInputField.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tagsInputField.setListData(new String[]{"tag1", "tag2", "tag3"});

        final JPanel buttons = new JPanel();
        buttons.add(addEvent);

        addEvent.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(addEvent)) {
                            addEventController.execute(
                                    eventNameInputField.getText(),
                                    eventDateInputField.getText(),
                                    eventTimeInputField.getText(),
                                    eventDescriptionInputField.getText(),
                                    new HashSet<>(tagsInputField.getSelectedValuesList())
                            );
                        }
                    }
                }
        );

        view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

        view.add(title);
        view.add(eventNameInfo);
        view.add(eventDateInfo);
        view.add(eventTimeInfo);
        view.add(eventDescriptionInfo);
        view.add(tags);
        view.add(buttons);

        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }

    public void setAddEventController(AddEventController addEventController) {
        this.addEventController = addEventController;
    }
}
