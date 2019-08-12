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
@EqualsAndHashCode(of = "uid")
@Data
public class JpaAccount {

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
    private List<JpaAccountRole> roles;
}

