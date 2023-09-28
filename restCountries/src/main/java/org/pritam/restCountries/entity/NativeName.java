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
@Table(name = "nativename")
public class NativeName {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "langcode")
	private String langcode;
	@Column(name = "common")
	private String common;
	@Column(name = "official")
	private String official;
	@Column(name = "cca2")
	private String cca2;
}
