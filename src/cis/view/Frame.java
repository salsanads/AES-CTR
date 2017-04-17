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

//        button = new JButton("Encrypt!");
//        button.setForeground(Color.WHITE);
//        button.setFont(new Font("SansSerif", Font.PLAIN, 48));
//        button.setOpaque(false);
//        button.setContentAreaFilled(false);
//
//        container.add(button);

        messageComponent = new MessageComponent();

        container.add(button);
        container.add(messageComponent);

        FilePickerComponent filePicker = new FilePickerComponent("Pick a file", "Browse...");
        filePicker.setMode(FilePickerComponent.MODE_OPEN);

        container.add(filePicker);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 100);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Frame();
    }

    public static MessageComponent getMessageComponent() {
        return messageComponent;
    }
}
