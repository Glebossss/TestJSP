package com.example.demo.dto;


import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.model.TeacherEntity;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class СonfirmedActivitiesDTO {

    private Long id;
    private TeacherEntity teacherEntity;
    private StudentEntity studentEntity;
    private Date dateStart;
    private Date dataEnd;
    private Long time;
    private Integer money;

    public СonfirmedActivitiesDTO(Long id, TeacherEntity teacherEntity, StudentEntity studentEntity, Date dateStart, Date dataEnd, Long time, Integer money) {
        this.id = id;
        this.teacherEntity = teacherEntity;
        this.studentEntity = studentEntity;
        this.dateStart = dateStart;
        this.dataEnd = dataEnd;
        this.time = time;
        this.money = money;
    }


    public static СonfirmedActivitiesDTO of(Long id, TeacherEntity teacherEntity, StudentEntity studentEntity, Date dateStart, Date dataEnd, Long time, Integer money) {
        return new СonfirmedActivitiesDTO(id, teacherEntity, studentEntity, dateStart, dataEnd, time, money);
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

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(Date dataEnd) {
        this.dataEnd = dataEnd;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
