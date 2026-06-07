package com.shamil.forgedesk.vault.ui;

import com.shamil.forgedesk.app.ui.WrapLayout;
import com.shamil.forgedesk.vault.application.VaultService;
import com.shamil.forgedesk.vault.domain.Vault;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VaultPanel extends JPanel {

    private final VaultService vaultService;
    JPanel listVaultPanel;

    public VaultPanel(VaultService vaultService) {

        this.vaultService = vaultService;

        setLayout(new BorderLayout());
        setBackground(UIManager.getColor("Panel.background"));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        headerPanel.setBackground(UIManager.getColor("Panel.background"));

        JLabel title = new JLabel("Password Vault");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JButton newEntryButton = getNewVaultEntryBtn();

        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(newEntryButton, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        listVaultPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 15, 15));
        listVaultPanel.setBackground(UIManager.getColor("Panel.background"));
        listVaultPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        loadVault();

        add(listVaultPanel, BorderLayout.CENTER);
    }

    private JButton getNewVaultEntryBtn() {
        JButton newEntryButton = new JButton("New Entry");
        newEntryButton.putClientProperty("Button.buttonType", "accent");
        newEntryButton.putClientProperty("Button.arc", 8);

        newEntryButton.addActionListener(e -> {
            NewVaultDialog dialog = new NewVaultDialog(SwingUtilities.getWindowAncestor(VaultPanel.this));
            dialog.setVisible(true);

            Vault newVault = dialog.getResult();
            if (newVault != null) {
                saveVault(newVault);
            }
        });
        return newEntryButton;
    }

    private void saveVault(Vault newVault) {
        Vault vault = vaultService.createNewVaultEntry(newVault);

        listVaultPanel.add(new VaultEntryCard(vault));
        listVaultPanel.revalidate();
        listVaultPanel.repaint();
    }

    private void loadVault() {
        vaultService.getAllVaults().forEach(vault -> {
            listVaultPanel.add(new VaultEntryCard(vault));
        });
    }
}
