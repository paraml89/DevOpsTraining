package com.capgemini.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;


public class AccountServiceImplTest {

	@Mock
	AccountRepo accountRepo;
	AccountService accountService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		accountService=new AccountServiceImpl(accountRepo);
	}
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialAmountException.class)
	public void whenTheAmountIsLessThanFiveHundredSystemShouldThrowException() throws InsufficientInitialAmountException
	{
		accountService.createAccount(101, 400);
	}
	
	@Test
	public void whenValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientInitialAmountException
	{
		Account account=new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepo.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenWithDepositAmountIsLessThanOrEqualToZeroSystemShouldThrowException() throws InvalidAccountNumberException
	{
		accountService.depositAmount(101, 200);
	}
	
	@Test
	public void whenAmountSuccessfullyDepositedAmountIsLessThanOrEqualToZeroSystemShouldThrowException() throws InsufficientInitialAmountException
	{
		Account account=new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		when(accountRepo.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}

}
