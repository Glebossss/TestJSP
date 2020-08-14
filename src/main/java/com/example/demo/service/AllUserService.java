package com.example.demo.service;

import com.example.demo.dao.model.AllUserEntity;
import com.example.demo.dto.AllUserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AllUserService {


    public List<AllUserEntity> findAllUser();

    public AllUserEntity findByLogin(String email);

    public void save(AllUserEntity allUser);

    public void update(String email);

    public List<AllUserDTO> findAllUserPagebal(Pageable pageable);

}
