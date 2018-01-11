package com.jeonguk.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeonguk.graphql.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}