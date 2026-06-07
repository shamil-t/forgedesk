
import com.shamil.forgedesk.app.ui.MainFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import com.shamil.forgedesk.infrastructure.database.DatabaseManager;
import com.shamil.forgedesk.infrastructure.database.MigrationRunner;

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
