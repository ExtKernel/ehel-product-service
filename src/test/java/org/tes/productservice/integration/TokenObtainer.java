package org.tes.productservice.integration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenObtainer {

    private String clientId;
    private String grantType;
    private String username;
    private String password;
}
