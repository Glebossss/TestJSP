package com.example.demo.serviceImp;

import com.example.demo.dao.model.UnconfirmedActivitiesEntity;
import com.example.demo.dao.model.СonfirmedActivitiesEntity;
import com.example.demo.dao.repository.UnconfirmedActivitiesRepository;
import com.example.demo.dao.repository.СonfirmedActivitiesRepository;
import com.example.demo.dto.СonfirmedActivitiesDTO;
import com.example.demo.service.СonfirmedActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class СonfirmedActivitiesServiceImp implements СonfirmedActivitiesService {

    @Autowired
    СonfirmedActivitiesRepository confirmedActivitiesRepository;


    @Autowired
    UnconfirmedActivitiesRepository unconfirmedActivitiesRepository;

    @Transient
    @Override
    public List<СonfirmedActivitiesDTO> findAllUnconfirmedActivitiesForStudent(String email, Pageable pageable) {

        List<СonfirmedActivitiesEntity> unconfirmedActivitiesEntities = confirmedActivitiesRepository.findByStudentEntity_Email(email, pageable);
        List<СonfirmedActivitiesDTO> сonfirmedActivitiesDTOS = new ArrayList<>();
        unconfirmedActivitiesEntities.forEach(x -> сonfirmedActivitiesDTOS.add(x.toConfirmedActivitiesDTO()));

        return сonfirmedActivitiesDTOS;
    }

    @Transient
    @Override
    public List<СonfirmedActivitiesDTO> findAllUnconfirmedActivitiesForTeacher(String email, Pageable pageable) {

        List<СonfirmedActivitiesEntity> сonfirmedActivitiesEntity = confirmedActivitiesRepository.findByTeacherEntity_Email(email, pageable);
        List<СonfirmedActivitiesDTO> сonfirmedActivitiesDTOS = new ArrayList<>();
        сonfirmedActivitiesEntity.forEach(x -> сonfirmedActivitiesDTOS.add(x.toConfirmedActivitiesDTO()));


        return сonfirmedActivitiesDTOS;
    }

    @Transient
    @Override
    public void save(Long id) {

        UnconfirmedActivitiesEntity unconfirmedActivitiesEntity = unconfirmedActivitiesRepository.findUnconfirmedActivitiesEntitiesById(id);

        СonfirmedActivitiesEntity сonfirmedActivitiesEntity = new СonfirmedActivitiesEntity(unconfirmedActivitiesEntity.getTeacherEntity(), unconfirmedActivitiesEntity.getStudentEntity(),
                unconfirmedActivitiesEntity.getDateStart(), unconfirmedActivitiesEntity.getDataEnd(), unconfirmedActivitiesEntity.getTime(), unconfirmedActivitiesEntity.getMoney());
        confirmedActivitiesRepository.save(сonfirmedActivitiesEntity);
        unconfirmedActivitiesRepository.delete(unconfirmedActivitiesEntity);

    }

    @Transient
    @Override
    public List<СonfirmedActivitiesDTO> findAllStudent(String email) {

        List<СonfirmedActivitiesDTO> сonfirmedActivitiesEntities = new ArrayList<>();
        confirmedActivitiesRepository.findByEmailStudent(email).forEach(x -> сonfirmedActivitiesEntities.add(x.toConfirmedActivitiesDTO()));

        return сonfirmedActivitiesEntities;
    }

    @Transient
    @Override
    public List<СonfirmedActivitiesDTO> findAllTeacher(String email) {

        List<СonfirmedActivitiesDTO> сonfirmedActivitiesDTOS = new ArrayList<>();
        confirmedActivitiesRepository.findByEmailTeacher(email).forEach(x -> сonfirmedActivitiesDTOS.add(x.toConfirmedActivitiesDTO()));

        return сonfirmedActivitiesDTOS;
    }


    @Transient
    @Override
    public void del(Long id) {
        confirmedActivitiesRepository.deleteById(id);
    }
}
