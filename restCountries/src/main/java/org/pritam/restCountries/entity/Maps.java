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
@Table(name = "Maps")
public class Maps {
	@Id
	@Column(name = "cca2")
	private String cca2;
	@Column(name = "googlemaps")
	private String googleMaps;
	@Column(name = "openstreetmaps")
	private String openStreetMaps;
}
