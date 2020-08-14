package com.example.demo.controller;

import com.example.demo.dto.UnconfirmedActivitiesDTO;
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

import java.util.List;
import java.util.Map;

@Controller
public class UnconfirmedActivitiesController {

    private static final int COUNT = 5;
    private static final String STUDENTROLE = "ROLE_STUDENT";
    private static final String TEACHERROLE = "ROLE_TEACHER";
    private static final String CONSTANT = "0";

    @Autowired
    UnconfirmedActivitiesService unconfirmedActivitiesService;

    @Autowired
    AllUserService allUserService;

    @Autowired
    СonfirmedActivitiesService сonfirmedActivitiesService;


    @RequestMapping("/unconfirmedactivitiesforstudent")
    public String forStudent(Model model, OAuth2AuthenticationToken auth, @RequestParam(required = false, defaultValue = "0") String id,
                             @RequestParam(required = false, defaultValue = "0") Integer pageCount) {


        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String emailStudent = (String) attrs.get("email");
        String roleUser = allUserService.findByLogin(emailStudent).getRole().toString();

        if (roleUser.equals(STUDENTROLE)) {
            Long ids = Long.valueOf(id);

            if (!id.equals(CONSTANT)) {
                unconfirmedActivitiesService.save(emailStudent, ids);
            }

            model.addAttribute("unconfirmedActivitiesDTOS", unconfirmedActivitiesService.findAllUnconfirmedActivitiesForStudent(emailStudent, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));
            model.addAttribute("count", getPageCountForStudent(emailStudent));

            return "unconfirmedactivitiesforstudent";
        } else if (roleUser.equals(TEACHERROLE))
            return "unconfirmedactivitiesforteacher";
        else
            return "unconfirmedactivitiesforteacher";

    }


    @RequestMapping("/unconfirmedactivitiesforteacher")
    public String forTeacher(Model model, OAuth2AuthenticationToken auth,
                             @RequestParam(required = false, defaultValue = "0") Long appr, @RequestParam(required = false, defaultValue = "0") Long not,
                             @RequestParam(required = false, defaultValue = "0") Integer pageCount) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String emailTeachers = (String) attrs.get("email");
        String roleUser = allUserService.findByLogin(emailTeachers).getRole().toString();

        if (roleUser.equals(TEACHERROLE)) {
            if (appr != 0)
                сonfirmedActivitiesService.save(appr);

            else if (not != 0)
                unconfirmedActivitiesService.declineActifities(not);

            List<UnconfirmedActivitiesDTO> unconfirmedActivitiesDTOS = unconfirmedActivitiesService.findAllUnconfirmedActivitiesForTeacher(emailTeachers, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id"));

            model.addAttribute("unconfirmedActivitiesDTOS", unconfirmedActivitiesDTOS);
            model.addAttribute("count", getPageCountForTeacher(emailTeachers));

            return "unconfirmedactivitiesforteacher";
        } else if (roleUser.equals(STUDENTROLE))
            return "unconfirmedactivitiesforstudent";
        else
            return "unconfirmedactivitiesforteacher";
    }

    private long getPageCountForStudent(String email) {
        long totalCount = unconfirmedActivitiesService.findAllStudent(email).size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }

    private long getPageCountForTeacher(String email) {
        long totalCount = unconfirmedActivitiesService.findAllTeacher(email).size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }
}
