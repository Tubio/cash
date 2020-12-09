package com.exam.cash.services;

import java.util.List;
import java.util.Optional;

import com.exam.cash.model.Loan;
import com.exam.cash.repositories.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    
    @Autowired(required = true)
    private LoanRepository repository;

    public List<Loan> getAllLoans() {
        return repository.findAll();
    }

    public Page<Loan> getLoans(Pageable page) {
        return repository.findAll(page);
    }

    public Page<Loan> getLoans(Long userId, Pageable page) {
        return repository.findByUser_id(userId, page);
    }

    public Optional<Loan> getLoan(Long loanId) {
        return repository.findById(loanId);
    }

    public Optional<Loan> save(Loan newLoan) {
        return Optional.of(repository.save(newLoan));
    }
}
