package cis.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

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
