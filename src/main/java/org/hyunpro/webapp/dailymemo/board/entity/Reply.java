package org.hyunpro.webapp.dailymemo.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reply")
@NoArgsConstructor
@Data
@IdClass(ReplyId.class)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rno;

    @Id
    private Integer bno;

    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime = LocalDateTime.now();

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String creatorId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDatetime;

}
