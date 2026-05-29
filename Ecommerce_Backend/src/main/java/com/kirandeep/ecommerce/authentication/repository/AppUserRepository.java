package com.kirandeep.ecommerce.authentication.repository;

import com.kirandeep.ecommerce.authentication.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    Optional<AppUser> findByEmail(String email);
}