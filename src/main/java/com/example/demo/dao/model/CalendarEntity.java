package com.example.demo.dao.model;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "calendar")
public class CalendarEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private TeacherEntity teacherEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEnd;

    private Long time;

    private Integer money;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");


    public CalendarEntity(TeacherEntity teacherEntity, Date date, Date dataEnd, Long time, Integer money) {
        this.teacherEntity = teacherEntity;
        this.dateStart = date;
        this.dataEnd = dataEnd;
        this.time = time;
        this.money = money;
    }

    public CalendarEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Date getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(Date dataEnd) {
        this.dataEnd = dataEnd;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "CalendarEntity{" +
                "id=" + id +
                ", teacherEntity=" + teacherEntity.getEmail() +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dataEnd +
                ", time=" + time +
                '}';
    }

}
