package br.com.projeto.parking_api.web.dto;

import br.com.projeto.parking_api.models.entity.Usuario;

public record UsuarioResponseDto(Long id, String username, String role) {

    public static UsuarioResponseDto fromUsuario(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        return new UsuarioResponseDto(usuario.getId(), usuario.getUsername(), role);
    }
    
}
