package com.example.demo.serviceImp;

import com.example.demo.dao.model.CalendarEntity;
import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.model.UnconfirmedActivitiesEntity;
import com.example.demo.dao.repository.CalendarRepository;
import com.example.demo.dao.repository.StudentRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.dao.repository.UnconfirmedActivitiesRepository;
import com.example.demo.dto.UnconfirmedActivitiesDTO;
import com.example.demo.service.UnconfirmedActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class UnconfirmedActivitiesServiceImp implements UnconfirmedActivitiesService {

    @Autowired
    UnconfirmedActivitiesRepository unconfirmedActivitiesRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


    @Transient
    @Override
    public List<UnconfirmedActivitiesDTO> findAllUnconfirmedActivitiesForStudent(String email, Pageable pageable) {

        List<UnconfirmedActivitiesEntity> unconfirmedActivitiesEntities = unconfirmedActivitiesRepository.findByStudentEntity_Email(email, pageable);
        List<UnconfirmedActivitiesDTO> unconfirmedActivitiesDTO = new ArrayList<>();
        unconfirmedActivitiesEntities.forEach(x -> unconfirmedActivitiesDTO.add(x.toUnconfirmedActivitiesDTO()));

        return unconfirmedActivitiesDTO;
    }

    @Transient
    @Override
    public List<UnconfirmedActivitiesDTO> findAllUnconfirmedActivitiesForTeacher(String email, Pageable pageable) {

        List<UnconfirmedActivitiesEntity> unconfirmedActivitiesEntities = unconfirmedActivitiesRepository.findByTeacherEntity_Email(email, pageable);
        List<UnconfirmedActivitiesDTO> unconfirmedActivitiesDTO = new ArrayList<>();
        unconfirmedActivitiesEntities.forEach(x -> unconfirmedActivitiesDTO.add(x.toUnconfirmedActivitiesDTO()));

        return unconfirmedActivitiesDTO;
    }

    @Transient
    @Override
    public void save(String emailStudent, Long id) {

        CalendarEntity calendarEntity = calendarRepository.findCalendarEntitiesById(id);

        StudentEntity studentEntity = studentRepository.findByEmail(emailStudent);
        TeacherEntity teacherEntity = calendarEntity.getTeacherEntity();
        UnconfirmedActivitiesEntity unconfirmedActivitiesEntity = new UnconfirmedActivitiesEntity(teacherEntity, studentEntity, calendarEntity.getDateStart(), calendarEntity.getDataEnd(), calendarEntity.getTime(), calendarEntity.getMoney());

        List<UnconfirmedActivitiesEntity> unconfirmedActivitiesEntities = unconfirmedActivitiesRepository.findByUser(unconfirmedActivitiesEntity.getDateStart(), unconfirmedActivitiesEntity.getDateStart(), emailStudent);


        if (unconfirmedActivitiesEntities.isEmpty()) {
            unconfirmedActivitiesRepository.save(unconfirmedActivitiesEntity);
            calendarRepository.delete(calendarEntity);
            sendMessageForAdd(teacherEntity.getEmail(), teacherEntity.getName(), studentEntity.getName());
        }
    }

    @Transient
    @Override
    public void declineActifities(Long id) {

        UnconfirmedActivitiesEntity unconfirmedActivitiesEntity = unconfirmedActivitiesRepository.findUnconfirmedActivitiesEntitiesById(id);
        unconfirmedActivitiesRepository.deleteById(id);

        sendMessageForNot(unconfirmedActivitiesEntity.getStudentEntity().getEmail(), unconfirmedActivitiesEntity.getStudentEntity().getName(), unconfirmedActivitiesEntity.getTeacherEntity().getName());

    }


    @Override
    public List<UnconfirmedActivitiesDTO> findAllStudent(String email) {

        List<UnconfirmedActivitiesDTO> unconfirmedActivitiesDTOS = new ArrayList<>();
        unconfirmedActivitiesRepository.findByEmailStudent(email).forEach(x -> unconfirmedActivitiesDTOS.add(x.toUnconfirmedActivitiesDTO()));

        return unconfirmedActivitiesDTOS;
    }

    @Override
    public List<UnconfirmedActivitiesDTO> findAllTeacher(String email) {

        List<UnconfirmedActivitiesDTO> unconfirmedActivitiesDTOS = new ArrayList<>();
        unconfirmedActivitiesRepository.findByEmailTeacher(email).forEach(x -> unconfirmedActivitiesDTOS.add(x.toUnconfirmedActivitiesDTO()));

        return unconfirmedActivitiesDTOS;
    }

    public void sendMessageForAdd(String email, String nameTeacher, String nameStudent) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("New lesson");
        simpleMailMessage.setText("Hello:\n\n [%s] you have new lesson. Student [%s]");
        simpleMailMessage.setFrom(fromEmail);

        String text = String.format(simpleMailMessage.getText(), nameTeacher, nameStudent);

        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(email);

        emailSender.send(simpleMailMessage);
    }

    public void sendMessageForNot(String email, String nameStudent, String nameTeacher) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Not lesson");
        simpleMailMessage.setText("Hello:\n\n [%s] you were refused [%s]");
        simpleMailMessage.setFrom(fromEmail);

        String text = String.format(simpleMailMessage.getText(), nameTeacher, nameStudent);

        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(email);

        emailSender.send(simpleMailMessage);
    }
}
