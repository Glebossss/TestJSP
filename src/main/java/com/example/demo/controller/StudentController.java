package com.example.demo.controller;

import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class StudentController {

    private static final int COUNT = 3;
    private static final String ADMINROLE = "ROLE_ADMIN";
    private static final String STUDENTROLE = "ROLE_STUDENT";

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CalendarService calendarService;

    @Autowired
    AllUserService allUserService;

    @RequestMapping("/student")
    public String student(Model model, OAuth2AuthenticationToken auth) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");
        String picture = (String) attrs.get("picture");
        String name = (String) attrs.get("name");

        String roleUser = allUserService.findByLogin(email).getRole().toString();

        if (roleUser.equals(ADMINROLE)) {

            return "admin";

        } else if (roleUser.equals(STUDENTROLE)) {

            model.addAttribute("email", email);
            model.addAttribute("picture", picture);
            model.addAttribute("name", name);

            return "student";
        } else
            return "teacher";


    }

    @RequestMapping("/recordingtoteacher")
    public String studentRecord(Model model, OAuth2AuthenticationToken auth, @RequestParam(required = false) String teacherEmail, @RequestParam(required = false, defaultValue = "0") Integer pageCount) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");

        model.addAttribute("teacher", teacherService.findOne(teacherEmail));
        model.addAttribute("calendar", calendarService.findAllRecord(teacherEmail, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));
        model.addAttribute("count", getPageCountRecord(teacherEmail));
        model.addAttribute("student", email);

        return "recordingtoteacher";
    }


    @RequestMapping(value = "/allstudent")
    public String allStudent(Model model, OAuth2AuthenticationToken auth, @RequestParam(required = false, defaultValue = "0") Integer pageCount) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");
        String picture = (String) attrs.get("picture");
        String name = (String) attrs.get("name");

        String roleUser = allUserService.findByLogin(email).getRole().toString();

        if (roleUser.equals(ADMINROLE))
            return "admin";

        else if (roleUser.equals(STUDENTROLE))
            return "student";

        else {
            model.addAttribute("email", email);
            model.addAttribute("picture", picture);
            model.addAttribute("name", name);

            model.addAttribute("allstudent", studentService.findAllStudentByPage(PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));

            model.addAttribute("count", getPageCount());

            return "allstudent";
        }
    }


    private long getPageCount() {
        long totalCount = studentService.findAllStudents().size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }

    private long getPageCountRecord(String email) {
        long totalCount = calendarService.findall(email).size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }
}
