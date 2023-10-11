package org.pritam.restCountries.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.pritam.restCountries.entity.Country;
import org.pritam.restCountries.model.Idd;
import org.pritam.restCountries.model.Error;
import org.pritam.restCountries.services.AltSpellingsService;
import org.pritam.restCountries.services.BordersService;
import org.pritam.restCountries.services.CapitalInfoLatLngService;
import org.pritam.restCountries.services.CapitalService;
import org.pritam.restCountries.services.CarService;
import org.pritam.restCountries.services.CoatOfArmsService;
import org.pritam.restCountries.services.ContinentsService;
import org.pritam.restCountries.services.CountryService;
import org.pritam.restCountries.services.CurrencyService;
import org.pritam.restCountries.services.DemonymsService;
import org.pritam.restCountries.services.FlagsService;
import org.pritam.restCountries.services.GiniService;
import org.pritam.restCountries.services.IddService;
import org.pritam.restCountries.services.LanguagesService;
import org.pritam.restCountries.services.LatLngService;
import org.pritam.restCountries.services.MapsService;
import org.pritam.restCountries.services.NameService;
import org.pritam.restCountries.services.NativeNameService;
import org.pritam.restCountries.services.PostalCodeService;
import org.pritam.restCountries.services.TimezonesService;
import org.pritam.restCountries.services.TldService;
import org.pritam.restCountries.services.TranslationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v3.1")
public class RestCountriesController {

	@Autowired
	private CountryService countryService;
	@Autowired
	private NameService nameService;
	@Autowired
	private NativeNameService nativeNameService;
	@Autowired
	private TldService tldService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private IddService iddService;
	@Autowired
	private CapitalService capitalService;
	@Autowired
	private AltSpellingsService altSpellingsService;
	@Autowired
	private LanguagesService languagesService;
	@Autowired
	private TranslationsService translationsService;
	@Autowired
	private LatLngService latLngService;
	@Autowired
	private BordersService bordersService;
	@Autowired
	private DemonymsService demonymsService;
	@Autowired
	private MapsService mapsService;
	@Autowired
	private GiniService giniService;
	@Autowired
	private CarService carService;
	@Autowired
	private TimezonesService timezonesService;
	@Autowired
	private ContinentsService continentsService;
	@Autowired
	private FlagsService flagsService;
	@Autowired
	private CoatOfArmsService coatOfArmsService;
	@Autowired
	private CapitalInfoLatLngService capitalInfoLatLngService;
	@Autowired
	private PostalCodeService postalCodeService;

	@GetMapping("/all")
	public ResponseEntity<Object> all() {
		List<String> cca2List = null;
		cca2List = countryService.getAllCca2();
		
		if (cca2List == null) {
			Error error = new Error(404, "No countries available in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Object> name(@PathVariable("name") String name,
			@RequestParam(name = "fullText", required = false) boolean fullText) {
		List<String> cca2List = null;

		if (fullText)
			cca2List = nameService.getCca2ByCommonOrOfficialFullMatch(name);
		else
			cca2List = nameService.getCca2ByCommonOrOfficialPatternMatch(name);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided name in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/alpha/{code}")
	public ResponseEntity<Object> code(@PathVariable("code") String code) {
		List<String> cca2List = null;
		cca2List = countryService.getCountryByCode(code);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided code in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/alpha")
	public ResponseEntity<Object> listOfCode(@RequestParam(name="codes",required = true)List<String> codes) {
		List<String> cca2List = null;
		cca2List = countryService.getCountryByListOfCodes(codes);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided codes in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/currency/{currency}")
	public ResponseEntity<Object> currency(@PathVariable("currency") String currencyCodeOrName) {
		List<String> cca2List = null;
		cca2List = currencyService.getCountryByCurrencyCodeOrName(currencyCodeOrName);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided currency code or name in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/demonym/{demonym}")
	public ResponseEntity<Object> demonym(@PathVariable("demonym") String demonym) {
		List<String> cca2List = null;
		cca2List = demonymsService.getCountryByDemonym(demonym);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided demonym in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/lang/{language}")
	public ResponseEntity<Object> language(@PathVariable("language") String languageCodeOrName) {
		List<String> cca2List = null;
		cca2List = languagesService.getCountryByLanguagesCodeOrName(languageCodeOrName);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided language code or name in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/capital/{capital}")
	public ResponseEntity<Object> capital(@PathVariable("capital") String capitalName) {
		List<String> cca2List = null;
		cca2List = capitalService.getCountryByCapitalName(capitalName);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided capital name in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/region/{region}")
	public ResponseEntity<Object> region(@PathVariable("region") String regionName) {
		List<String> cca2List = null;
		cca2List = countryService.getCountryByRegionName(regionName);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided region name in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/subregion/{subregion}")
	public ResponseEntity<Object> subRegion(@PathVariable("subregion") String subRegionName) {
		List<String> cca2List = null;
		cca2List = countryService.getCountryBySubRegionName(subRegionName);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided sub region name in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/translation/{translation}")
	public ResponseEntity<Object> translation(@PathVariable("translation") String translationOfficialOrCommon) {
		List<String> cca2List = null;
		cca2List = translationsService.getCountryByTranslationOfficialOrCommon(translationOfficialOrCommon);

		if (cca2List == null) {
			Error error = new Error(404, "There are no countries found with the provided sub region name in the database.");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}

		List<Object> countryList = cca2List.stream().map(countryCca2 -> {
			Country country = countryService.getCountryByCca2(countryCca2);
			country.setName(nameService.getName(country.getCca2()));
			TreeMap<String, LinkedHashMap<String, String>> m = nativeNameService.getNativeNames(country.getCca2());
			if (m != null)
				country.getName().put("nativeName", m);
			country.setTld(tldService.getTld(country.getCca2()));
			country.setCurrencies(currencyService.getCurrencies(country.getCca2()));
			country.setIdd(new Idd());
			country.getIdd().setRoot(iddService.getIddRoot(country.getCca2()));
			country.getIdd().setSuffixes(iddService.getIddSuffixes(country.getCca2()));
			country.setCapital(capitalService.getCapital(country.getCca2()));
			country.setAltSpellings(altSpellingsService.getAltSpellings(country.getCca2()));
			country.setLanguages(languagesService.getLanguages(country.getCca2()));
			country.setTranslations(translationsService.getTranslations(country.getCca2()));
			country.setLatlng(latLngService.getLatLng(country.getCca2()));
			country.setBorders(bordersService.getBorders(country.getCca2()));
			country.setDemonyms(demonymsService.getDemonyms(country.getCca2()));
			country.setMaps(mapsService.getMaps(country.getCca2()));
			country.setGini(giniService.getGini(country.getCca2()));
			country.setCar(carService.getCar(country.getCca2()));
			country.setTimezones(timezonesService.getTimezones(country.getCca2()));
			country.setContinents(continentsService.getContinents(country.getCca2()));
			country.setFlags(flagsService.getCoatOfArms(country.getCca2()));
			country.setCoatOfArms(coatOfArmsService.getCoatOfArms(country.getCca2()));
			country.setCapitalInfo(capitalInfoLatLngService.getLatLng(country.getCca2()));
			country.setPostalCode(postalCodeService.getPostalCode(country.getCca2()));
			return country;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
}
