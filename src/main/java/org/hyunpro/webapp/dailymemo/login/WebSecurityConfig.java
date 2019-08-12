package org.hyunpro.webapp.dailymemo.login;

import org.hyunpro.webapp.dailymemo.Account.JpaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity //csrf protection이 활성화 됨 아래 http.csrf()는 명시적인것
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JpaAccountService jpaAccountService;

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                //.disable()
                .and()
                .authorizeRequests()
                .antMatchers("board/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/home")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jpaAccountService).passwordEncoder(passwordEncoder());
    }

}
