package com.auth.login.demo.repo;

import com.auth.login.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {


}
