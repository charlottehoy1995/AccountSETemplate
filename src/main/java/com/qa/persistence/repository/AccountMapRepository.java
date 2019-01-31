package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import com.qa.persistance.util.JSONUtil;
import com.qa.persistence.domain.Account;

@Alternative
public class AccountMapRepository implements AccountRepository {

	private Map<Long, Account> account = new HashMap<>();

	private JSONUtil json = new JSONUtil();

	public String getAllAccounts() {

		return json.getJSONForObject(account);
	}
	
	public String createAccount(String jsonString) {

		Account newAcc = json.getObjectForJSON(jsonString, Account.class);
		Long id = (long) newAcc.getAccountNumber();
		account.put(id, newAcc);
		return "Created new account with first name: " + newAcc.getFirstName() + ", Last name: " + newAcc.getLastName()
				+ ", Account Number: " + newAcc.getAccountNumber();
	}

	public String deleteAccount(Long id) {

		account.remove(id);
		return "Removed account by the id: " + id;
	}

	public String updateAccount(Long id, String account) {
		return null;
	}

	public String findAccount(Long id) {
		return json.getJSONForObject(account.get(id));
	}

	public int getCountByFirstNames(String firstName) {
		int count = 0;
		for (Map.Entry<Long, Account> AccEntry : account.entrySet()) {
			if (AccEntry.getValue().getFirstName().equals(firstName)) {
				count++;
			}

		}
		return count;
	}

}
