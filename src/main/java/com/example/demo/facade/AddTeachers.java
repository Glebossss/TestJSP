package com.example.demo.facade;

import com.example.demo.dao.model.AllUserEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.model.enums.RoleUser;
import com.example.demo.service.AllUserService;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddTeachers {

    @Autowired
    TeacherService teacherService;

    @Autowired
    AllUserService allUserService;

    public void teacherEntities() {

        List<TeacherEntity> teacherEntities = studentEntities();
        List<AllUserEntity> allUserEntities = allUser();

        teacherEntities.forEach(teacherService::save);

        allUserEntities.forEach(allUserService::save);

    }

    public static List<AllUserEntity> allUser() {

        List<AllUserEntity> allUserEntities = new ArrayList<>();

        AllUserEntity allUserEgor = AllUserEntity.of("blablabla@gg.com", "Egor", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserEgor);

        AllUserEntity allUserIgor = AllUserEntity.of("igor@gg.com", "Igor", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserIgor);

        AllUserEntity allUserValera = AllUserEntity.of("Valera@gg.com", "Valera", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserValera);

        AllUserEntity allUserVasil = AllUserEntity.of("Vasil@gg.com", "Vasil", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserVasil);

        AllUserEntity allUserPetr = AllUserEntity.of("Vasil@gg.com", "Vasil", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg", RoleUser.TEACHER);
        allUserEntities.add(allUserPetr);

        return allUserEntities;
    }

    public static List<TeacherEntity> studentEntities() {

        List<TeacherEntity> teacherEntities = new ArrayList<>();

        TeacherEntity teacherEntity = TeacherEntity.of("blablabla@gg.com", "Egor", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg");
        teacherEntities.add(teacherEntity);

        TeacherEntity teacheUserIgor = TeacherEntity.of("igor@gg.com", "Igor", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg");
        teacherEntities.add(teacheUserIgor);

        TeacherEntity teacheUserValera = TeacherEntity.of("Valera@gg.com", "Valera", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg");
        teacherEntities.add(teacheUserValera);

        TeacherEntity teacheUserVasil = TeacherEntity.of("Vasil@gg.com", "Vasil", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg");
        teacherEntities.add(teacheUserVasil);

        TeacherEntity teacheUserPetr = TeacherEntity.of("Vasil@gg.com", "Vasil", "https://lh4.googleusercontent.com/-I78Y4-goIOU/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucmHDdSplh61cwIAOi3CVKkUqmaIOg/s96-c/photo.jpg");
        teacherEntities.add(teacheUserPetr);

        return teacherEntities;
    }
}
