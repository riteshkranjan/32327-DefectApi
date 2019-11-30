package com.infy.defect.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.defect.auth.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	List<Users> findByUserId(String userId);

}
