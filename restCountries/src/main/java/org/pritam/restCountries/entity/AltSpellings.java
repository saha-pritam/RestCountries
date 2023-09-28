package org.pritam.restCountries.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "altspellings")
public class AltSpellings {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "cca2")
	private String cca2;
	@Column(name = "spelling")
	private String spelling;
}
