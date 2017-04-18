package cis.view;

import javax.swing.*;
import java.awt.*;

/**
 * The class to create title component - an object having a graphical representation
 * that can be displayed on the screen as a title.
 */
public class TitleComponent extends JPanel {
    private JLabel title;
    private JLabel subTitle;

    /**
     * Constructs the title.
     */
    public TitleComponent() {
        title = new JLabel("AES Calculator", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Consolas", Font.PLAIN, 36));

        subTitle = new JLabel("Using CTR Mode and PKCS#5", SwingConstants.CENTER);
        subTitle.setForeground(Color.WHITE);
        subTitle.setFont(new Font("Consolas", Font.PLAIN, 24));

        setLayout(new BorderLayout());
        add(title, BorderLayout.PAGE_START);
        add(subTitle, BorderLayout.PAGE_END);
        setBackground(Color.BLACK);
    }
}
