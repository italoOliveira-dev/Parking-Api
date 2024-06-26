package br.com.projeto.parking_api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.parking_api.exception.EntityNotFoundException;
import br.com.projeto.parking_api.exception.UsernameValueUniqueException;
import br.com.projeto.parking_api.models.entity.Usuario;
import br.com.projeto.parking_api.models.repository.UsuarioRepository;
import br.com.projeto.parking_api.web.dto.UsuarioCreateDto;
import br.com.projeto.parking_api.web.dto.UsuarioResponseDto;
import br.com.projeto.parking_api.web.dto.UsuarioUpdatePasswordDto;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponseDto newUsuario(UsuarioCreateDto usuarioCreateDto) {
        try {
            return UsuarioResponseDto.fromUsuario(usuarioRepository.save(usuarioCreateDto.toUsuario()));
        } catch(DataIntegrityViolationException ex) {
            throw new UsernameValueUniqueException(String.format("Username '%s' já foi cadastrado", usuarioCreateDto.username()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado.", id)));
    }

    
    public UsuarioResponseDto getUsuario(Long id) {
        Usuario user = getUsuarioById(id);
        return UsuarioResponseDto.fromUsuario(user);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDto> getAllUsuarios() {
        List<UsuarioResponseDto> users = usuarioRepository.findAll().stream().map(UsuarioResponseDto::fromUsuario).toList();
        return users;
    }

    @Transactional
    public void updatePasswordUsuario(Long id, UsuarioUpdatePasswordDto updatePasswordDto) {
        Usuario user = getUsuarioById(id);

        if (!user.getPassword().equals(updatePasswordDto.currentPassword())) {
            new RuntimeException("Sua senha atual está errada!");
        }

        if (updatePasswordDto.newPassword().equals(updatePasswordDto.confirmPassword())){
            new RuntimeException("Senhas não conferem!");
        }

        user.setPassword(updatePasswordDto.newPassword());
    }
}
