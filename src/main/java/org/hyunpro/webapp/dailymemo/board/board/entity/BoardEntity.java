package org.hyunpro.webapp.dailymemo.board.board.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hyunpro.webapp.dailymemo.board.board.dto.BoardFileDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="t_jpa_board")
@NoArgsConstructor
@Data
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int boardIdx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int hitCnt = 0;

    @Column(nullable = false)
    private String creatorId;

    @Column(nullable = false)
    private LocalDateTime createdDatetime = LocalDateTime.now();

    private String updaterId;

    private LocalDateTime updatedDatetime;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="board_idx")
    private Collection<BoardFileEntity> fileList;
}
