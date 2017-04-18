package cis.view;

import javax.swing.*;
import java.awt.*;

/**
 * The class to create option component - an object having a graphical representation
 * that can be displayed on the screen and that can give a choice to user.
 */
public class OptionComponent extends JPanel {
    private JRadioButton encryptOption;
    private JRadioButton decryptOption;

    /**
     * Constructs the option.
     */
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

    /**
     * Gets the selection of encryption.
     *
     * @return ecryption option you selected.
     */
    public boolean isEncryptSelected() {
        return encryptOption.isSelected();
    }

    /**
     * Gets the selection of decryption.
     *
     * @return deryption option you selected.
     */
    public boolean isDecryptSelected() {
        return decryptOption.isSelected();
    }
}
