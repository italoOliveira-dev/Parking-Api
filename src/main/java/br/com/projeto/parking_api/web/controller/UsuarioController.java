package br.com.projeto.parking_api.web.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.parking_api.models.service.UsuarioService;
import br.com.projeto.parking_api.web.dto.UsuarioCreateDto;
import br.com.projeto.parking_api.web.dto.UsuarioResponseDto;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto usuarioCreateDto, UriComponentsBuilder uriBuilder) {
        UsuarioResponseDto user = usuarioService.newUsuario(usuarioCreateDto);
        URI uri = uriBuilder.path("api/v1/usuarios/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
