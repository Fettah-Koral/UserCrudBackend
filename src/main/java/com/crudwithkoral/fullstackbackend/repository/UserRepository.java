package com.crudwithkoral.fullstackbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudwithkoral.fullstackbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
