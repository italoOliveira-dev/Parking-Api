package br.com.projeto.parking_api.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.parking_api.models.service.UsuarioService;
import br.com.projeto.parking_api.web.dto.UsuarioCreateDto;
import br.com.projeto.parking_api.web.dto.UsuarioResponseDto;
import br.com.projeto.parking_api.web.dto.UsuarioUpdatePasswordDto;

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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        UsuarioResponseDto user = usuarioService.getUsuario(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<UsuarioResponseDto> users = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UsuarioUpdatePasswordDto updatePasswordDto) {
        usuarioService.updatePasswordUsuario(id, updatePasswordDto);
        return ResponseEntity.noContent().build();
    }
}
