package com.shamil.forgedesk.app.ui;

import com.shamil.forgedesk.infrastructure.database.DatabaseManager;
import com.shamil.forgedesk.vault.application.VaultService;
import com.shamil.forgedesk.vault.infrastucture.repositories.VaultRepository;
import com.shamil.forgedesk.vault.ui.VaultPanel;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    private CardLayout cardLayout;

    ContentPanel(DatabaseManager databaseManager) {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        VaultRepository vaultRepository = new VaultRepository(databaseManager);

        add(createPlaceholderPanel("Dashboard Content"), "Dashboard");
        add(new VaultPanel(new VaultService(vaultRepository)), "Vault");
        add(createPlaceholderPanel("Notes Contest"), "Notes");
        add(createPlaceholderPanel("Snippets Content"), "Snippets");
        add(createPlaceholderPanel("Resources Account"), "Resources");
        add(createPlaceholderPanel("Search Development"), "Search");
        add(createPlaceholderPanel("Settings Development"), "Settings");

    }

    // Call this method from the Sidebar to flip between views
    public void switchView(String cardName) {
        cardLayout.show(this, cardName);
    }

    // Helper for quick placeholders until you build separate classes for all views
    private JPanel createPlaceholderPanel(String text) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(new JLabel(text));
        return panel;
    }
}
