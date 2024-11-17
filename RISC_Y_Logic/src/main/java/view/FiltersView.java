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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FiltersView extends JPanel {
    private final String viewName = "Filters";

    private final JTextField interestsInputField = new JTextField(15);
    private final JTextField DateInputField = new JTextField(15);
    private final JTextField TimeInputField = new JTextField(15);

    private final JButton back = new JButton("Back");

    public FiltersView() {
        final JLabel title = new JLabel("Filters");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setSize(100, 100);

        final LabelTextPanel interestsInfo = new LabelTextPanel(
                new JLabel("Interests: "), interestsInputField);
        final LabelTextPanel DateInfo = new LabelTextPanel(
                new JLabel("Date: "), DateInputField);
        final LabelTextPanel TimeInfo = new LabelTextPanel(
                new JLabel("Time: "), TimeInputField);

        final JPanel buttons = new JPanel();
        buttons.add(back);

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(back)) {
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(interestsInfo);
        this.add(DateInfo);
        this.add(TimeInfo);
        this.add(buttons);
    }

    public String getViewName() {
        return viewName;
    }
}
