package com.example.demo.dao.repository;

import com.example.demo.dao.model.AllUserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AllUserRepository extends JpaRepository<AllUserEntity, Long> {

    @Query("SELECT u FROM AllUserEntity u where u.email = :email")
    AllUserEntity findByEmail(@Param("email") String email);

    List<AllUserEntity> findAllUserEntitiesBy(Pageable pageable);

    @Query
    boolean existsByEmail(String email);

}
