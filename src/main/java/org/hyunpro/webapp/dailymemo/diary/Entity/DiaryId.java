package org.hyunpro.webapp.dailymemo.diary.Entity;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class DiaryId implements Serializable {

    @Column
    private String id;

    @Column
    private String date;
}
