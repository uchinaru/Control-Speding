package testes;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.jb.erp.controller.FormAbstract;
import com.jb.erp.model.Despesa;
import com.jb.erp.repository.DespesasSearch;
import com.jb.erp.repository.TipoPagamentos;
import com.jb.erp.util.DateUtils;

class RelatorioDespesaTeste extends FormAbstract {
	
	private static final long serialVersionUID = 1L;

	private Despesa despesa = new Despesa();
	
	EntityManager EntityManager() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("ControlSpending");
	        EntityManager em = emf.createEntityManager();
	        return em;
	}
	
	@Test
	void cadastroDespesa() {
		
		despesa.setNome("teste");
		despesa.setValor((double) 200);
		despesa.setDataCusto(DateUtils.transformaDataSimples(new Date()));
		despesa.setMesGasto(DateUtils.getMes(new Date()));
		despesa.setQuantidade(2);
		despesa.setTipoPagamentos(TipoPagamentos.Debito);
		despesa.setDescricao("Teste unitario");
		despesa.setDeletado(false);
		despesa.setUserId(0l);
		
		DespesasSearch despesaS = new DespesasSearch(EntityManager());
		
		boolean retorno = despesaS.save(despesa);
		
		Assertions.assertEquals(true, retorno);
	}
	
	@Test
	void alterarDespesa() {
		
		DespesasSearch despesaS = new DespesasSearch(EntityManager());
		despesa = despesaS.findById(1l);
		
		System.out.println("Valor despesa: " + despesa.getValor());
		
		despesa.setValor((double) 1000);
		System.out.println("Valor atual despesa: " + despesa.getValor());
		
		boolean retorno = despesaS.save(despesa);
		
		Assertions.assertEquals(true, retorno);
	}
	
	
}
