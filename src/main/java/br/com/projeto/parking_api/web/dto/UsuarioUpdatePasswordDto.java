package br.com.projeto.parking_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdatePasswordDto(
    @NotBlank(message = "campo é obrigatório!")
    @Size(
        min = 6,
        max = 6,
        message = "senha deve conter 6 dígitos!")
    String currentPassword,

    @NotBlank(message = "campo é obrigatório!")
    @Size(
        min = 6,
        max = 6,
        message = "senha deve conter 6 dígitos!")
    String newPassword,
    
    @NotBlank(message = "campo é obrigatório!")
    @Size(
        min = 6,
        max = 6,
        message = "senha deve conter 6 dígitos!")
    String confirmPassword) {
}
