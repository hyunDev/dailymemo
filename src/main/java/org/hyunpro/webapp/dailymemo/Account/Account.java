package org.hyunpro.webapp.dailymemo.Account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Account")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Account {

    @Id
    private String id;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    @CreationTimestamp
    private Date regdate;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="uid")
    private List<AccountRole> roles;
}

