package app.ui;

import com.formdev.flatlaf.FlatDarkLaf;
import infrastructure.database.DatabaseManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final ContentPanel contentPanel;
    private final SidebarPanel sidebarPanel;
    private final StatusBarPanel statusBarPanel;
    private final HeaderPanel headerPanel;

    public MainFrame(DatabaseManager databaseManager) {

        setTitle("ForgeDesk");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 650);
        setMinimumSize(new Dimension(800, 500));
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        contentPanel = new ContentPanel(databaseManager);
        sidebarPanel = new SidebarPanel(contentPanel);
        statusBarPanel = new StatusBarPanel();
        headerPanel = new HeaderPanel();

        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        add(statusBarPanel, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

    }
}
