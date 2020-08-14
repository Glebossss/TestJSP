package com.example.demo.serviceImp;

import com.example.demo.dao.model.AllUserEntity;
import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.model.enums.RoleUser;
import com.example.demo.dao.repository.AllUserRepository;
import com.example.demo.dao.repository.StudentRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.dto.AllUserDTO;
import com.example.demo.service.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllUserServiceImp implements AllUserService {

    @Autowired
    AllUserRepository allUserRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    private static final String ROLE = "ROLE_STUDENT";

    @Transient
    @Override
    public List<AllUserEntity> findAllUser() {
        return allUserRepository.findAll();
    }

    @Transient
    @Override
    public void save(AllUserEntity allUser) {
        if (allUserRepository.existsByEmail(allUser.getEmail()))
            return;
        allUserRepository.save(allUser);
    }

    @Transient
    @Override
    public void update(String email) {

        String rool = allUserRepository.findByEmail(email).getRole().toString();

        if (rool.equals(ROLE)) {
            AllUserEntity allUserEntity = allUserRepository.findByEmail(email);
            allUserEntity.setRole(RoleUser.TEACHER);

            StudentEntity studentEntity = studentRepository.findByEmail(email);
            TeacherEntity teacherEntity = new TeacherEntity(allUserEntity.getEmail(),
                    allUserEntity.getName(), allUserEntity.getPictureURL());

            teacherRepository.save(teacherEntity);

            studentRepository.delete(studentEntity);
        } else {
            AllUserEntity allUserEntity = allUserRepository.findByEmail(email);
            allUserEntity.setRole(RoleUser.STUDENT);

            TeacherEntity teacherEntity = teacherRepository.findByEmail(email);
            StudentEntity studentEntity = new StudentEntity(allUserEntity.getEmail(),
                    allUserEntity.getName(), allUserEntity.getPictureURL());

            studentRepository.save(studentEntity);

            teacherRepository.delete(teacherEntity);
        }
    }


    @Transient
    @Override
    public AllUserEntity findByLogin(String email) {

        return allUserRepository.findByEmail(email);
    }

    @Transient
    @Override
    public List<AllUserDTO> findAllUserPagebal(Pageable pageable) {

        List<AllUserDTO> allUserDTOS = new ArrayList<>();

        allUserRepository.findAllUserEntitiesBy(pageable).forEach(x -> allUserDTOS.add(x.toAllUserDTO()));
        return allUserDTOS;
    }
}
