package br.com.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

}
