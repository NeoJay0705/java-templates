package com.example.templates.jpa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.templates.jpa.model.UserEntity;
import com.example.templates.jpa.repository.UserRepository;

@Service
public class JpaUserEntityService {
    private UserRepository userRepository;

    public JpaUserEntityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        // this.userRepository.fin
        return users;
    }

    @Transactional
    public List<UserEntity> getbyName(String name) {
        List<UserEntity> users = new ArrayList<>();
        this.userRepository.findByName(name).forEach(users::add);
        // this.userRepository.fin
        return users;
    }

    @Transactional
    public List<UserEntity> getbyNameAndAge(String name, int age) {
        List<UserEntity> users = new ArrayList<>();
        this.userRepository.findByNameAndAge(name, age).forEach(users::add);
        // this.userRepository.fin
        return users;
    }

    @Transactional
    public List<UserEntity> getbyNameOrAge(String name, int age) {
        List<UserEntity> users = new ArrayList<>();
        this.userRepository.findByNameOrAge(name, age).forEach(users::add);
        // this.userRepository.fin
        return users;
    }

    @Transactional(noRollbackFor = RuntimeException.class, isolation = Isolation.REPEATABLE_READ)
    public List<UserEntity> savebyNameOrAge(UserEntity user) {
        List<UserEntity> users = new ArrayList<>();
        this.userRepository.save(user);
        this.userRepository.findByName(user.getName()).forEach(users::add);
        // if (1==1) throw new RuntimeException("rollback");
        // this.userRepository.fin
        return users;
    }

    @Transactional
    public List<UserEntity> getbyNameSorted(String name) {
        List<UserEntity> users = new ArrayList<>();
        TypedSort<UserEntity> sUsers = Sort.sort(UserEntity.class);
        this.userRepository.findByName(name, sUsers.by(UserEntity::getName).ascending().and(sUsers.by(UserEntity::getAge).descending())).forEach(users::add);
        // this.userRepository.fin
        return users;
    }

    @Transactional
    public List<UserEntity> getbyNameSlice(String name, int page) {
        List<UserEntity> users = new ArrayList<>();
        this.userRepository.findByName(name, PageRequest.of(page, 1)).forEach(users::add);
        // this.userRepository.fin
        return users;
    }

    @Transactional
    public List<UserEntity> getAllByNative() {
        return this.userRepository.selectAll();
    }
}
