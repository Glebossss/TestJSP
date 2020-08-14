package com.example.demo.controller;


import com.example.demo.dto.SubjectDTO;
import com.example.demo.dto.TeacherDTO;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {

    private static final int COUNT = 5;
    private static final String STUDENTROLE = "ROLE_STUDENT";
    private static final String TEACHERROLE = "ROLE_TEACHER";

    @Autowired
    TeacherService teacherService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    StudentService studentService;

    @Autowired
    CalendarService calendarService;


    @Autowired
    AllUserService allUserService;


    @RequestMapping("/teacher")
    public String teacher(Model model, OAuth2AuthenticationToken auth, @RequestParam(required = false, defaultValue = "0") Integer pageCount) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");
        String picture = (String) attrs.get("picture");
        String name = (String) attrs.get("name");

        String roleUser = allUserService.findByLogin(email).getRole().toString();

        if (TEACHERROLE.equals(roleUser)) {

            List<SubjectDTO> subjectDTO = subjectService.findAllSubject();
            TeacherDTO teacherDTO = teacherService.findOne(email);
            model.addAttribute("subject", subjectDTO);
            model.addAttribute(teacherDTO);

            model.addAttribute("calendar", calendarService.findAllRecord(email, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));
            model.addAttribute("count", getPageCountRecord(email, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));
            return "teacher";
        } else if (STUDENTROLE.equals(roleUser)) {
            return "student";
        } else
            return "admin";
    }


    @RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
    public String contactAdd(OAuth2AuthenticationToken auth, @RequestParam(required = false, defaultValue = "0", value = "subject") long subject,
                             @RequestParam(required = false, defaultValue = "0") Integer money, @RequestParam(required = false, defaultValue = "0", value = "startDate") String startDate,
                             @RequestParam(required = false, defaultValue = "0") String endDate) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");
        String roleUser = allUserService.findByLogin(email).getRole().toString();

        if (TEACHERROLE.equals(roleUser)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Long timeLessons = Long.valueOf(endDate);
            try {
                Date dateStart = formatter.parse(startDate);
                if (dateStart != null)
                    calendarService.save(dateStart, timeLessons, email);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (money <= 0 || money > 5000 || money == null)
                money = 1;

            teacherService.update(subject, money, email);
            return "redirect:/teacher";
        } else if (STUDENTROLE.equals(roleUser))
            return "student";
        else
            return "admin";


    }

    @RequestMapping(value = "/allteacher")
    public String allTeacher(OAuth2AuthenticationToken auth, Model model, @RequestParam(required = false, defaultValue = "0") Integer pageCount) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");
        String roleUser = allUserService.findByLogin(email).getRole().toString();

        if (STUDENTROLE.equals(roleUser)) {

            model.addAttribute("allteacher", teacherService.findAllTeacherByPage(PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));

            model.addAttribute("count", getPageCount());

            return "allteacher";
        } else if (TEACHERROLE.equals(roleUser))
            return "teacher";
        else
            return "admin";

    }


    private long getPageCount() {
        long totalCount = teacherService.findAllTeacher().size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }

    private long getPageCountRecord(String email, Pageable pageable) {
        long totalCount = calendarService.findAllRecord(email, pageable).size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }
}
