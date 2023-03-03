package dev.leeshuyun.paf_assessment.models;

import java.util.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

@Data
public class Transfer {

    private String transactionId;
    private String date;

    private String fromAccountName;

    @Pattern(regexp="^[0-9]{10,}$", message="Must be least 10 digits long")
    private String fromAccountId;

    private String toAccountName;
    
    @Pattern(regexp="^[0-9]{10,}$", message="Must be least 10 digits long")
    private String toAccountId;

    @NotNull(message="Please state the amount to be transferred")
    @DecimalMin (value = "10")
    private Float transferAmount;

    private Boolean isTransferSuccessful;
    
    public void setCurrentDate(){
        Date date = new Date();

        // display time and date using toString()
        // System.out.println(date.toString());

        this.setDate(date.toString());
    }

    // convert this obj to json String
    public String toJson(){
        JsonObjectBuilder jsonOB = Json.createObjectBuilder();
        jsonOB.add("transaction_id", this.getTransactionId());
        jsonOB.add("date", this.getDate());
        jsonOB.add("from_account", this.getFromAccountId());
        jsonOB.add("to_account", this.getToAccountId());
        jsonOB.add("amount", this.getTransferAmount());
        return jsonOB.build().toString();
    }

}
