package dev.leeshuyun.paf_assessment.utils;

import java.util.UUID;

import jakarta.json.JsonObjectBuilder;

public class TransferUtils {
    
    public String createRandId(){
        return UUID.randomUUID().toString().substring(0, 8);
    }

}
