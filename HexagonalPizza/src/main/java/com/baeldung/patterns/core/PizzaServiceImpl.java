package com.baeldung.patterns.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baeldung.patterns.domain.Pizza;
import com.baeldung.patterns.port.PizzaRepo;

@Service
public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	private PizzaRepo pizzaRepo;
	

	@Override
	public void createPizza(Pizza pizza) {
		pizzaRepo.createPizza(pizza);
	}

	@Override
	public Pizza getPizza(String name) {
		
		return pizzaRepo.getPizza(name);
	}

	@Override
	public List<Pizza> listPizza() {
		return pizzaRepo.getAllPizza();
	}

}
