package br.com.api.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pedido_item")
public class PedidoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="id_pedido_item")
	@TableGenerator(
			table = "sequences", 
			name = "id_pedido_item",
			pkColumnName = "sequence_name",
            valueColumnName = "sequence_next_hi_value",
            pkColumnValue = "id_pedido_item",
            allocationSize=1)	
	@Column(name="ID")
	private Long id;

	@NotNull(message="O codigo não pode ser null.")
	@Column(name="codigo")
	private Long codigo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	@JsonBackReference 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_pedido", referencedColumnName="ID")
	private Pedido pedido;
	
	@NotNull(message="A quantidade deve ser informada.")
	@Column(name="quantidade")
	private Double quantidade;
	
	@Column(name="valor")
	private Double valor;

}
