package dev.leeshuyun.paf_assessment.models;

import lombok.Data;

//holds one person's worth of data for transferrance
@Data
public class Account {
    String accountId; 
    String name;
    Float balance;
}

