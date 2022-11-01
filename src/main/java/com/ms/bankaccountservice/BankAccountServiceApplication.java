package com.ms.bankaccountservice;

import com.ms.bankaccountservice.entities.BankAccount;
import com.ms.bankaccountservice.enums.AccountType;
import com.ms.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}


	@Bean  // Execution en demarrage
	CommandLineRunner start(BankAccountRepository bankAccountRepository){
		return args -> {
			for (int i=0;i<10;i++){
				// On utilisant le builder
				BankAccount bankAccount = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.type(Math.random()>0.5 ? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
						.balance(10000+Math.random()*10000)
						.createdAt(new Date())
						.currency("MAD")
						.build();

				bankAccountRepository.save(bankAccount);
			}
		};
	}

}
