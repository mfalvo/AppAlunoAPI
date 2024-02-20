package br.com.spring.login.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.login.model.Usuario;
import ch.qos.logback.core.joran.conditional.ElseAction;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u WHERE u.login LIKE %:login AND u.senha LIKE %:senha")
	Usuario login(String login, String senha);
}
