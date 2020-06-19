package com.revature.cats.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.cats.exceptions.CatNotFoundException;
import com.revature.cats.exceptions.NotYetImplementedException;
import com.revature.cats.models.Cat;

@Service
public class MockCatService implements CatService {
	
	private List<Cat> mockCats;
	private Integer nextId;
	
	public MockCatService() {
		super();
		this.mockCats = new ArrayList<Cat>();
		this.mockCats.add(new Cat(1, "alley", 100.00));
		this.mockCats.add(new Cat(2, "biscuit II", 180.00));
		this.mockCats.add(new Cat(3, "jeb", 10.50));
		this.mockCats.add(new Cat(4, "johnson", 10000.00));
		this.mockCats.add(new Cat(5, "leia", 378900.00));
		this.mockCats.add(new Cat(6, "diez", 9358900.00));
		this.nextId = 7;
	}

	@Override
	public List<Cat> getAll() {
		return this.mockCats;
	}
	
	@Override
	public Cat getById(Integer id) {
		Cat out = null;
		for(Cat c : this.mockCats) {
			if(c.getCatId().equals(id)) {
				out = c;
				break; // exit the loop early if we find our cat
			}
		}
		if(out == null) {
			throw new CatNotFoundException("Cat with id " + id + " not found");
		}
		return out;
	}

	@Override
	public Cat create(Cat cat) {
		cat.setCatId(this.nextId);
		this.nextId++;
		this.mockCats.add(cat);
		return cat;
	}

	@Override
	public Cat update(Cat cat) {
		throw new NotYetImplementedException();
	}

	@Override
	public Cat createOrUpdate(Cat cat) {
		throw new NotYetImplementedException();
	}

	@Override
	public void delete(Integer id) {
		throw new NotYetImplementedException();

	}

}
