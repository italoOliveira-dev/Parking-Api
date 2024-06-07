package br.com.projeto.parking_api.web.dto;

import br.com.projeto.parking_api.models.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCreateDto(
    
    @NotBlank(message = "campo é obrigatório!")
    @Email(
        regexp = "[a-z0-9._+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
        message = "formato do e-mail está inválido!")
    String username,

    @NotBlank(message = "campo é obrigatório!")
    @Size(
        min = 6,
        max = 6,
        message = "senha deve conter 6 dígitos!")
    String password) {
    
    public Usuario toUsuario() {
        return new Usuario(username, password);
    }
}
