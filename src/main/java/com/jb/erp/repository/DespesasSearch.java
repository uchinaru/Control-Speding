package com.jb.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jb.erp.model.Despesa;

public class DespesasSearch implements Serializable{
	private static final long serialVersionUID = -5542000124828559849L;
	
	@Inject
	private EntityManager manager;

	public DespesasSearch() {
	}
	
	public DespesasSearch(EntityManager manager) {
		this.manager = manager;
	}
	
	public Despesa findById(Long id) {
		return manager.find(Despesa.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Despesa> findDespesasWithUserId(Long userId) {
		
		Query query = manager.createQuery("from Despesa where deletado = 0 and userId = :userId");
		query.setParameter("userId", userId);
		
		return query.getResultList();
	}
	
	public boolean save(Despesa despesa) {

		try {
			manager.merge(despesa);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean deletar(Despesa despesa) {

		try {
			despesa.setDeletado(true);
			save(despesa);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
