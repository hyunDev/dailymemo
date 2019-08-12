package org.hyunpro.webapp.dailymemo.Account;


import org.hyunpro.webapp.dailymemo.board.entity.BoardEntity;
import org.hyunpro.webapp.dailymemo.board.entity.BoardFileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class JpaAccountService implements UserDetailsService{

    @Autowired
    private JpaAccountRepository jpaAccountRepository;


    public void saveAccount(JpaAccount account, HttpServletRequest httpServletRequest) throws Exception {
        JpaAccountRole role = new JpaAccountRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        role.setRoleName("USER");
        account.setRoles(Arrays.asList(role));
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        jpaAccountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JpaAccount account = jpaAccountRepository.findById(username);

        if(account == null)
            throw new UsernameNotFoundException(username);

        SecurityAccount securityAccount = new SecurityAccount(account);
        return securityAccount;
    }
}
