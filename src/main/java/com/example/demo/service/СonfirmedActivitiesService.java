package com.example.demo.service;

import com.example.demo.dto.СonfirmedActivitiesDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface СonfirmedActivitiesService {

    public List<СonfirmedActivitiesDTO> findAllUnconfirmedActivitiesForStudent(String email, Pageable pageable);

    public List<СonfirmedActivitiesDTO> findAllUnconfirmedActivitiesForTeacher(String email, Pageable pageable);

    public void save(Long id);

    public List<СonfirmedActivitiesDTO> findAllStudent(String email);

    public List<СonfirmedActivitiesDTO> findAllTeacher(String email);

    public void del(Long id);
}
