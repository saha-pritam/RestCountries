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
@Table(name = "languages")
public class Languages {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "cca2")
	private String cca2;
	@Column(name = "langcode")
	private String langcode;
	@Column(name = "langname")
	private String langname;
}
