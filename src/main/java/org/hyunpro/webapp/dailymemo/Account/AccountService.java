package org.hyunpro.webapp.dailymemo.Account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AccountService implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void saveAccount(Account account) {
        AccountRole role = new AccountRole();
        role.setRoleName("USER");
        account.setRoles(Arrays.asList(role));
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public void updatePw(Account account){
        accountRepository.updatePw(account.getId(), passwordEncoder.encode(account.getPassword()));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findById(username);
        UserDetails userDetails = null;

        if(account == null)
            return userDetails;
            //throw new UsernameNotFoundException(username);

        SecurityAccount securityAccount = new SecurityAccount(account);
        return securityAccount;
    }

    public String getEmail(String username) throws Exception{
        Account account = accountRepository.findById(username);
        return account.getEmail();
    }

    public String getTempPw(){
        String tempPw = "";

        for (int i = 0; i < 12; i++) {
            tempPw += (char) ((Math.random() * 26) + 97);
        }

        return tempPw;
    }

    public boolean isSamePw(String pw1, String pw2){

        if(passwordEncoder.matches(pw2, pw1))
            return true;
        else
            return false;
    }
}
