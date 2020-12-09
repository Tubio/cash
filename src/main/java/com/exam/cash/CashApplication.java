package com.exam.cash;

import com.exam.cash.model.Loan;
import com.exam.cash.model.User;
import com.exam.cash.repositories.LoanRepository;
import com.exam.cash.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CashApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner initData(UserRepository userRepository, LoanRepository loanRepository) {
		return (args) -> {
			sampleUser1(userRepository, loanRepository);
			sampleUser2(userRepository, loanRepository);
			sampleUser3(userRepository, loanRepository);
			sampleUser4(userRepository, loanRepository);
			sampleUser5(userRepository, loanRepository);
		};
	}

	private void sampleUser1(UserRepository userRepository, LoanRepository loanRepository) {

		User user1 = new User();
		user1.setEmail("lukeskywalker@app.com.ar");
		user1.setFirstName("Luke");
		user1.setLastName("Skywalker");
		
		Loan loan1 = new Loan();
		loan1.setTotal(1000);
		user1.addLoan(loan1);

		Loan loan2 = new Loan();
		loan1.setTotal(2100);
		user1.addLoan(loan2);
		
		userRepository.save(user1);
		loanRepository.save(loan1);
		loanRepository.save(loan2);
	}

	private void sampleUser2(UserRepository userRepository, LoanRepository loanRepository) {

		User user1 = new User();
		user1.setEmail("pepeargento@app.com.ar");
		user1.setFirstName("Pepe");
		user1.setLastName("Argento");
		
		Loan loan1 = new Loan();
		loan1.setTotal(500);
		user1.addLoan(loan1);
		
		userRepository.save(user1);
		loanRepository.save(loan1);
	}

	private void sampleUser3(UserRepository userRepository, LoanRepository loanRepository) {

		User user1 = new User();
		user1.setEmail("pacman@app.com.ar");
		user1.setFirstName("Pac");
		user1.setLastName("Man");
		
		Loan loan1 = new Loan();
		loan1.setTotal(1000);
		user1.addLoan(loan1);

		Loan loan2 = new Loan();
		loan1.setTotal(2000);
		user1.addLoan(loan2);

		Loan loan3 = new Loan();
		loan1.setTotal(3000);
		user1.addLoan(loan3);
		
		userRepository.save(user1);
		loanRepository.save(loan1);
		loanRepository.save(loan2);
		loanRepository.save(loan3);
	}

	private void sampleUser4(UserRepository userRepository, LoanRepository loanRepository) {

		User user1 = new User();
		user1.setEmail("homerosimpson@duff.com.ar");
		user1.setFirstName("Homero");
		user1.setLastName("Simpson");
		
		Loan loan1 = new Loan();
		loan1.setTotal(100);
		user1.addLoan(loan1);

		Loan loan2 = new Loan();
		loan1.setTotal(200);
		user1.addLoan(loan2);

		Loan loan3 = new Loan();
		loan1.setTotal(350);
		user1.addLoan(loan3);
		
		userRepository.save(user1);
		loanRepository.save(loan1);
		loanRepository.save(loan2);
		loanRepository.save(loan3);
	}

	private void sampleUser5(UserRepository userRepository, LoanRepository loanRepository) {

		User user1 = new User();
		user1.setEmail("carlsagan@gmail.com.ar");
		user1.setFirstName("Carl");
		user1.setLastName("Sagan");
		
		Loan loan1 = new Loan();
		loan1.setTotal(12000);
		user1.addLoan(loan1);
		
		userRepository.save(user1);
		loanRepository.save(loan1);
	}
}
