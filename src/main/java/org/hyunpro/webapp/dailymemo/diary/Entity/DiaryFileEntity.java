package org.hyunpro.webapp.dailymemo.diary.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="diaryFile")
@NoArgsConstructor
@IdClass(DiaryFileId.class) //복합키 매핑
@Data
public class DiaryFileEntity {
    @Id
    private String id;

    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns(value={
            @JoinColumn(name = "id",  updatable = false, insertable = false)
            ,@JoinColumn(name="date", updatable = false, insertable = false)
    }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Diary diary;

    @Column(nullable=false)
    private String originalFileName;

    @Column(nullable=false)
    private String storedFilePath;

    @Column(nullable=false)
    private long fileSize;

    @Column(nullable=false)
    private String creatorId;

    @Column(nullable=false)
    private LocalDateTime createdDatetime = LocalDateTime.now();

    private String updaterId;

    private LocalDateTime updatedDatetime;
}
