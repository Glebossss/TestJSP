package com.example.demo.controller;


import com.example.demo.service.AllUserService;
import com.example.demo.service.UnconfirmedActivitiesService;
import com.example.demo.service.СonfirmedActivitiesService;
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
public class СonfirmedActivitiesController {

    private static final int COUNT = 1;
    private static final String STUDENTROLE = "ROLE_STUDENT";
    private static final String TEACHERROLE = "ROLE_TEACHER";


    @Autowired
    UnconfirmedActivitiesService unconfirmedActivitiesService;

    @Autowired
    AllUserService allUserService;

    @Autowired
    СonfirmedActivitiesService сonfirmedActivitiesService;


    @RequestMapping("/confirmedactivitiesforstudent")
    public String forStudent(Model model, OAuth2AuthenticationToken auth,
                             @RequestParam(required = false, defaultValue = "0") Integer pageCount, @RequestParam(required = false, defaultValue = "0") Long not) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String emailStudent = (String) attrs.get("email");
        String roleUser = allUserService.findByLogin(emailStudent).getRole().toString();
        if (roleUser.equals(TEACHERROLE))
            return "confirmedactivitiesforteacher";
        else {
            if (not != 0)
                сonfirmedActivitiesService.del(not);

            model.addAttribute("confirmedactivitiesforstudent", сonfirmedActivitiesService.findAllUnconfirmedActivitiesForStudent(emailStudent, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));
            model.addAttribute("count", getPageCountForStudent(emailStudent));

            return "confirmedactivitiesforstudent";
        }
    }


    @RequestMapping("/confirmedactivitiesforteacher")
    public String forTeacher(Model model, OAuth2AuthenticationToken auth,
                             @RequestParam(required = false, defaultValue = "0") Integer pageCount) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String emailTeachers = (String) attrs.get("email");

        String roleUser = allUserService.findByLogin(emailTeachers).getRole().toString();

        if (roleUser.equals(TEACHERROLE)) {
            model.addAttribute("confirmedactivitiesforteacher", сonfirmedActivitiesService.findAllUnconfirmedActivitiesForTeacher(emailTeachers, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));
            model.addAttribute("count", getPageCountForTeacher(emailTeachers));
            return "confirmedactivitiesforstudent";

        } else if (roleUser.equals(roleUser.equals(STUDENTROLE)))
            return "confirmedactivitiesforstudent";

        else
            return "confirmedactivitiesforstudent";
    }

    private long getPageCountForStudent(String email) {
        long totalCount = сonfirmedActivitiesService.findAllStudent(email).size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }

    private long getPageCountForTeacher(String email) {
        long totalCount = сonfirmedActivitiesService.findAllTeacher(email).size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }
}
