package br.com.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 9173297557936400119L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="idproduto")
	@TableGenerator(
			table = "sequences", 
			name = "id_produto",
			pkColumnName = "sequence_name",
            valueColumnName = "sequence_next_hi_value",
            pkColumnValue = "id_produto",
            allocationSize=1)	
	@Column(name="ID")
	protected Long id;
	
	@NotNull(message="O codigo não pode ser null.")
	@Column(name="codigo")
	private Long codigo;
	
	@Length(max=100 ,message="O nome tem mais de 100 caracteres.")
	@Column(name="nome")
	private String nome;
	
	@Length(max=20 ,message="O Codigo de fabrica tem mais de 20 caracteres.")
	@Column(name="codigo_barras", length=20)
	private String codigoBarras;

	@Column(name="volume")
	private Double volume;

	@Column(name="VALOR")
	private Double valor;

	@Column(name="PESO")
	private Double peso;

	@Temporal(TemporalType.DATE)
	@Column(name="VENCTO")
	private Date vencimento;
}
