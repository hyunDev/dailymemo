package org.hyunpro.webapp.dailymemo.board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="t_jpa_board")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int boardIdx;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new LinkedList<Comment>();

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setBoard(this);
    }

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int hitCnt = 0;

    @Column(nullable = false)
    private String creatorId;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime = LocalDateTime.now();

    private String updaterId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDatetime;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="board_idx")
    private Collection<BoardFileEntity> fileList;
}
