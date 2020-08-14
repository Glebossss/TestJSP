package com.example.demo.dao.repository;

import com.example.demo.dao.model.CalendarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

    @Query("SELECT u FROM CalendarEntity u where u.teacherEntity.email = :email")
    List<CalendarEntity> findByEmail(@Param("email") String email);

    List<CalendarEntity> findByTeacherEntity_Email(String email, Pageable pageable);

    @Query("SELECT u FROM CalendarEntity u where u.dateStart = :dateStart and u.dataEnd = :dataEnd")
    CalendarEntity createOrNo(@Param("dateStart") Date dateStart, @Param("dataEnd") Date dataEnd);

    CalendarEntity findCalendarEntitiesById(Long id);

}
