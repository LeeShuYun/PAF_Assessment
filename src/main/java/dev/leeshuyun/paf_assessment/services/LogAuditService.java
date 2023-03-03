package dev.leeshuyun.paf_assessment.services;

import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import dev.leeshuyun.paf_assessment.models.Transfer;

//for storing the transaction logs
@Repository
public class LogAuditService {

    // @Autowired @Qualifier("redisTemplate")
    // private RedisTemplate<String, String> redisTemplate;

    // public Boolean logTransaction(final Transfer transfer) {
    //     System.out.println("Transfer String>>> " + transfer.toJson());

    //     //sets the transaction_id as key and transferObj as String value
    //     redisTemplate.opsForValue().set(transfer.getTransactionId(), transfer.toJson());

    //     //test by retrieving the data we just saved from redis 
    //     String result = (String) redisTemplate.opsForValue().get(transfer.getTransactionId());
        
    //     System.out.println(result);
        
    //     //if we successfully save the transaction into redis, we return true
    //     if (result != null)
    //         return true;

    //     return false;
    // }
}
