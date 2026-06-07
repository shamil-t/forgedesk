package com.shamil.forgedesk.app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {
    HeaderPanel() {
        setLayout(new BorderLayout());
        setBackground(UIManager.getColor("ToolBar.background"));
//        setBorder(new EmptyBorder(10, 20, 10, 20));


        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 1, 0,
                        UIManager.getColor("Separator.foreground") // or Color.LIGHT_GRAY
                ),
                new EmptyBorder(10, 20, 10, 20)
        ));


        setPreferredSize(new Dimension(0, 65));

        JLabel title = new JLabel("Engineering Hub");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 14));
        title.setForeground(UIManager.getColor("Label.foreground"));
        add(title, BorderLayout.WEST);

        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(220, 30));

        searchField.putClientProperty("JTextField.placeholderText", "Search");
        searchField.putClientProperty("JTextField.showClearButton", true);
        searchField.putClientProperty("JTextField.leadingIcon", new ImageIcon());

        JPanel searchPanel = new JPanel();
        searchPanel.setOpaque(false);
        searchPanel.add(searchField);

        add(searchPanel, BorderLayout.EAST);

    }
}
