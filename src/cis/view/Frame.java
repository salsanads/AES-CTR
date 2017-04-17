package cis.view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private static Dimension size;
    private static JPanel container;
    private static JButton button;

    private static MessageComponent messageComponent;

    public Frame() {
        size = new Dimension(400, 300);

        setTitle("AES Calculator with CTR Mode and PKCS#5");
        setMinimumSize(size);
        getContentPane().setBackground(Color.BLACK);

        constructContent();

        add(container, BorderLayout.CENTER);

        setVisible(true);
    }

    private void constructContent() {
        container = new JPanel();
        container.setLayout(new GridLayout(4, 1));
        container.setBackground(Color.BLACK);
        container.setPreferredSize(size);

        button = new JButton("Encrypt!");
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.PLAIN, 48));
        button.setOpaque(false);
        button.setContentAreaFilled(false);

        messageComponent = new MessageComponent();

        container.add(button);
        container.add(messageComponent);
    }

    public static void main(String[] args) {
        new Frame();
    }

    public static MessageComponent getMessageComponent() {
        return messageComponent;
    }
}
