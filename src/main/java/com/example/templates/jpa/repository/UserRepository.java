package com.example.templates.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.templates.jpa.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {
    public List<UserEntity> findByName(String name);
    public List<UserEntity> findByNameAndAge(String name, int age);
    public List<UserEntity> findByNameOrAge(String name, int age);
    // delete returns Long
    // remove returs removed entities
    public List<UserEntity> findByName(String name, Sort sort);
    public Slice<UserEntity> findByName(String name, Pageable page);

    @Query(value = "select * from users", nativeQuery = true)
    public List<UserEntity> selectAll();
}
