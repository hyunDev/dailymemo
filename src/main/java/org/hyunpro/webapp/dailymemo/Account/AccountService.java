package org.hyunpro.webapp.dailymemo.Account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class AccountService implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void saveAccount(Account account, HttpServletRequest httpServletRequest) throws Exception {
        AccountRole role = new AccountRole();
        role.setRoleName("USER");
        account.setRoles(Arrays.asList(role));
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findById(username);

        if(account == null)
            throw new UsernameNotFoundException(username);

        SecurityAccount securityAccount = new SecurityAccount(account);
        return securityAccount;
    }
}
