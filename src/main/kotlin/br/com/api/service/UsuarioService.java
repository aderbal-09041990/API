package br.com.api.service;

import br.com.api.dto.LoginRequestDTO;
import br.com.api.dto.LoginResponseDTO;
import br.com.api.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario save(Usuario usuario);
    Optional<Usuario> find(Long id);
    List<Usuario> findAll();
    void delete(Long id);
    Optional<Usuario> findByEmail(String email);

    LoginResponseDTO autenticar(LoginRequestDTO loginRequestDTO);
}
