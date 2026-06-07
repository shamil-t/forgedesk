package vault.infrastucture.repositories;

import infrastructure.database.DatabaseManager;
import vault.domain.Vault;
import vault.infrastucture.interfaces.IVaultRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VaultRepository implements IVaultRepository {

    private final DatabaseManager db;

    public VaultRepository(DatabaseManager db) {
        this.db = db;
    }

    @Override
    public void insert(Vault vault) {
        try (Connection connection = db.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("""
                    INSERT INTO vault_entries(title, username, url, description, encrypted_pwd, iv,created_at,updated_at)
                    VALUES (?, ?, ?, ?, ?, ?,?,?)
                    """);

            ps.setString(1, vault.title());
            ps.setString(2, vault.username());
            ps.setString(3, vault.url());
            ps.setString(4, vault.description());
            ps.setString(5, vault.encrypted_pwd());
            ps.setString(6, vault.iv());
            ps.setString(7, String.valueOf(vault.created_at()));
            ps.setString(8, String.valueOf(vault.updated_at()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Vault> findById(Long id) {

        return Optional.empty();
    }

    @Override
    public List<Vault> findAll() {
        List<Vault> result = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM vault_entries");

            while (rs.next()) {
                result.add(new Vault(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("title"),
                        rs.getString("url"),
                        rs.getString("username"),
                        rs.getString("description"),
                        rs.getString("encrypted_pwd"),
                        rs.getString("iv"),
                        Long.parseLong(rs.getString("created_at")),
                        Long.parseLong(rs.getString("updated_at"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void update(Vault vault) {

    }

    @Override
    public void delete(int id) {

    }
}
