package com.qa.business;

import javax.inject.Inject;

import com.qa.persistance.util.JSONUtil;
import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountRepository;

public class AccountBusinessImp implements AccountBusiness {

	@Inject
	private AccountRepository repo;
	@Inject
	private JSONUtil json = new JSONUtil();

	@Override
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	@Override
	public String createAccount(String account) {
		Account newAcc = json.getObjectForJSON(account, Account.class);
		if (newAcc.getAccountNumber() == 9) {
			return "{\"message\": \"Cannot create account.\"}";
		}
		
		return repo.createAccount(account);
	}

	@Override
	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);
	}

	@Override
	public String updateAccount(Long id, String account) {
		return repo.updateAccount(id, account);
	}

}
