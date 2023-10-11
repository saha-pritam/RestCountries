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
@Table(name = "postalcode")
public class PostalCode {
	@Id
	@Column(name = "cca2")
	private String cca2;
	@Column(name = "format")
	private String format;
	@Column(name = "regex")
	private String regex;
}
