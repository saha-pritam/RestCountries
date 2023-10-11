package org.pritam.restCountries.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "currencies")
public class Currencies {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "currencycode")
	private String currencycode;
	@Column(name = "name")
	private String name;
	@Column(name = "symbol")
	private String symbol;
	@Column(name = "cca2")
	private String cca2;
}
