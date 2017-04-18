package cis.view;

import javax.swing.*;
import java.awt.*;

public class HelpTextComponent extends JPanel {
    private JLabel labelField;

    public HelpTextComponent() {
        labelField = new JLabel("Provide hex string with 128, 192, or 256 bit long.");
        labelField.setFont(new Font("Consolas", Font.PLAIN, 10));
        labelField.setForeground(Color.GRAY);
        add(labelField);

        setBackground(Color.BLACK);

    }
}
