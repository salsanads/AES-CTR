package cis.view;

import javax.swing.*;
import java.awt.*;

/**
 * The class to create message component - an object having a graphical representation
 * that can be displayed on the screen and that can give more infomation to user.
 */
public class MessageComponent extends JPanel {
    private JLabel messageField;

    /**
     * Constructs the message.
     */
    public MessageComponent() {
        messageField = new JLabel(" ");
        messageField.setFont(new Font("Consolas", Font.PLAIN, 14));
        messageField.setForeground(Color.WHITE);
        add(messageField);

        setBackground(Color.BLACK);
    }

    /**
     * Gets the message.
     *
     * @return the message you want to display.
     */
    public JLabel getMessageField() {
        return messageField;
    }
}
