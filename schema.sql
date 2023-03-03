create database acme_bank;
use acme_bank;

create table accounts ( 
	account_id varchar(40) not null,
    name varchar(100) not null,
    balance decimal(30,2) not null,
    constraint pk_id primary key(account_id)
);

insert into accounts (account_id, name, balance) values ("V9L3Jd1BBI", "fred", 100.00);
insert into accounts (account_id, name, balance) values ("fhRq46Y6vB", "barney", 300.00);
insert into accounts (account_id, name, balance) values ("uFSFRqUpJy", "wilma", 1000.00);
insert into accounts (account_id, name, balance) values ("ckTV56axff", "betty", 1000.00);
insert into accounts (account_id, name, balance) values ("Qgcnwbshbh", "pebbles", 50.00);
insert into accounts (account_id, name, balance) values ("if9l185l18", "bambam", 50.00);

select * from accounts;

update accounts set balance = 110.00 where account_id = "V9L3Jd1BBI";

select * from accounts where account_id = "V9L3Jd1BBI";