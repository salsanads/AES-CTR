package cis.view;

import javax.swing.*;
import java.awt.*;

/**
 * The class to create help text component - an object having a graphical representation
 * that can be displayed on the screen and that can give more infomation of the key to user.
 */
public class HelpTextComponent extends JPanel {
    private JLabel labelField;

    /**
     * Constructs the help text for the key field.
     */
    public HelpTextComponent() {
        labelField = new JLabel("Provide hex string with 128, 192, or 256 bit long.");
        labelField.setFont(new Font("Consolas", Font.PLAIN, 10));
        labelField.setForeground(Color.GRAY);
        add(labelField);

        setBackground(Color.BLACK);
    }
}
