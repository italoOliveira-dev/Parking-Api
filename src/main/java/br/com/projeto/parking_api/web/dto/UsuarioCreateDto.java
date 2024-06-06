package br.com.projeto.parking_api.web.dto;

import br.com.projeto.parking_api.models.entity.Usuario;

public record UsuarioCreateDto(String username, String password) {
    
    public Usuario toUsuario() {
        return new Usuario(username, password);
    }
}
