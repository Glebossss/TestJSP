package com.example.demo.service;

import com.example.demo.dao.model.CalendarEntity;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface CalendarService {

    public List<CalendarEntity> findAllRecord(String email, Pageable pageable);

    public void save(Date startData, Long timeLessons, String email);

    public List<CalendarEntity> findall(String email);

}
