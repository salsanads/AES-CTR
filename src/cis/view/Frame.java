package cis.view;

import cis.app.AESCTRDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    private static Dimension size;

    private static TitleComponent titleComponent;
    private static MessageComponent messageComponent;
    private static FilePickerComponent inputFileComponent;
    private static FilePickerComponent keyFileComponent;
    private static HelpTextComponent helpTextComponent;
    private static FilePickerComponent outputFileComponent;

    private static JPanel buttonContainer;
    private static ButtonComponent encryptionButton;
    private static ButtonComponent decryptionButton;

    public Frame() {
        size = new Dimension(750, 500);

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

        contentPane.add(helpTextComponent);
        layout.putConstraint(SpringLayout.NORTH, helpTextComponent, 0, SpringLayout.SOUTH, keyFileComponent);
        layout.putConstraint(SpringLayout.WEST, helpTextComponent, 220, SpringLayout.WEST, contentPane);

        contentPane.add(outputFileComponent);
        layout.putConstraint(SpringLayout.NORTH, outputFileComponent, 5, SpringLayout.SOUTH, helpTextComponent);

        contentPane.add(buttonContainer);
        layout.putConstraint(SpringLayout.NORTH, buttonContainer, 20, SpringLayout.SOUTH, outputFileComponent);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttonContainer, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);

        setVisible(true);
    }

    private void constructContent() {
        titleComponent = new TitleComponent();
        inputFileComponent = new FilePickerComponent("Input", "Browse...", 1);
        keyFileComponent = new FilePickerComponent("Key", "Browse...", 1);
        outputFileComponent = new FilePickerComponent("Output", "Browse...", 2);
        helpTextComponent = new HelpTextComponent();

        buttonContainer = new JPanel();
        buttonContainer.setLayout(new FlowLayout());
        buttonContainer.setBackground(Color.BLACK);
        encryptionButton = new ButtonComponent("Encrypt!", new Color(255, 100, 0));
        decryptionButton = new ButtonComponent("Decrypt!", new Color(65,105,225));

        messageComponent = new MessageComponent();

        buttonContainer.add(encryptionButton);
        buttonContainer.add(decryptionButton);
        encryptionButton.addActionListener(this);
        decryptionButton.addActionListener(this);
    }

    public static void main(String[] args) {
        new Frame();
    }

    public static MessageComponent getMessageComponent() {
        return messageComponent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel messageField = messageComponent.getMessageField();
        messageField.setForeground(Color.RED);

        String inputFilePath = inputFileComponent.getSelectedFilePath();
        String keyFilePath = keyFileComponent.getSelectedFilePath();
        String outputFilePath = outputFileComponent.getSelectedFilePath();

        if (inputFilePath.equals("")) {
            messageField.setText("Please pick input file!");
        } else if (keyFilePath.equals("")) {
            messageField.setText("Please pick key file!");
        } else if (outputFilePath.equals("")) {
            messageField.setText("Please pick output file!");
        } else {
            if (e.getSource() == encryptionButton) {
                messageField.setForeground(Color.YELLOW);
                messageField.setText("Doing encryption, please wait!");
                AESCTRDriver.doEncryption(inputFilePath, keyFilePath, outputFilePath);
            } else if (e.getSource() == decryptionButton) {
                messageField.setText("Doing decryption, please wait!");
                AESCTRDriver.doDecryption(inputFilePath, keyFilePath, outputFilePath);
            }
        }
    }
}
