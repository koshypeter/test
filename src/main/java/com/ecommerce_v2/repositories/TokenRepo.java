package com.ecommerce_v2.repositories;

import com.ecommerce_v2.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken,Integer> {
}
