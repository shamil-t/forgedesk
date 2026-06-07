package app.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatusBarPanel extends JPanel {
    public StatusBarPanel() {
        setLayout(new BorderLayout());
        setBackground(UIManager.getColor("Panel.background"));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(
                        2, 0, 0, 0,
                        UIManager.getColor("Separator.foreground") // or Color.LIGHT_GRAY
                ),
                new EmptyBorder(10, 20, 10, 20)
        ));

        setPreferredSize(new Dimension(0, 36));

        JLabel statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(UIManager.getColor("Label.disabledForeground"));
        add(statusLabel, BorderLayout.WEST);

        JLabel infoLabel = new JLabel("SQLite DB Connected");
        infoLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        infoLabel.setForeground(UIManager.getColor("Component.accentColor"));

        add(infoLabel, BorderLayout.EAST);
    }
}
