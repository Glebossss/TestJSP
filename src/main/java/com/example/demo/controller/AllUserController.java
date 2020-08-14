package com.example.demo.controller;


import com.example.demo.dao.model.AllUserEntity;
import com.example.demo.dao.model.enums.RoleUser;
import com.example.demo.service.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@Controller
public class AllUserController {

    private static final int COUNT = 10;
    private static final String ADMINROLE = "ROLE_ADMIN";
    private static final String STUDENTROLE = "ROLE_STUDENT";
    private static final String TEACHERROLE = "ROLE_TEACHER";


    @Autowired
    AllUserService allUserService;

    @RequestMapping(value = "/alluser")
    public String allUser(Model model, @RequestParam(required = false, defaultValue = "0") Integer pageCount,
                          @RequestParam(required = false) String userEmail, OAuth2AuthenticationToken auth) throws IOException {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");

        String roleUser = allUserService.findByLogin(email).getRole().toString();

        if (roleUser.equals(ADMINROLE)) {
            AllUserEntity allUserEntity = allUserService.findByLogin(userEmail);

            if (userEmail != null)
                allUserService.update(userEmail);

            model.addAttribute("alluser", allUserService.findAllUserPagebal(PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));

            model.addAttribute("alluser", allUserService.findAllUserPagebal(PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id")));

            model.addAttribute("count", getPageCount());

            return "alluser";

        } else if (roleUser.equals(STUDENTROLE))
            return "student";

        else
            return "teacher";
    }


    private long getPageCount() {
        long totalCount = allUserService.findAllUser().size();
        return (totalCount / COUNT) + ((totalCount % COUNT > 0) ? 1 : 0);
    }


}
