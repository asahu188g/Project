package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.app.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
