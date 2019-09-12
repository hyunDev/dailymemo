package org.hyunpro.webapp.dailymemo.diary.Entity;

import javax.persistence.Column;
import java.io.Serializable;

public class DiaryFileId implements Serializable {

    @Column
    private String id;

    @Column
    private String date;

    @Column
    private int idx;
}
