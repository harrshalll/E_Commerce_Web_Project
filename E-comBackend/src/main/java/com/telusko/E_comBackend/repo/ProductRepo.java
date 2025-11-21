package com.telusko.E_comBackend.repo;

import com.telusko.E_comBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE "+
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER (CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand)LIKE LOWER (CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);
}
/*
JPQL is like SQL, but instead of using:
Table names → you use Entity names
Column names → you use Entity field names
JPQL works on Java objects, not directly on database tables.
JPQL helps you write custom queries for searches and filters in a Spring Boot project.
 */