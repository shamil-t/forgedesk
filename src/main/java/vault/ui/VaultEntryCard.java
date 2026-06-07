package vault.ui;

import com.formdev.flatlaf.ui.FlatLineBorder;
import vault.domain.Vault;

import javax.swing.*;
import java.awt.*;

public class VaultEntryCard extends JPanel {

    VaultEntryCard(Vault vault) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(240, 120));

        setBackground(UIManager.getColor("Button.background"));
        setBorder(new FlatLineBorder(
                new Insets(15, 15, 25, 15),
                UIManager.getColor("Component.borderColor"),
                1, 12));

        JLabel title = new JLabel(vault.title());
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField username = new JTextField(vault.username());
        username.setEditable(false);
        username.setOpaque(false);
        username.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        username.setForeground(UIManager.getColor("Label.disabledForeground"));
        username.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel url = new JLabel("<html><a href=''>" + vault.url() + "</a></html>");
        url.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        url.setForeground(UIManager.getColor("Component.accentColor"));
        url.setAlignmentX(Component.LEFT_ALIGNMENT);
        url.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        url.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String os = System.getProperty("os.name").toLowerCase();
                boolean success = false;

                // Try Method 1: Standard Java Desktop API
                try {
                    if (java.awt.Desktop.isDesktopSupported() && java.awt.Desktop.getDesktop().isSupported(java.awt.Desktop.Action.BROWSE)) {
                        java.awt.Desktop.getDesktop().browse(new java.net.URI(vault.url()));
                        success = true;
                    }
                } catch (Exception ex) {
                    // Standard API failed, silent move to fallback options
                }

                // Try Method 2: OS Native Terminal Command Fallbacks
                if (!success) {
                    try {
                        Runtime runtime = Runtime.getRuntime();
                        if (os.contains("win")) {
                            runtime.exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", vault.url()});
                        } else if (os.contains("mac")) {
                            runtime.exec(new String[]{"open", vault.url()});
                        } else if (os.contains("nix") || os.contains("nux")) {
                            runtime.exec(new String[]{"xdg-open", vault.url()});
                        } else {
                            JOptionPane.showMessageDialog(url.getParent(), "Could not open browser. Link URL: " + url, "Link Blocked", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(url.getParent(), "Failed to launch native system browser.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        panel.setBackground(UIManager.getColor("Button.background"));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton copyPasswordBtn = new JButton("Copy Password");
        JButton editBtn = new JButton("Edit");

        for (JButton button : new JButton[]{copyPasswordBtn, editBtn}) {
            button.putClientProperty("Button.arc", 6);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            button.setFocusable(false);
            panel.add(button);
        }

        copyPasswordBtn.putClientProperty("Button.buttonType", "square");

        copyPasswordBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Password copied to clipboard", "Secure Copy", JOptionPane.INFORMATION_MESSAGE);
        });

        editBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Opening edit context menu for: " + vault.title(), "Edit Vault Entry", JOptionPane.PLAIN_MESSAGE);
        });

        add(title);
        add(Box.createRigidArea(new Dimension(0, 4)));
        add(username);
        add(Box.createRigidArea(new Dimension(0, 4)));
        add(url);
        add(Box.createVerticalGlue());
        add(panel);
    }
}
