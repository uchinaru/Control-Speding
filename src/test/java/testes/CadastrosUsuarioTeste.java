package testes;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.jb.erp.controller.FormAbstract;
import com.jb.erp.model.User;
import com.jb.erp.repository.TipoUsuario;
import com.jb.erp.repository.UsersSearch;
import com.jb.erp.util.encryptUtils;

class CadastrosUsuarioTeste extends FormAbstract {
	
	private static final long serialVersionUID = 1L;

	EntityManager EntityManager() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("ControlSpending");
	        EntityManager em = emf.createEntityManager();
	        return em;
	}
	
	@Test
	void cadastroUser() {
		
		User usuario = new User();
		usuario.setNome("usuario.teste");
		usuario.setLogin("teste");
		usuario.setSenha(encryptUtils.encryptPasswordMD5("teste"));
		usuario.setEmail("teste@teste.com.br");
		usuario.setDataAniversario(new Date());
		usuario.setTipoUsuario(TipoUsuario.B);
		usuario.setDeletado(false);
		
		boolean salvou = false;
		
		try {
			UsersSearch users = new UsersSearch(EntityManager());
			users.save(usuario);
			salvou = true;
		} catch (Exception e) {
		}
		//Assertions.assertEquals(true, salvou);
		Assertions.assertNotEquals(false, salvou);
	}


}
