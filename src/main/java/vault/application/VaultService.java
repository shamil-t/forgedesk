package vault.application;

import vault.domain.Vault;
import vault.infrastucture.repositories.VaultRepository;

import java.util.Date;
import java.util.List;

public class VaultService {
    private final VaultRepository vaultRepository;

    public VaultService(VaultRepository vaultRepository) {
        this.vaultRepository = vaultRepository;
    }

    public Vault createNewVaultEntry(Vault vault) {

        String encrypted_pwd = "";
        String iv = "";
        Date date = new Date();


        vaultRepository.insert(vault);

        return vault;
    }

    public List<Vault> getAllVaults() {
        return vaultRepository.findAll();
    }
}
