package infrastructure.database;

import java.sql.Connection;
import java.sql.Statement;

public class MigrationRunner {
    public static void migrate(DatabaseManager db) {
        try (Connection connection = db.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS "vault_entries" (
                    	"id"	INTEGER,
                    	"title"	TEXT NOT NULL,
                    	"username"	TEXT,
                    	"url"	TEXT,
                    	"description"	TEXT,
                    	"encrypted_pwd"	BLOB NOT NULL,
                    	"iv"	BLOB NOT NULL,
                    	"created_at"	INTEGER NOT NULL,
                    	"updated_at"	INTEGER NOT NULL,
                    	PRIMARY KEY("id" AUTOINCREMENT)
                    )
                    """);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
