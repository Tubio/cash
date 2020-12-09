package com.exam.cash.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.exam.cash.model.Loan;
import com.exam.cash.model.User;
import com.exam.cash.repositories.LoanRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class LoanServiceTest {

    @Autowired
    LoanService loanService;

    @MockBean
    LoanRepository loanRepository;

    @Test
    public void shouldGetAllLoans() {

        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(1l, 1000f));
        loans.add(new Loan(2l, 2400f));
        loans.add(new Loan(3l, 3220f));

        when(loanRepository.findAll()).thenReturn(loans);

        assertEquals(loans, loanService.getAllLoans());
    }

    @Test
    public void shouldGetOneLoan() {

        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(1l, 1000f));

        when(loanRepository.findAll()).thenReturn(loans);

        assertEquals(loans, loanService.getAllLoans());
    }

    @Test
    public void shouldGetSpecifiedLoan() {

        Loan loan = new Loan(1l, 1000f);

        when(loanRepository.findById(loan.getId())).thenReturn(Optional.of(loan));

        assertEquals(Optional.of(loan), loanService.getLoan(loan.getId()));
    }

    @Test
    public void shouldGetLoansByPage() {

        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(2l, 1000f));
        loans.add(new Loan(3l, 1222f));

        PageImpl<Loan> page = new PageImpl<>(loans);

        when(loanRepository.findAll(PageRequest.of(0, 50))).thenReturn(page);

        assertEquals(page, loanService.getLoans(PageRequest.of(0, 50)));
    }

    @Test
    public void shouldGetLoansByIdAndPage() {

        User user = new User(1l, "email", "name", "surname");

        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(2l, 1000f));
        loans.add(new Loan(3l, 1222f));

        PageImpl<Loan> page = new PageImpl<>(loans);

        when(loanRepository.findByUser_id(user.getId(), PageRequest.of(0,50))).thenReturn(page);

        assertEquals(page, loanService.getLoans(1l, PageRequest.of(0, 50)));
    }
    
    @Test
    public void shouldSaveLoan() {

        Loan testLoan = new Loan(1l,200f);

        when(loanRepository.findById(testLoan.getId())).thenReturn(Optional.of(testLoan));
        when(loanRepository.save(testLoan)).thenReturn(testLoan);

        assertEquals(Optional.of(testLoan), loanService.getLoan(testLoan.getId()));
    }
}
