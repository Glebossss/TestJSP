package com.example.demo.controller;

import com.example.demo.service.AllUserService;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@Controller
public class SubjectController {

    private static final int COUNT = 3;
    private static final String ADD = "addsubject";
    private static final String ADMINROLE = "ROLE_ADMIN";
    private static final String STUDENTROLE = "ROLE_STUDENT";

    @Autowired
    SubjectService subjectService;

    @Autowired
    AllUserService allUserService;


    @RequestMapping(value = "/subject")
    public String allSubject(Model model, @RequestParam(required = false, defaultValue = "0") Integer pageCount) {

        model.addAttribute("subject", subjectService.findAllSubjectByPageable(PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));
        model.addAttribute("count", getPageCount());

        return "subject";
    }


    @RequestMapping(value = "/addsubject")
    public String allSubject(OAuth2AuthenticationToken auth) throws IOException {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");

        String roleUser = allUserService.findByLogin(email).getRole().toString();

        if (roleUser.equals(ADMINROLE))
            return ADD;

        else if (roleUser.equals(STUDENTROLE))
            return "student";

        else
            return "teacher";

    }

    @RequestMapping(value = "/subject/add", method = RequestMethod.POST)
    public String subjectAdd(@RequestParam(value = "subject") String subject) {

        subjectService.add(subject);

        return "redirect:/subject";
    }


    private long getPageCount() {
        long totalCount = subjectService.findAllSubject().size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }
}
