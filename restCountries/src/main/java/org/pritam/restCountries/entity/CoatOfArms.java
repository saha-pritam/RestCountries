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
@Table(name = "coatofarms")
public class CoatOfArms {
	@Id
	@Column(name = "cca2")
	private String cca2;
	@Column(name = "png")
	private String png;
	@Column(name = "svg")
	private String svg;
}
