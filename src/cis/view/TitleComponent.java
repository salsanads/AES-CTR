package cis.view;

import javax.swing.*;
import java.awt.*;

public class TitleComponent extends JPanel {
    private JLabel title;
    private JLabel subTitle;

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
