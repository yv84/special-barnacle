package me.yv84.specialbarnacle.authumbrella.config;

import java.util.Map;

import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;

public class CustomClaimVerifier  implements JwtClaimsSetVerifier  {
    
    @Override
    public void verify(Map<String, Object> claims) throws InvalidTokenException {
        final String username = (String) claims.get("username");
        if ((username != null) && (username.length() == 0)) {
            throw new InvalidTokenException("user_name claim is empty");
        }
    }
    
}
