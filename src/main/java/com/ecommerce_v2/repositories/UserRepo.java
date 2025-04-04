package com.ecommerce_v2.repositories;

import com.ecommerce_v2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
    User findByEmail(String email);
}
