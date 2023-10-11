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
