package br.com.winecompany.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface CrudService<T> {
	
	T create(T entity);

	List<T> findAll();
	
	Page<T> findAllPaged(Optional<Integer> page, Integer pageNumber);
	
	T findById(Long id);
	
	T findByZipCode(T entity);

	boolean update(T entity);
	
	void remove(T entity);

	void removeAll();
}
