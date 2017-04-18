package cis.view;

import cis.app.AESCTRDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    private static Dimension size;

    private static TitleComponent titleComponent;
    private static FilePickerComponent inputFileComponent;
    private static FilePickerComponent keyFileComponent;
    private static FilePickerComponent outputFileComponent;
    private static OptionComponent optionComponent;
    private static ButtonComponent buttonComponent;
    private static MessageComponent messageComponent;

    public Frame() {
        size = new Dimension(750, 525);

        setTitle("AES Calculator Using CTR Mode and PKCS#5");
        setMinimumSize(size);
        setResizable(false);
        setLocationRelativeTo(null);

        constructContent();

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.BLACK);
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        contentPane.add(titleComponent);
        layout.putConstraint(SpringLayout.NORTH, titleComponent, 40, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleComponent, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);

        contentPane.add(messageComponent);
        layout.putConstraint(SpringLayout.NORTH, messageComponent, 15, SpringLayout.SOUTH, titleComponent);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, messageComponent, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);

        contentPane.add(inputFileComponent);
        layout.putConstraint(SpringLayout.NORTH, inputFileComponent, 15, SpringLayout.SOUTH, messageComponent);

        contentPane.add(keyFileComponent);
        layout.putConstraint(SpringLayout.NORTH, keyFileComponent, 20, SpringLayout.SOUTH, inputFileComponent);

        contentPane.add(outputFileComponent);
        layout.putConstraint(SpringLayout.NORTH, outputFileComponent, 20, SpringLayout.SOUTH, keyFileComponent);

        contentPane.add(optionComponent);
        layout.putConstraint(SpringLayout.NORTH, optionComponent, 20, SpringLayout.SOUTH, outputFileComponent);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, optionComponent, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);

        contentPane.add(buttonComponent);
        layout.putConstraint(SpringLayout.NORTH, buttonComponent, 20, SpringLayout.SOUTH, optionComponent);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttonComponent, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);

        setVisible(true);
    }

    private void constructContent() {
        titleComponent = new TitleComponent();
        inputFileComponent = new FilePickerComponent("Input", "Browse...", 1);
        keyFileComponent = new FilePickerComponent("Key", "Browse...", 1);
        outputFileComponent = new FilePickerComponent("Output", "Browse...", 2);
        optionComponent = new OptionComponent();
        buttonComponent = new ButtonComponent("Execute!");
        messageComponent = new MessageComponent();

        buttonComponent.addActionListener(this);
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
                    messageField.setForeground(Color.YELLOW);
                    messageField.setText("Doing encryption, please wait!");
                    AESCTRDriver.doEncryption(inputFilePath, keyFilePath, outputFilePath);
                } else if (isDecryptSelected) {
                    messageField.setText("Doing decryption, please wait!");
                    AESCTRDriver.doDecryption(inputFilePath, keyFilePath, outputFilePath);
                } else {
                    messageField.setText("Please choose Encrypt or Decrypt!");
                }
            }
        }
    }
}
