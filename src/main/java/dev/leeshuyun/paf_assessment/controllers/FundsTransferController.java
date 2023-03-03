package dev.leeshuyun.paf_assessment.controllers;

import java.util.List;
import java.sql.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import jakarta.validation.Valid;
import java.util.logging.Logger;

import dev.leeshuyun.paf_assessment.models.Account;
import dev.leeshuyun.paf_assessment.models.Transfer;
import dev.leeshuyun.paf_assessment.services.FundsTransferService;
import dev.leeshuyun.paf_assessment.services.LogAuditService;

import dev.leeshuyun.paf_assessment.exception.TransferException;
@Controller
@RequestMapping("")
public class FundsTransferController {
    
    @Autowired
    FundsTransferService fundSvc;

    @Autowired
    LogAuditService logSvc;

    private Logger logger = Logger.getLogger(FundsTransferController.class.getName());
    


    //retrieve the data from mySQL DB
    @GetMapping(path={"/", "/index.html"})
    public String getAccountData(Model model){
        //start fresh session
        // session.invalidate();

        //fetch the accounts data from SQL DB
        List<Account> accList = fundSvc.getAllAccounts();

        // add the SQL data we retrieved to the model
        model.addAttribute("accounts", accList );

        return "index";
    }
    
    @PostMapping(path="/transfer")
	public String postTransaction(@RequestBody MultiValueMap<String, String> form, Model model
			, @Valid Transfer transfer, BindingResult bindings) throws TransferException {

		logger.info("POST /transfer: %s".formatted(transfer.toString()));
        
        for(ObjectError error: bindings.getAllErrors()){
            System.out.println(error);
        }
        //check for errors 
		// throws us back to index if fail validation
        //this is for the @Valid 
		// if (bindings.hasErrors())
		// 	return "index";

        //since it passed error checking we can continue with our transaction
        //if successful, transfer will come back with isSuccessful marked to true
        transfer = fundSvc.createTransaction(transfer);

        //logging the process 
        // logSvc.logTransaction(transfer);

        // if (!isSuccessful){
        //     // bindings.addError(err);
        //     return "index";
        // }else{
        //     //we add the transaction to redis
           
        
        //manual validation
		// List<ObjectError> errors = accSvc.validateTransaction(transfer);
		// if (!errors.isEmpty()) {
		// 	for (ObjectError err: errors)
		// 		bindings.addError(err);
		// 	return "index";
		// }
        
        //this transfer does work thankfully.
		model.addAttribute("transfer", transfer);

		return "transfer";
	}
}
