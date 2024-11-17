package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddEventView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "add event";

    private final JTextField eventNameInputField = new JTextField(15);
    private final JTextField eventDescriptionInputField = new JTextField(15);
    private final JTextField eventDateInputField = new JTextField(15);
    private final JTextField eventTimeInputField = new JTextField(15);

    private final JButton addEvent = new JButton("Add Event");
    private final JButton back = new JButton("Back");

    public AddEventView() {
        final JLabel title = new JLabel("Add Event");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel eventNameInfo = new LabelTextPanel(
                new JLabel("Event Name: "), eventNameInputField);
        final LabelTextPanel eventDateInfo = new LabelTextPanel(
                new JLabel("Event Date: "), eventDateInputField);
        final LabelTextPanel eventTimeInfo = new LabelTextPanel(
                new JLabel("Event Time: "), eventTimeInputField);
        final LabelTextPanel eventDescriptionInfo = new LabelTextPanel(
                new JLabel("Event Description: "), eventDescriptionInputField);

        final JPanel buttons = new JPanel();
        buttons.add(addEvent);
        buttons.add(back);

        addEvent.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(addEvent)) {
                        }
                    }
                }
        );

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(back)) {
                        }
                    }
                }
        );

        eventNameInputField.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                    }

                    public void removeUpdate(DocumentEvent e) {
                    }

                    public void changedUpdate(DocumentEvent e) {
                    }
                }
        );

        eventDateInputField.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                    }

                    public void removeUpdate(DocumentEvent e) {
                    }

                    public void changedUpdate(DocumentEvent e) {
                    }
                }
        );

        eventTimeInputField.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                    }

                    public void removeUpdate(DocumentEvent e) {
                    }

                    public void changedUpdate(DocumentEvent e) {
                    }
                }
        );

        eventDescriptionInputField.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                    }

                    public void removeUpdate(DocumentEvent e) {
                    }

                    public void changedUpdate(DocumentEvent e) {
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(eventNameInfo);
        this.add(eventDateInfo);
        this.add(eventTimeInfo);
        this.add(eventDescriptionInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }
}
