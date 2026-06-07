package com.shamil.forgedesk.vault.domain;

public record Vault(int id,
                    String title,
                    String url,
                    String username,
                    String description,
                    String encrypted_pwd,
                    String iv,
                    Long created_at,
                    Long updated_at) {
}
