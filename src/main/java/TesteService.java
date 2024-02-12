import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jb.erp.model.TipoUsuario;
import com.jb.erp.model.User;
import com.jb.erp.repository.UsersSearch;
import com.jb.erp.util.encryptUtils;

public class TesteService {
	
	
	public static void main(String[] args) {
		
		User usuario = new User();
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ControlSpending");
		EntityManager manager = factory.createEntityManager();
		
		usuario.setNome("Jonatas F Benicio");
		usuario.setLogin("admin");
		usuario.setSenha(encryptUtils.encryptPasswordMD5("teste"));
		usuario.setTipoUsuario(TipoUsuario.A);
		usuario.setEmail("teste@teste.com.br");
		usuario.setDataAniversario(new Date());
		usuario.setDeletado(false);
		
		UsersSearch userSearch = new UsersSearch(manager);
		
		userSearch.Save(usuario);
	}

}
