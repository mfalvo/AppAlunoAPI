package br.com.spring.login.controller;

import br.com.spring.login.model.Usuario;
import br.com.spring.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Endpoint para criar um novo usuário
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Endpoint para buscar todos os usuários
    @GetMapping
    public Iterable<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public Optional<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    // Endpoint para atualizar um usuário
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setIdUsuario(id);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    // Endpoint para deletar um usuário por ID
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    // Método de login customizado usando o método definido no UsuarioRepository
    //@GetMapping("/login")
    //public Usuario login(@RequestParam String login, @RequestParam String senha) {
    //    return usuarioRepository.login(login, senha);
    //}
    
    @GetMapping("/login")
    public boolean login(@RequestParam String login, @RequestParam String senha) {
        Usuario usuario = usuarioRepository.login(login, senha);
        return usuario != null; // Retorna true se o usuário foi encontrado, senão retorna false
    }
    
    @PostMapping("/login")
    public boolean login(@RequestBody Usuario usuario) {
        String login = usuario.getLogin();
        String senha = usuario.getSenha();
        
        Usuario usuarioEncontrado = usuarioRepository.login(login, senha);
        
        return usuarioEncontrado != null;
    }
}
