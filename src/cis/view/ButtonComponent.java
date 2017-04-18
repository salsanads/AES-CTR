package cis.view;

import javax.swing.*;
import java.awt.*;

public class ButtonComponent extends JButton {
    public ButtonComponent(String text) {
        super(text);
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.PLAIN, 16));
        setBackground(Color.BLUE);
        setFocusPainted(false);
    }
}
