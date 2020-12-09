package com.exam.cash.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoanTest {
    
    @Test
    public void shouldGetId() {

        Loan testLoan = new Loan(1l, 1400f);

        assertEquals(1l, testLoan.getId());
    }

    @Test
    public void shouldGetTotal() {

        Loan testLoan = new Loan(1l, 1400f);

        assertEquals(1400f, testLoan.getTotal());
    }

    @Test
    public void shouldGetUser() {

        User testUser = new User();
        Loan testLoan = new Loan(1l, 1400f, testUser);

        assertEquals(testUser, testLoan.getUser());
    }

    @Test
    public void shouldGetUserId() {

        User testUser = new User(1l, "email", "name", "surname");
        Loan testLoan = new Loan(2l, 1400f, testUser);

        assertEquals(1l, testLoan.getUserId());
    }

    @Test
    public void shouldSetId() {

        Loan testLoan = new Loan();
        testLoan.setId(1l);

        assertEquals(1l, testLoan.getId());
    }

    @Test
    public void shouldSetTotal() {

        Loan testLoan = new Loan();
        testLoan.setTotal(1400f);

        assertEquals(1400f, testLoan.getTotal());
    }

    @Test
    public void shouldSetUser() {

        User testUser = new User();
        Loan testLoan = new Loan();
        testLoan.setUser(testUser);

        assertEquals(testUser, testLoan.getUser());
    }
}
