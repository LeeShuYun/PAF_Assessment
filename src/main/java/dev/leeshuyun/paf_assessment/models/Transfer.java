package dev.leeshuyun.paf_assessment.models;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Transfer {

    private String transactionId;

    
    private String date;
    
    private String fromAccountName;
    private String fromAccountId;

    private String toAccountName;
    private String toAccountId;

    @NotNull(message="Please state the amount to be transferred")
    private Float transferAmount;
    
    public void setCurrentDate(){
        Date date = new Date();

        // display time and date using toString()
        System.out.println(date.toString());

        this.setDate(date.toString());
    }
}
