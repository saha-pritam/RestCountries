package org.pritam.restCountries.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
	private String token;
	private int expiry;
}
