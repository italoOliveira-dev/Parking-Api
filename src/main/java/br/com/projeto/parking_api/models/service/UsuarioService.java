package br.com.projeto.parking_api.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.parking_api.models.repository.UsuarioRepository;
import br.com.projeto.parking_api.web.dto.UsuarioCreateDto;
import br.com.projeto.parking_api.web.dto.UsuarioResponseDto;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDto newUsuario(UsuarioCreateDto usuarioCreateDto) {
        return UsuarioResponseDto.fromUsuario(usuarioRepository.save(usuarioCreateDto.toUsuario()));
    }
}
