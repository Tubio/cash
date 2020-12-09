package com.exam.cash.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserTest {

    @Test
    public void shouldGetId() {

        User testUser = new User(1l, "email", "name", "surname");

        assertEquals(1l, testUser.getId());
    }

    @Test
    public void shouldGetEmail() {

        User testUser = new User(1l, "email", "name", "surname");

        assertEquals("email", testUser.getEmail());
    }

    @Test
    public void shouldGetFirstName() {

        User testUser = new User(1l, "email", "name", "surname");

        assertEquals("name", testUser.getFirstName());
    }

    @Test
    public void shouldGetLastName() {

        User testUser = new User(1l, "email", "name", "surname");

        assertEquals("surname", testUser.getLastName());
    }

    @Test
    public void shouldGetLoans() {

        User testUser = new User(1l, "email", "name", "surname");

        Set<Loan> loans = new HashSet<>();
        Loan loan1 = new Loan(1l, 1000f);
        Loan loan2 = new Loan(2l, 1500f);
        loans.addAll(Arrays.asList(loan1, loan2));
        testUser.addLoan(loan1);
        testUser.addLoan(loan2);

        assertEquals(loans, testUser.getLoans());
    }
    

    @Test
    public void shouldSetId() {

        User testUser = new User();
        testUser.setId(1l);

        assertEquals(1l, testUser.getId());
    }
    
    @Test
    public void shouldSetEmail() {

        User testUser = new User();
        testUser.setEmail("email");

        assertEquals("email", testUser.getEmail());
    }
    
    @Test
    public void shouldSetFirstName() {

        User testUser = new User();
        testUser.setFirstName("name");

        assertEquals("name", testUser.getFirstName());
    }

    @Test
    public void shouldSetLastName() {

        User testUser = new User();
        testUser.setLastName("surname");

        assertEquals("surname", testUser.getLastName());
    }
    
    @Test
    public void shouldAddLoan() {

        User testUser = new User(1l, "email", "name", "surname");

        Loan loan = new Loan(1l, 1000f);
        testUser.addLoan(loan);

        assertEquals(new HashSet<>(Arrays.asList(loan)), testUser.getLoans());
    }

    @Test
    public void shouldAddMultipleLoans() {

        User testUser = new User(1l, "email", "name", "surname");

        Loan loan1 = new Loan(1l, 1000f);
        Loan loan2 = new Loan(2l, 1200f);
        testUser.addLoan(loan1);
        testUser.addLoan(loan2);

        assertEquals(new HashSet<>(Arrays.asList(loan1, loan2)), testUser.getLoans());
    }
}
