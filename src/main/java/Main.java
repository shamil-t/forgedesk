import app.ui.MainFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import infrastructure.database.DatabaseManager;
import infrastructure.database.MigrationRunner;

import javax.swing.*;

void main() {

    try {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        FlatDarkLaf.setup();
    } catch (Exception e) {
        IO.println(e.getMessage());
    }

    try {
        DatabaseManager databaseManager = new DatabaseManager();
        MigrationRunner.migrate(databaseManager);
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(databaseManager);
            mainFrame.setVisible(true);
        });

    } catch (Exception e) {
        IO.println(e.getMessage());
    }

}
