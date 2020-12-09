package com.exam.cash.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.exam.cash.model.Loan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class LoanRepositoryTest {
    
    @Autowired
    LoanRepository loanRepository;
    
    @Autowired
    TestEntityManager entityManager;

    @Test
    public void shouldFindAllLoans() {

        Loan loan1 = new Loan();
        loan1.setTotal(1400f);

        Loan loan2 = new Loan();
        loan2.setTotal(2000f);

        entityManager.persist(loan1);
        entityManager.persist(loan2);

        assertEquals(Arrays.asList(loan1, loan2), loanRepository.findAll());
    }

    @Test
    public void shouldFindOneLoan() {

        Loan loan1 = new Loan();
        loan1.setTotal(1400f);

        entityManager.persist(loan1);

        assertEquals(Arrays.asList(loan1), loanRepository.findAll());
    }

    @Test
    public void shouldFindNoLoans() {
        assertEquals(new ArrayList<>(), loanRepository.findAll());
    }

    @Test
    public void shouldFindSpecifiedLoanByLoanId() {

        Loan loan1 = new Loan();
        loan1.setTotal(1400f);

        entityManager.persist(loan1);

        assertEquals(Optional.of(loan1), loanRepository.findById(loan1.getId()));
    }

    @Test
    public void shouldNotFindSpecifiedLoanByLoanId() {
        assertEquals(Optional.empty(), loanRepository.findById(1l));
    }

    @Test
    public void shouldSaveLoan() {

        Loan loan1 = new Loan();
        loan1.setTotal(1400f);

        loanRepository.save(loan1);

        assertEquals(Optional.of(loan1), loanRepository.findById(loan1.getId()));
    }
}
