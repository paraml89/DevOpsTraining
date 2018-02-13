package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepo;

public class AccountServiceImpl implements AccountService {

	AccountRepo accountRepo;

	public AccountServiceImpl(AccountRepo accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}

	@Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientInitialAmountException {
		if (amount < 500) {
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		if (accountRepo.save(account)) {
			accountRepo.save(account);
		}

		return account;
	}

	@Override
	public Account withDrawAmount(int accountNumber, int amount) throws InsufficientBalanceException {
		if(amount<0)
		{
			throw new InsufficientBalanceException();
		}
		
		Account account=new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		System.out.println("Rs. "+amount+" has been debited from account "+accountNumber);
		return account;
	}

	@Override
	public Account depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException {
		
		Account account=accountRepo.searchAccount(accountNumber);
		if(account==null)
		{
			throw new InvalidAccountNumberException();
		}
		account.setAmount(account.getAmount()+amount);		
		System.out.println("Rs. "+amount+" has been debited from account "+accountNumber);
		return account;
		
	}
	
	
	
		

}
