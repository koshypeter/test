package com.ecommerce_v2.repositories;

import com.ecommerce_v2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{
    Optional<Category> findBycName(String cName);
}
