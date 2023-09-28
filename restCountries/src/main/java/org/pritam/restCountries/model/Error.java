package org.pritam.restCountries.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
	private int errorCode;
	private String description;
}
