package cis.view;

import javax.swing.*;
import java.awt.*;

/**
 * The class to create button component - an object having a graphical representation
 * that can be displayed on the screen and that can interact with the user.
 */
public class ButtonComponent extends JButton {

    /**
     * Constructs a button with the specified text and color.
     *
     * @param text a string text for the button, or null for no text.
     * @param color a color for the button, or null for no color.
     */
    public ButtonComponent(String text, Color color) {
        super(text);
        setForeground(Color.WHITE);
        setFont(new Font("Consolas", Font.PLAIN, 16));
        setBackground(color);
        setFocusPainted(false);
    }
}
