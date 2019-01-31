package com.qa.MapTests;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.repository.AccountMapRepository;

public class AccountServiceTest {
	
	private  String MOCK_DATA_ARRAY_ONE = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"accountNumber\":1234}";
	private  String MOCK_DATA_ARRAY_TWO = "{\"firstName\":\"Charlotte\",\"lastName\":\"Hoy\",\"accountNumber\":1256}";
	private  String MOCK_DATA_ARRAY_THREE = "{\"firstName\":\"Charlotte\",\"lastName\":\"Hoy\",\"accountNumber\":1286}";
	
	
	private AccountMapRepository repo;

	@Before
	public void setup() {
		repo = new AccountMapRepository();
	}
	
	@Test
	public void addAccountTest() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		assertEquals(MOCK_DATA_ARRAY_ONE, repo.findAccount(1234L));
	}


	@Test
	public void add2AccountTest() {
		String reply = repo.createAccount(MOCK_DATA_ARRAY_TWO);
		Assert.assertEquals("Created new account with first name: Charlotte, Last name: Hoy, Account Number: 1256", reply);
	}

	@Test
	public void removeAccountTest() {
		String reply = repo.deleteAccount(1234L);
	}
	
	
	
	@Test
	public void remove2AccountTest() {
		
	}

	
	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		
	}
	
	@Test
	public void accountConversionToJSONTestWithEmptyMap() {
	
	}
	
	@Test
	public void accountConversionToJSONTestEmptyMapWithConversion() {
	
	}

	@Test
	public void accountConversionToJSONTest() {
		
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		repo.createAccount(MOCK_DATA_ARRAY_TWO);
		assertEquals(0, repo.getCountByFirstNames("Sonia"));
	}
	
	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		repo.createAccount(MOCK_DATA_ARRAY_TWO);
		assertEquals(1, repo.getCountByFirstNames("Charlotte"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenMult() {
		repo.createAccount(MOCK_DATA_ARRAY_ONE);
		repo.createAccount(MOCK_DATA_ARRAY_TWO);
		repo.createAccount(MOCK_DATA_ARRAY_THREE);
		assertEquals(2, repo.getCountByFirstNames("Charlotte"));
	}

}

