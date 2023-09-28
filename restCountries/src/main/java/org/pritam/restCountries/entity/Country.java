package org.pritam.restCountries.entity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import org.pritam.restCountries.model.Car;
import org.pritam.restCountries.model.Idd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="country")
@JsonInclude(Include.NON_NULL)
public class Country {
	@Transient
	private LinkedHashMap<String,Object> name;
	@Transient
	private List<String> tld;
	@Id
	@Column(name="cca2")
	private String cca2;
	@Column(name="ccn3")
	private String ccn3;
	@Column(name="cca3")
	private String cca3;
	@Column(name="cioc")
	private String cioc;
	@Column(name="independent")
    private boolean independent;
	@Column(name="status")
    private String status;
	@Column(name="unmember")
    private boolean unMember;
	@Transient
	private TreeMap<String,LinkedHashMap<String, String>> currencies;
	@Transient
	private Idd idd;
	@Transient
	private List<String> capital;
	@Transient
	private List<String> altSpellings;
	@Column(name="region")
    private String region;
	@Column(name="subregion")
    private String subregion;
	@Transient
	private TreeMap<String,String> languages;
	@Transient
	TreeMap<String,LinkedHashMap<String,String>> translations;
	@Transient
	List<Double> latlng;
	@Column(name="landlocked")
    private boolean landlocked;
	@Transient
	private List<String> borders;
	@Column(name="area")
    private double area;
	@Transient
	private TreeMap<String,LinkedHashMap<String,String>> demonyms;
	@Column(name="flag")
    private String flag;
	@Transient
	private LinkedHashMap<String,String> maps;
	@Column(name="population")
    private long population;
	@Transient
	private TreeMap<String,Double> gini;
	@Column(name="fifa")
    private String fifa;
	@Transient
	private Car car;
	@Transient
	private List<String> timezones;
	@Transient
	private List<String> continents;
	@Transient
	LinkedHashMap<String,String> flags;
	@Transient
	LinkedHashMap<String,String> coatOfArms;
	@Column(name="startofweek")
    private String startofweek;
	@Transient
	private HashMap<String, List<Double>> capitalInfo;
	@Transient
	private LinkedHashMap<String,String> postalCode;
}
