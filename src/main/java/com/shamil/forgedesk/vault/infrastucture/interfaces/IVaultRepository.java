package com.shamil.forgedesk.vault.infrastucture.interfaces;

import com.shamil.forgedesk.vault.domain.Vault;

import java.util.List;
import java.util.Optional;

public interface IVaultRepository {
    void insert(Vault vault);

    Optional<Vault> findById(Long id);

    List<Vault> findAll();

    void update(Vault vault);

    void delete(int id);
}
