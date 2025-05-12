package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.app.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

