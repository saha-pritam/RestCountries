package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.CarSide;
import org.pritam.restCountries.model.Car;
import org.pritam.restCountries.repository.CarSideRepository;
import org.pritam.restCountries.repository.CarSignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CarService {
	@Autowired
	private CarSignsRepository carSignsRepository;

	@Autowired
	private CarSideRepository carSideRepository;

	@Transactional
	public Car getCar(String cca2) {
		Car car = null;
		Optional<List<String>> signs = carSignsRepository.getAllSignsByCca2(cca2);
		if (signs.isPresent() && signs.get().size() > 0) {
			car = new Car();
			car.setSigns(signs.get());
		}
		Optional<CarSide> side = carSideRepository.findById(cca2);
		if (side.isPresent() && side.get().getSide() != null) {
			if (car == null)
				car = new Car();
			car.setSide(side.get().getSide());
		}
		return car;
	}
}
