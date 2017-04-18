package cis.view;

import javax.swing.*;
import java.awt.*;

public class ButtonComponent extends JButton {
    public ButtonComponent(String text, Color color) {
        super(text);
        setForeground(Color.WHITE);
        setFont(new Font("Consolas", Font.PLAIN, 16));
        setBackground(color);
        setFocusPainted(false);
    }
}
