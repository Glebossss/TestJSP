package com.example.demo.serviceImp;

import com.example.demo.dao.model.CalendarEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.repository.CalendarRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class CalendarServiceImp implements CalendarService {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    TeacherRepository teacherRepository;


    @Override
    public List<CalendarEntity> findAllRecord(String email, Pageable pageable) {

        return calendarRepository.findByTeacherEntity_Email(email, pageable);
    }

    @Override
    public void save(Date startData, Long timeLessons, String email) {

        TeacherEntity teacherEntity = teacherRepository.findByEmail(email);
        Integer money;

        final java.util.Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(startData);
        cal.add(GregorianCalendar.MINUTE, 15);

        Date dataEnd = cal.getTime();

        if (timeLessons == 15)
            money = teacherEntity.getPrice() / 6 + (teacherEntity.getPrice() / 15);

        else if (timeLessons == 30)
            money = teacherEntity.getPrice() / 3 + (teacherEntity.getPrice() / 30);

        else if (timeLessons == 60)
            money = teacherEntity.getPrice() + (teacherEntity.getPrice() / 60);

        else
            money = teacherEntity.getPrice();

        CalendarEntity calendarEntity = new CalendarEntity(teacherEntity, startData, dataEnd, timeLessons, money);

        if (calendarRepository.createOrNo(startData, dataEnd) == null)
            calendarRepository.save(calendarEntity);

    }

    @Override
    public List<CalendarEntity> findall(String email) {

        return calendarRepository.findByEmail(email);
    }
}
