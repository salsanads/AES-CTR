package cis.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class to create file picker component - an object having a graphical representation
 * that can be displayed on the screen and that can interact with the user.
 */
public class FilePickerComponent extends JPanel {
    private JLabel label;
    private JTextField textField;
    private JButton button;

    private JFileChooser fileChooser;

    private int mode;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;

    /**
     * Constructs the file picker service with the specified text field label, button label, and mode.
     *
     * @param textFieldLabel a string label for the field, or null for no label.
     * @param buttonLabel a string label for the button, or null for no label.
     * @param mode an integer mode for the file picker mode.
     */
    public FilePickerComponent(String textFieldLabel, String buttonLabel, int mode) {
        this.mode = mode;
        fileChooser = new JFileChooser();

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 150));

        label = new JLabel(textFieldLabel);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Consolas", Font.PLAIN, 14));
        label.setPreferredSize(new Dimension(75, 40));

        textField = new JTextField(30);
        textField.setFont(new Font("Consolas", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(3, 3, 3, 3)));

        button = new JButton(buttonLabel);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Consolas", Font.PLAIN, 14));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        add(label, BorderLayout.LINE_START);
        add(textField, BorderLayout.CENTER);
        add(button, BorderLayout.LINE_END);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param evt the button you clicked.
     */
    private void buttonActionPerformed(ActionEvent evt) {
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }

    /**
     * Gets a user selected path with a file picker.
     *
     * @return the path URL or null if the dialog is canceled.
     */
    public String getSelectedFilePath() {
        return textField.getText();
    }

    /**
     * Gets a text field from file picker.
     *
     * @return the text field part of file picker.
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Gets a button from file picker.
     *
     * @return the button part of file picker.
     */
    public JButton getButton() {
        return button;
    }
}
