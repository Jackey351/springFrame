package com.yanfei.springFrame.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yanfei.springFrame.Entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
