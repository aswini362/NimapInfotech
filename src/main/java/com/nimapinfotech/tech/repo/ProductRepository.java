package com.nimapinfotech.tech.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimapinfotech.tech.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
