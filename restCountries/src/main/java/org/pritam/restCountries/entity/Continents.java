package org.pritam.restCountries.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "continents")
public class Continents {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "cca2")
	private String cca2;
	@Column(name = "name")
	private String name;
}
