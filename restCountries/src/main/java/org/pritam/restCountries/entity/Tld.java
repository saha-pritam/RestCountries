package org.pritam.restCountries.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
