package view;

import javax.swing.*;

public class LabelListPanel extends JPanel {
    LabelListPanel(JLabel label, JList<String> list) {
        this.add(label);
        this.add(new JScrollPane(list));
    }

}
