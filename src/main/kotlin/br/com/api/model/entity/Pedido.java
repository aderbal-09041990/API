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
@Table(name="pedido")
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="id_pedido")
	@TableGenerator(
			table = "sequences", 
			name = "id_pedido",
			pkColumnName = "sequence_name",
            valueColumnName = "sequence_next_hi_value",
            pkColumnValue = "id_pedido",
            allocationSize=1)	
	@Column(name="ID")
	protected Long id;

	@NotNull(message="O codigo não pode ser null.")
	@Column(name="codigo")
	private Long codigo;

	@NotNull(message="A data deve ser informada.")
	@Temporal(TemporalType.DATE)
	@Column(name="data")
	private Date data;
	
	@Column(name="hora")
	private Integer hora = 0;
	
	@Column(name="minuto")
	private Integer minuto = 0;
	
	@Length(max=250 ,message="Observação de entrega não deve conter mais de 100 caracteres.")
	@Column(name="observacao")
	private String obsEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name="data_canelamento")
	private Date dataCancelamento;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_faturamento")
	private Date dataFaturamento;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_pendencia")
	private Date dataPendencia;

}
