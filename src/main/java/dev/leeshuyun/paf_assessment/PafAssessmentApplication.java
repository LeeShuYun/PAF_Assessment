package dev.leeshuyun.paf_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.leeshuyun.paf_assessment.exception.TransferException;
import dev.leeshuyun.paf_assessment.models.Account;
import dev.leeshuyun.paf_assessment.models.Transfer;
import dev.leeshuyun.paf_assessment.repository.AccountsRepository;
import dev.leeshuyun.paf_assessment.services.LogAuditService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//implements CommandLineRunner  
//
@SpringBootApplication
public class PafAssessmentApplication {

	public static void main(String[] args) throws TransferException {
		SpringApplication.run(PafAssessmentApplication.class, args);
	}

	// @Autowired 
	// AccountsRepository accRepo; 
	// public String createRandId(){
	// 	return UUID.randomUUID().toString().substring(0, 8);
	// }
	// @Override
	// public void run(String... args) throws Exception {
	// 	System.out.println("Hello?");

	// 	//test transfer 
    //     Transfer test = new Transfer();
    //     test.setCurrentDate();
    //     test.setFromAccountId("V9L3Jd1BBI");
    //     test.setFromAccountName("fred");
    //     test.setToAccountId("fhRq46Y6vB");
    //     test.setToAccountName("barney");
    //     test.setTransactionId("cnwbshbh");
    //     test.setTransferAmount(100.00f);

	// 	LogAuditService logSvc2 = new LogAuditService();
    //     logSvc2.logTransaction(test);
		
	// 	// List<Account> results = accRepo.getAllAccounts();

	// 	// for (Account acc: results) 
	// 	// System.out.printf("accID >>> %s Name >>> %s  Balance>>> %s %n", acc.getAccountId(), acc.getName(), acc.getBalance().toString());
	// }
}

