package com.exam.cash.controllers;

import java.util.List;
import java.util.Optional;

import com.exam.cash.services.LoanService;
import com.exam.cash.model.Loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {
    
    @Autowired
    private LoanService service;
    
    //------------GET METHODS------------//

    @GetMapping(value = "/loans")
    public List<Loan> getAllLoans() {
        return service.getAllLoans();
    }

    @GetMapping(value = "/loans", params = {"page", "size", "user_id"})
    public Page<Loan> getLoans(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("user_id") Optional<Long> userId) {

        return userId.isPresent() ? service.getLoans(userId.get(), PageRequest.of(page, size)) 
                                  : service.getLoans(PageRequest.of(page, size));
    }

    @GetMapping(value = "/loans/{loanId}")
    public Optional<Loan> getLoan(@PathVariable Long loanId) {
        return service.getLoan(loanId);
    }

    //------------POST METHODS------------//
    
    @PostMapping(value = "/loans")
    public Optional<Loan> save(@RequestBody Loan newLoan) {
        return service.save(newLoan);
    }
}
