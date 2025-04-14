package mk.ukim.finki.emtlabb.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_HOST;

    @Override
    public String getAuthority() {
        return name();
    }
}
