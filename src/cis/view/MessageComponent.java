package cis.view;

import javax.swing.*;
import java.awt.*;

public class MessageComponent extends JPanel {
    private JLabel messageField;

    public MessageComponent() {
        messageField = new JLabel(" ");
        messageField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        messageField.setForeground(Color.WHITE);
        add(messageField);

        setBackground(Color.BLACK);
    }

    public JLabel getMessageField() {
        return messageField;
    }
}
