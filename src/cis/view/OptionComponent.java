package cis.view;

import javax.swing.*;
import java.awt.*;

public class OptionComponent extends JPanel {
    private JRadioButton encryptOption;
    private JRadioButton decryptOption;

    public OptionComponent() {
        encryptOption = new JRadioButton("Encrypt");
        encryptOption.setFont(new Font("Consolas", Font.PLAIN, 14));
        encryptOption.setForeground(Color.WHITE);
        encryptOption.setBackground(Color.BLACK);
        encryptOption.setFocusPainted(false);

        decryptOption = new JRadioButton("Decrypt");
        decryptOption.setFont(new Font("Consolas", Font.PLAIN, 14));
        decryptOption.setForeground(Color.WHITE);
        decryptOption.setBackground(Color.BLACK);
        decryptOption.setFocusPainted(false);

        ButtonGroup group = new ButtonGroup();
        group.add(encryptOption);
        group.add(decryptOption);

        add(encryptOption);
        add(decryptOption);

        setBackground(Color.BLACK);
    }

    public boolean isEncryptSelected() {
        return encryptOption.isSelected();
    }

    public boolean isDecryptSelected() {
        return decryptOption.isSelected();
    }
}
