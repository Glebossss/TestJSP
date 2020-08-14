package com.example.demo.dto;


import com.example.demo.dao.model.CalendarEntity;
import com.example.demo.dao.model.SubjectEntity;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
public class TeacherDTO {

    private Long id;
    private String email;
    private String name;
    private String pictureURL;
    private Integer price;
    private SubjectEntity subjectEntity;
    private List<CalendarEntity> calendarEntity;

    public TeacherDTO(Long id, String email, String name, String pictureURL, Integer price, SubjectEntity subjectEntity, List<CalendarEntity> calendarEntity) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
        this.price = price;
        this.subjectEntity = subjectEntity;
        this.calendarEntity = calendarEntity;
    }

    public static TeacherDTO of(Long id, String email, String name, String pictureURL, Integer price, SubjectEntity subjectEntity, List<CalendarEntity> calendarEntity) {
        return new TeacherDTO(id, email, name, pictureURL, price, subjectEntity, calendarEntity);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }


    public void setSubjectEntity(SubjectEntity subjectEntity) {
        this.subjectEntity = subjectEntity;
    }

    public List<CalendarEntity> getCalendarEntity() {
        return calendarEntity;
    }

    public void setCalendarEntity(List<CalendarEntity> calendarEntity) {
        this.calendarEntity = calendarEntity;
    }
}
