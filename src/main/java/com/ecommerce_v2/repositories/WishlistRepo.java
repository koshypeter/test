package com.ecommerce_v2.repositories;

import com.ecommerce_v2.model.User;
import com.ecommerce_v2.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist,Integer> {
    List<Wishlist> findAllByUserOrderByCreateddateDesc(User user);
}
