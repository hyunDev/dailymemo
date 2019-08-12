package org.hyunpro.webapp.dailymemo.Account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SecurityAccount extends User {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final long serialVersionUID = 1L;

    public SecurityAccount(JpaAccount account){
        super(account.getId(), account.getPassword(), makeGrantedAuthority(account.getRoles()));
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<JpaAccountRole> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
        return list;
    }

}
