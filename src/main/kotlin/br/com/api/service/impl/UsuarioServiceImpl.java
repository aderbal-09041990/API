package br.com.api.service.impl;

import br.com.api.dto.LoginRequestDTO;
import br.com.api.dto.LoginResponseDTO;
import br.com.api.exception.CustomException;
import br.com.api.model.entity.Usuario;
import br.com.api.model.repository.UsuarioRepository;
import br.com.api.service.UsuarioService;
import br.com.api.util.ApiUtil;
import br.com.api.util.JwtUtils;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Usuario save(Usuario usuario) {
        try{

            usuario.setSenha(encoder.encode(usuario.getSenha()));

            return usuarioRepository.save(usuario);

        }catch (Exception e){
            log.error("Erro ao registar usuário : " + e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public Optional<Usuario> find(Long id) {
        try{
            return usuarioRepository.findById(id);
        }catch (Exception e){
            log.error("Erro ao buscar usuário com o id : " + id);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<Usuario> findAll() {
        try{
            return usuarioRepository.findAll();
        }catch (Exception e){
            log.error("Erro ao buscar a lista de usuário : " + e);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try{
            usuarioRepository.deleteById(id);
        }catch (Exception e){
            log.error("Erro ao tentar deletar o usuário com o id : " + id);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        try{
            return usuarioRepository.findByEmail(email);
        }catch (Exception e){
            log.error("Erro ao buscar usuário com o email : " + email);
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public LoginResponseDTO autenticar(LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO loginResponse = new LoginResponseDTO();

        try{

            Authentication authentication  = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                                                            loginRequestDTO.getSenha()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            loginResponse.setToken(jwtUtils.generateJwtToken(authentication));


        }catch (Exception e){
            log.error("Erro na autenticação : " + e);
            throw new CustomException("Usuário ou senha inválidos.");
        }

        return loginResponse;
    }
}
