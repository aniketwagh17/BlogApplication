package com.blog.application.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
