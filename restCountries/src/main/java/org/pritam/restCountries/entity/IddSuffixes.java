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
@Table(name = "idd_suffixes")
public class IddSuffixes {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "suffix")
	private String suffix;
	@Column(name = "cca2")
	private String cca2;
}
