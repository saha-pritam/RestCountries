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
@Table(name = "tld")
public class Tld {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "value")
	private String value;
	@Column(name = "cca2")
	private String cca2;
}
