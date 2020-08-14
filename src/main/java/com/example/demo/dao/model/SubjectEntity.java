package com.example.demo.dao.model;


import com.example.demo.dto.SubjectDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject_table")
public class SubjectEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public SubjectEntity(String name) {
        this.name = name;
    }

    public SubjectEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubjectEntity() {
    }

    @OneToMany(mappedBy = "subjectEntity", cascade = CascadeType.ALL)
    private List<TeacherEntity> teacherEntityList = new ArrayList<>();

    public static SubjectEntity of(String name) {
        return new SubjectEntity(name);
    }

    public static SubjectEntity fromSubjectEntity(SubjectDTO subjectDTO) {
        return of(subjectDTO.getName());
    }

    public SubjectDTO toSubjectDTO() {
        return SubjectDTO.of(id, name);
    }

    public static SubjectEntity fromDTO(SubjectDTO subjectDTO) {
        return new SubjectEntity(subjectDTO.getName());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TeacherEntity> getTeacherEntityList() {
        return teacherEntityList;
    }

    public void setTeacherEntityList(List<TeacherEntity> teacherEntityList) {
        this.teacherEntityList = teacherEntityList;
    }

    @Override
    public String toString() {
        return name;
    }
}

