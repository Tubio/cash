package com.exam.cash.repositories;

import java.util.List;

import com.exam.cash.model.Loan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    public Page<Loan> findAll(Pageable Pageable);
    public List<Loan> findByUser_id(Long userId);
    public Page<Loan> findByUser_id(Long userId, Pageable Pageable);
}
