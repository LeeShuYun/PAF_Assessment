package dev.leeshuyun.paf_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.leeshuyun.paf_assessment.models.Account;
import dev.leeshuyun.paf_assessment.repository.AccountsRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class PafAssessmentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PafAssessmentApplication.class, args);
	}

	@Autowired 
	AccountsRepository accRepo; 
	public String createRandId(){
		return UUID.randomUUID().toString().substring(0, 8);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello?");
		
		List<Account> results = accRepo.getAllAccounts();

		for (Account acc: results) 
			// System.out.printf("accID >>> %s Name >>> %s  Balance>>> %s %n", acc.getAccountId(), acc.getName(), acc.getBalance().toString());
			System.out.println(createRandId());
		}
	}

