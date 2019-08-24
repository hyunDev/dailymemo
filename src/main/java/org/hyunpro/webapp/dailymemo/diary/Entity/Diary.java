package org.hyunpro.webapp.dailymemo.diary.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Diary")
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "date"})
@IdClass(DiaryId.class) //복합키 매핑
@Data
public class Diary {

    @Id
    private String id;

    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String date;

    private String title;

    private String contents;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime = LocalDateTime.now();

}
