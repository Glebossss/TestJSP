package com.example.demo.configs;

import com.example.demo.dao.model.AllUserEntity;
import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.model.enums.RoleUser;
import com.example.demo.email.EmailService;
import com.example.demo.service.AllUserService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class Handlers implements AuthenticationSuccessHandler, EmailService {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    AllUserService allUserService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ApplicationContext applicationContext;

    private static final String EMAILTEACHER = "glebgomenyuk@gmail.com";
    private static final String EMAILTEACHERSECOND = "gomenyukgleb@gmail.com";

    //Method for sending message
    @Override
    public void sendMessageForRegistr(String email, String name) {

        SimpleMailMessage message = applicationContext.getBean(SimpleMailMessage.class);

        String text = String.format(message.getText(), name);

        message.setText(text);
        message.setTo(email);

        emailSender.send(message);
    }

    //First input
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = token.getPrincipal();
        Map<String, Object> atribute = user.getAttributes();
        if ((EMAILTEACHER).equals(atribute.get("email")) || (EMAILTEACHERSECOND).equals((String) atribute.get("email"))) {
            TeacherEntity teacherEntity = TeacherEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"));
            teacherService.save(teacherEntity);

            if (teacherService.findOne((String) atribute.get("email")) == null)
                sendMessageForRegistr((String) atribute.get("email"), (String) atribute.get("name"));

            if ((EMAILTEACHER).equals(atribute.get("email"))) {
                AllUserEntity allUser = AllUserEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"), RoleUser.ADMIN);
                allUserService.save(allUser);
                httpServletResponse.sendRedirect("/admininput");
            } else if ((EMAILTEACHERSECOND).equals(atribute.get("email"))) {
                AllUserEntity allUser = AllUserEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"), RoleUser.TEACHER);
                allUserService.save(allUser);
                httpServletResponse.sendRedirect("/teacher");
            }
        } else {
            StudentEntity studentEntity = StudentEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"));
            AllUserEntity allUser = AllUserEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"), RoleUser.STUDENT);
            if (studentService.findOne((String) atribute.get("email")))
                sendMessageForRegistr((String) atribute.get("email"), (String) atribute.get("name"));

            allUserService.save(allUser);
            studentService.save(studentEntity);
            httpServletResponse.sendRedirect("/student");
        }
    }
}
