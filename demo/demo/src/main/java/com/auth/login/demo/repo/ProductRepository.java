package com.auth.login.demo.repo;

import com.auth.login.demo.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductItem,Long> {

}
