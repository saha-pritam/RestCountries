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
@Table(name = "capitalinfo_latlng")
public class CapitalInfoLatLng {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "cca2")
	private String cca2;
	@Column(name = "lat")
	private double lat;
	@Column(name = "lng")
	private double lng;
}
