package dev.leeshuyun.paf_assessment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dev.leeshuyun.paf_assessment.models.Account;

@Repository
public class AccountsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public List<Account> getAllAccounts(){
        String selectAllFromAcc = "select * from accounts";
        
        return jdbcTemplate.query(selectAllFromAcc, new ResultSetExtractor<List<Account>>(){
            
            @Override
            public List<Account> extractData(ResultSet rs) throws SQLException, DataAccessException {
                
                List<Account> accList = new ArrayList<Account>();
                
                //retrieve all records as Account objs
                while (rs.next()){
                    Account currentAcc = new Account();
                    currentAcc.setAccountId(rs.getString("account_id"));
                    currentAcc.setBalance(rs.getFloat("balance"));
                    currentAcc.setName(rs.getString("name"));
                    accList.add(currentAcc);
                }
                return accList;
            }
            
        });
        
    }

    public Boolean updateAccountBalance(Float balance, String accountId) {
        int updated = 0;
        String updateBalanceSQLCmd = "update accounts set balance = ? where account_id = ?";

        Account acc = getAccountUsingId(accountId);
        
        updated = jdbcTemplate.update(updateBalanceSQLCmd, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setFloat(1, acc.getBalance()); //1st arg
                ps.setString(1, acc.getAccountId()); //2nd arg
            }

        });

        return updated > 0; //evaluate to true for 1
    }

    public Account getAccountUsingId(String id){
        String getBalanceUsingIdSQL = "select * from accounts where account_id = ?";

        return jdbcTemplate.queryForObject(getBalanceUsingIdSQL, BeanPropertyRowMapper.newInstance(Account.class), id);
    }



}
