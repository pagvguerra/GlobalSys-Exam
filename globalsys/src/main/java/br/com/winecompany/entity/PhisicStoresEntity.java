package br.com.winecompany.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CEP")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PhisicStoresEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name= "id")
	private Long id;	

	@Column(name= "codigo_loja")
	private String storesCode;

	@Column(name = "faixa_inicio")
	private Long initialRange;

	@Column(name = "faixa_fim")
	private Long finalRange;
}
