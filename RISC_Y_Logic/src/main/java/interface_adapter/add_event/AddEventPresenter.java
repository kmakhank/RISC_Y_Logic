package interface_adapter.add_event;

import javax.swing.*;

public class AddEventPresenter {

    public void prepareSuccessView(String message) {
        JOptionPane.showMessageDialog(null, message, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public void prepareFailView(String message) {
        JOptionPane.showMessageDialog(null, message, "Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
