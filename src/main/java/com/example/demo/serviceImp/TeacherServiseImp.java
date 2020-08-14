package com.example.demo.serviceImp;

import com.example.demo.dao.model.SubjectEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.repository.SubjectRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.dto.TeacherDTO;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiseImp implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;


    @Transactional
    @Override
    public List<TeacherDTO> findAllTeacher() {

        List<TeacherDTO> teacherDTOList = new ArrayList<>();

        List<TeacherEntity> teacherEntities = teacherRepository.findAll();

        teacherEntities.forEach(x -> teacherDTOList.add(x.toTeacherDTO()));

        return teacherDTOList;
    }


    @Transactional
    @Override
    public void update(long id, int price, String email) {

        TeacherEntity teacherEntity = teacherRepository.findByEmail(email);

        teacherEntity.setPrice(price);

        SubjectEntity subjectEntity = subjectRepository.findByID(id);

        teacherEntity.setSubjectEntity(subjectEntity);
    }

    @Transactional
    @Override
    public void save(TeacherEntity teacherEntity) {

        if (teacherRepository.existsByEmail(teacherEntity.getEmail()))
            return;

        teacherRepository.save(teacherEntity);
    }

    @Transactional
    @Override
    public TeacherDTO findOne(String email) {

        TeacherEntity teacherEntity = teacherRepository.findByEmail(email);
        TeacherDTO teacherDTO = teacherEntity.toTeacherDTO();

        return teacherDTO;
    }

    @Transactional
    @Override
    public List<TeacherDTO> findAllTeacherByPage(Pageable pageable) {

        List<TeacherDTO> teacherDTOList = new ArrayList<>();

        teacherRepository.findTeacherEntitiesBy(pageable).forEach(x -> {

            if (x.getPrice() == null)
                x.setPrice(0);

            teacherDTOList.add(x.toTeacherDTO());

        });
        return teacherDTOList;
    }
}
