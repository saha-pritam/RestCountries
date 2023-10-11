package org.pritam.restCountries.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.pritam.restCountries.entity.Currencies;
import org.pritam.restCountries.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Transactional
	public TreeMap<String,LinkedHashMap<String, String>> getCurrencies(String cca2) {
		TreeMap<String,LinkedHashMap<String, String>> treeMap = null;
		Optional<List<Currencies>> currencies = currencyRepository.findByCca2(cca2);
		if(currencies.isPresent()) {
			treeMap = new TreeMap<String,LinkedHashMap<String, String>>(currencies.get().stream().collect(Collectors.toMap(currency->currency.getCurrencycode(), currency->{
				LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
				linkedHashMap.put("name", currency.getName());
				linkedHashMap.put("symbol", currency.getSymbol());
				return linkedHashMap;
			})));
		}
		return treeMap;
	}
	
	@Transactional
	public List<String> getCountryByCurrencyCodeOrName(String currencyCodeOrName) {
		Optional<List<String>> cca2List= currencyRepository.findByCurrencyCodeOrName(currencyCodeOrName);
		if(cca2List.isPresent() && cca2List.get().size()>0)
			return cca2List.get();
		return null;
	}
}
