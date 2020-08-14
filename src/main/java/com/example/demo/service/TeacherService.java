package com.example.demo.service;

import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dto.TeacherDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    public List<TeacherDTO> findAllTeacher();

    public void save(TeacherEntity teacherEntity);

    public void update(long id, int price, String email);

    public TeacherDTO findOne(String email);

    public List<TeacherDTO> findAllTeacherByPage(Pageable pageable);

}
