package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistance.util.JSONUtil;
import com.qa.persistence.domain.Account;


@Transactional(SUPPORTS)
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")		//Configures entity manager
	private EntityManager em;
	
	@Inject
	private JSONUtil json;

	public String getAllAccounts() {
		Query allAccounts = em.createQuery("Select a FROM Account a");
		return json.getJSONForObject(allAccounts.getResultList());
	}	

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account newAcc = json.getObjectForJSON(account, Account.class);
		em.persist(newAcc);
		return "{\"message\": \"account sucessfully created\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		Account delAccount = findAccount(id);
		if (delAccount != null) {
			em.remove(delAccount);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	
	@Override
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String account) {
		Account updateAcc = findAccount(id);
		deleteAccount(id);
		createAccount(account);
		return "{\"message\": \"account sucessfully updated\"}";
	}
	
	
	public Account findAccount(Long id) {
		return em.find(Account.class, id);
	}
	
}
