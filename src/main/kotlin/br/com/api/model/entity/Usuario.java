package br.com.api.model.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 9173297557936400119L;

    @Id
    @TableGenerator(
            table = "sequences",
            name = "id_usuario",
            pkColumnName = "sequence_name",
            valueColumnName = "sequence_next_hi_value",
            pkColumnValue = "id_usuario",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator="id_usuario")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 200, min = 10 ,message = "Nome deve possuir entre 10 e 200 caracteres.")
    @Column(name="nome")
    private String nome;

    @Column(name="cpf")
    private String cpf;

    @Column(name = "senha")
    private String senha;

    @NotBlank(message = "E-mail é obrigatório.")
    @Size(max =  100, min = 10 ,message = "E-mail deve possuir entre 10 e 100 caracteres.")
    @Email(message = "E-mail é inválido.")
    @Column(name = "email")
    private String email;

    @Column(name = "ativo")
    private Boolean ativo = Boolean.TRUE;

}
