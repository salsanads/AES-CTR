package cis.view;

import cis.app.AESCTRDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    private static Dimension size;
    private static JPanel container;
    private static JButton button;

    private static FilePickerComponent inputFileComponent;
    private static FilePickerComponent keyFileComponent;
    private static FilePickerComponent outputFileComponent;
    private static OptionComponent optionComponent;
    private static ButtonComponent buttonComponent;
    private static MessageComponent messageComponent;

    public Frame() {
        size = new Dimension(600, 300);

        setTitle("AES Calculator with CTR Mode and PKCS#5");
        setMinimumSize(size);
        getContentPane().setBackground(Color.BLACK);

        constructContent();

        add(container, BorderLayout.CENTER);

        setVisible(true);
    }

    private void constructContent() {
        container = new JPanel();
        container.setLayout(new FlowLayout());
        container.setBackground(Color.BLACK);
        container.setPreferredSize(size);

        inputFileComponent = new FilePickerComponent("Input file", "Browse...", 1);
        keyFileComponent = new FilePickerComponent("Key file", "Browse...", 1);
        outputFileComponent = new FilePickerComponent("Output file", "Browse...", 2);
        optionComponent = new OptionComponent();
        buttonComponent = new ButtonComponent("Execute!");
        buttonComponent.addActionListener(this);
        messageComponent = new MessageComponent();

        container.add(inputFileComponent);
        container.add(keyFileComponent);
        container.add(outputFileComponent);
        container.add(optionComponent);
        container.add(buttonComponent);
        container.add(messageComponent);
    }

    public static void main(String[] args) {
        new Frame();
    }

    public static MessageComponent getMessageComponent() {
        return messageComponent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonComponent) {
            JLabel messageField = messageComponent.getMessageField();
            messageField.setForeground(Color.RED);

            String inputFilePath = inputFileComponent.getSelectedFilePath();
            String keyFilePath = keyFileComponent.getSelectedFilePath();
            String outputFilePath = outputFileComponent.getSelectedFilePath();

            boolean isEcryptSelected = optionComponent.isEncryptSelected();
            boolean isDecryptSelected = optionComponent.isDecryptSelected();

            if (inputFilePath.equals("")) {
                messageField.setText("Please pick input file!");
            } else if (keyFilePath.equals("")) {
                messageField.setText("Please pick key file!");
            } else if (outputFilePath.equals("")) {
                messageField.setText("Please pick output file!");
            } else {
                if (isEcryptSelected) {
                    AESCTRDriver.doEncryption(inputFilePath, keyFilePath, outputFilePath);
                } else if (isDecryptSelected) {
                    AESCTRDriver.doDecryption(inputFilePath, keyFilePath, outputFilePath);
                } else {
                    messageField.setText("Please choose Encrypt or Decrypt!");
                }
            }
        }
    }
}
