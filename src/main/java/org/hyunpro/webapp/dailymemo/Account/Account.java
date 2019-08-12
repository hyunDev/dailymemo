package org.hyunpro.webapp.dailymemo.Account;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
//z@Entity
//@EqualsAndHashCode(of = "uid")
public class Account {
    private String id;
    private String email;
    private String password;

}
