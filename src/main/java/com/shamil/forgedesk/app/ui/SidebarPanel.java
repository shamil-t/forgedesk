package com.shamil.forgedesk.app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SidebarPanel extends JPanel {

    SidebarPanel(ContentPanel contentPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 15, 20, 15));
        setPreferredSize(new Dimension(200, 0));
        setBackground(UIManager.getColor("TextArea.background"));

//        JLabel title = new JLabel("Workspace");
//        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 16));
//        title.setForeground(UIManager.getColor("Label.disabledForeground"));
//        title.setAlignmentX(Component.LEFT_ALIGNMENT);
//        add(title, BorderLayout.NORTH);

        add(Box.createRigidArea(new Dimension(0, 20)));

        String[] menus = {"Dashboard", "Vault", "Notes", "Snippets", "Resources", "Search", "Settings"};

        for (String menu : menus) {
            JButton button = new JButton(menu);
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.setMaximumSize(new Dimension(170, 35));
            button.setFocusable(false);
            button.putClientProperty("Button.buttonType", "toolBarButton");

            button.addActionListener(e -> contentPanel.switchView(menu));

            add(button);
            add(Box.createRigidArea(new Dimension(0, 8)));
        }

    }
}
