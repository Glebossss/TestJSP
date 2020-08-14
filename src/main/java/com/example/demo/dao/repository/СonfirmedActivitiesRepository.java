package com.example.demo.dao.repository;

import com.example.demo.dao.model.СonfirmedActivitiesEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface СonfirmedActivitiesRepository extends JpaRepository<СonfirmedActivitiesEntity, Long> {

    List<СonfirmedActivitiesEntity> findByTeacherEntity_Email(String email, Pageable pageable);

    List<СonfirmedActivitiesEntity> findByStudentEntity_Email(String email, Pageable pageable);

    @Query("SELECT u FROM СonfirmedActivitiesEntity u where u.studentEntity.email = :email")
    List<СonfirmedActivitiesEntity> findByEmailStudent(@Param("email") String email);

    @Query("SELECT u FROM СonfirmedActivitiesEntity u where u.teacherEntity.email = :email")
    List<СonfirmedActivitiesEntity> findByEmailTeacher(@Param("email") String email);


}
