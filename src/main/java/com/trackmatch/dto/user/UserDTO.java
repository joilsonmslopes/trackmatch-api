package com.trackmatch.dto.user;

import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.domain.enums.ProfileType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(message = "O nome deve conter pelo menos 3 caracteres", min = 3)
    private String name;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(message = "A senha deve conter pelo menos 6 caracteres", min = 6)
    private String password;

    @NotNull(message = "Tipo de usuário é obrigatório")
    private ProfileType profileType;

    @NotBlank(message = "Celular é obrigatório")
    private String phone;

    @NotBlank(message = "Adicionar pelo menos 1 instrumento")
    private String instruments;
    private String styles;

    @NotBlank(message = "Cidade é obrigatório")
    private String city;

    @NotBlank(message = "Estado é obrigatório")
    private String state;
    private String bio;
    private List<EventEntity> events = new ArrayList<>();
}
