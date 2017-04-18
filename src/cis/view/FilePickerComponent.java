package cis.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FilePickerComponent extends JPanel {
    private JLabel label;
    private JTextField textField;
    private JButton button;

    private JFileChooser fileChooser;

    private int mode;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;

    public FilePickerComponent(String textFieldLabel, String buttonLabel, int mode) {
        this.mode = mode;
        fileChooser = new JFileChooser();

        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        setBackground(Color.BLACK);

        label = new JLabel(textFieldLabel);
        label.setForeground(Color.WHITE);

        textField = new JTextField(30);
        button = new JButton(buttonLabel);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        add(label);
        add(textField);
        add(button);

    }

    private void buttonActionPerformed(ActionEvent evt) {
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }

    public String getSelectedFilePath() {
        return textField.getText();
    }
}
