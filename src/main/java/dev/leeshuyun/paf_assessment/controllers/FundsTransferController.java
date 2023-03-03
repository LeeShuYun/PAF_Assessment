package dev.leeshuyun.paf_assessment.controllers;

import java.util.List;
import java.sql.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("")
public class FundsTransferController {
    
    @Autowired
    FundsTransferService accSvc;

    private Logger logger = Logger.getLogger(FundsTransferController.class.getName());
    


    //retrieve the data from mySQL DB
    @GetMapping(path={"/", "/index.html"})
    public String getAccountData(Model model){
        //start fresh session
        // session.invalidate();

        //fetch the accounts data from SQL DB
        List<Account> accList = accSvc.getAllAccounts();

        // add the SQL data we retrieved to the model
        model.addAttribute("accounts", accList );

        return "index";
    }
    
    @PostMapping(path="/transfer")
	public String postTransaction(Model model, HttpSession sess
			, @Valid Transfer transfer, BindingResult bindings) {

		logger.info("POST /transfer: %s".formatted(transfer.toString()));

		// throws us back to index if fail validation
        //this is for the @Valid 
		if (bindings.hasErrors())
			return "index";
        
            //manual validation
		// List<ObjectError> errors = accSvc.validateTransaction(transfer);
		// if (!errors.isEmpty()) {
		// 	for (ObjectError err: errors)
		// 		bindings.addError(err);
		// 	return "index";
		// }

        //test transfer 
        Transfer test = new Transfer();
        test.setCurrentDate();
        test.setFromAccountId("V9L3Jd1BBI");
        test.setFromAccountName("fred");
        test.setToAccountId("fhRq46Y6vB");
        test.setToAccountName("barney");
        test.setTransactionId("cnwbshbh");
        test.setTransferAmount(100.00f);

		// sess.setAttribute("transfer", test);

		model.addAttribute("transfer", test);

		return "transfer";
	}
    // @PostMapping("/transfer")
    // public String postFundsTransfer(@RequestBody MultiValueMap<String, String> form,
    //     Model model, HttpSession sess){
    //         List<LineItem> lineItems = (List<LineItem>)sess.getAttribute("cart");
    //         if(null == lineItems) {
    //             //this is the init to catch nulls, bc nulls create errors  errors.
    //             lineItems = new LinkedList();
    //             sess.setAttribute("cart", lineItems);
    //         }

    //         String item = form.getFirst("item");
    //         Integer quantity = Integer.parseInt(form.getFirst("quantity"));
    //         lineItems.add(new LineItem(item, quantity));
    //         Order ord = new Order();
    //         ord.setCustomerName(form.getFirst("name"));
    //         ord.setLineItems(lineItems); //for the for loop

    //         //for the next page. this is going to be looping the page until we do 
    //         //checkout 
    //         sess.setAttribute("checkoutCart", ord);
    //         model.addAttribute("lineItems", lineItems);
    //         return "cart";
    //     }
}
