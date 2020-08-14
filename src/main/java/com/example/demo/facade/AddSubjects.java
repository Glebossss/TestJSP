package com.example.demo.facade;

import com.example.demo.dao.model.SubjectEntity;

import java.util.ArrayList;
import java.util.List;

public class AddSubjects {

    public static List<SubjectEntity> addSubject() {

        List<SubjectEntity> subjectEntities = new ArrayList<>();

        SubjectEntity subjectEntityMaths = new SubjectEntity("Maths");
        subjectEntities.add(subjectEntityMaths);

        SubjectEntity subjectEntityProgramming = new SubjectEntity("Programming");
        subjectEntities.add(subjectEntityProgramming);

        SubjectEntity subjectEntityPhysics = new SubjectEntity("Physics");
        subjectEntities.add(subjectEntityPhysics);

        SubjectEntity subjectEntityEconomy = new SubjectEntity("Economy");
        subjectEntities.add(subjectEntityEconomy);

        SubjectEntity subjectEntityEnglish = new SubjectEntity("English");
        subjectEntities.add(subjectEntityEnglish);

        SubjectEntity subjectEntityChemistry = new SubjectEntity("Chemistry");
        subjectEntities.add(subjectEntityChemistry);

        return subjectEntities;
    }
}
