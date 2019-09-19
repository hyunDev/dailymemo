package org.hyunpro.webapp.dailymemo.board.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ReplyId implements Serializable {

    @Column
    private int rno;

    @Column
    private Integer bno;

    @Column
    private LocalDateTime createdDatetime = LocalDateTime.now();
}
