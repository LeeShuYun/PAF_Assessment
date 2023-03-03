package dev.leeshuyun.paf_assessment.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.leeshuyun.paf_assessment.exception.TransferException;
import dev.leeshuyun.paf_assessment.models.Transfer;

import dev.leeshuyun.paf_assessment.models.Account;
import dev.leeshuyun.paf_assessment.repository.AccountsRepository;

@Service
public class FundsTransferService {
    @Autowired
    AccountsRepository accRepo;
    
    public List<Account> getAllAccounts(){
        return accRepo.getAllAccounts();
    }
    
    //transactional. will rollback changes if exception thrown
    @Transactional(rollbackFor = TransferException.class)
    public Transfer createTransaction(Transfer transfer) throws TransferException{
        //generate the id using uuid 
        String transactionId = UUID.randomUUID().toString().substring(0, 8);
        transfer.setTransactionId(transactionId);
        
        //fetch the current amount from DB to get the latest amount before we change it
        Float toAccBalance = accRepo.getAccountUsingId(transfer.getToAccountId()).getBalance();
        Float fromAccBalance = accRepo.getAccountUsingId(transfer.getFromAccountId()).getBalance();
        
        //do some calculations to change the balance
        toAccBalance = toAccBalance + transfer.getTransferAmount();
        fromAccBalance = fromAccBalance + transfer.getTransferAmount();
        
        //update the balances
        Boolean isToChangeSuccess = accRepo.updateAccountBalance(toAccBalance, transfer.getToAccountId());
        Boolean isFromChangeSuccess = accRepo.updateAccountBalance(fromAccBalance, transfer.getFromAccountId());

        //check if successful, else rollback
        // test and simulate the transactional capabilities with a manual check
        if (isToChangeSuccess && isFromChangeSuccess){
            transfer.setIsTransferSuccessful(true);
            return transfer;
        }else{
            throw new TransferException("Transaction failed");
        }
        
    } //end transaction


}

