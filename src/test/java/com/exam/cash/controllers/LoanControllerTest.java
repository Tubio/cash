package com.exam.cash.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.exam.cash.model.Loan;
import com.exam.cash.model.User;
import com.exam.cash.services.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired          
    private MockMvc mockMvc; 
    
    @MockBean
    private LoanService loanService;

    @Test
    public void shouldGetAllLoans() throws Exception {

        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(1l,12000f, new User()));
        loans.add(new Loan(2l,4400f, new User()));

        when(loanService.getAllLoans()).thenReturn(loans);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                  .get("/loans"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        assertEquals(asJsonString(loans), result.getResponse().getContentAsString());
    }

    @Test
    public void shouldGetEmptyLoans() throws Exception {

        List<Loan> loans = new ArrayList<>();

        when(loanService.getAllLoans()).thenReturn(loans);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                  .get("/loans"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        assertEquals(asJsonString(loans), result.getResponse().getContentAsString());
    }

    @Test
    public void shouldGetLoanPage() throws Exception {

        Loan loan1 = new Loan(1l,12000f, new User());
        Loan loan2 = new Loan(2l,4400f, new User());       
        
        List<Loan> loans = new ArrayList<>();
        loans.add(loan1);
        loans.add(loan2);
        Page<Loan> page = new PageImpl<>(loans);

        when(loanService.getLoans(any(PageRequest.class))).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders
               .get("/loans?page=0&size=50"))
               .andExpect(status().isOk())
               .andReturn();

    }

    @Test
    public void shouldGetLoanPageByUserId() throws Exception {

        Loan loan1 = new Loan(1l,12000f, new User());     
        
        List<Loan> loans = new ArrayList<>();
        loans.add(loan1);
        Page<Loan> page = new PageImpl<>(loans);

        when(loanService.getLoans(loan1.getId(), PageRequest.of(0,50))).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders
               .get("/loans?page=0&size=50&user_id=1"))
               .andExpect(status().isOk())
               .andReturn();
    }

    @Test
    public void shouldSaveLoan() throws Exception {

        Loan loan = new Loan(1l,12000f, new User());     

        when(loanService.save(any(Loan.class))).thenReturn(Optional.of(loan));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                  .post("/loans")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(asJsonString(loan)))
                                  .andExpect(status().isOk())
                                  .andReturn();

        assertEquals(asJsonString(loan), result.getResponse().getContentAsString());
    }

    private static String asJsonString(Object someObject) {
        try {
            return new ObjectMapper().writeValueAsString(someObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
