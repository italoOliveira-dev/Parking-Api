package br.com.projeto.parking_api.web.dto;

public record UsuarioUpdatePasswordDto(String currentPassword, String newPassword, String confirmPassword) {
    
}
