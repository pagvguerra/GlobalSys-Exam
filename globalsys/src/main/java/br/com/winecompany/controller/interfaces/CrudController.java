package br.com.winecompany.controller.interfaces;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.winecompany.response.Response;
import br.com.winecompany.util.Mappers;

public interface CrudController<T> {
	
	@PostMapping(Mappers.MAPPER_URL_PHISIC_STORES_CREATE)
	public ResponseEntity<Response<T>> create(@Valid @NotEmpty @RequestBody T entity);
	
	@GetMapping(Mappers.MAPPER_URL_PHISIC_STORES_FIND_ALL_PAGED)
	public ResponseEntity<Page<T>> findAllPaged(@PathVariable Optional<Integer> page);
	
	@GetMapping(Mappers.MAPPER_URL_PHISIC_STORES_FIND_ALL)
	public ResponseEntity<List<T>> findAll();

	@GetMapping(Mappers.MAPPER_URL_PHISIC_STORES_FIND_BY_ID)
	public ResponseEntity<T> findById(@Valid @NotEmpty @PathVariable Long id);

	@GetMapping(Mappers.MAPPER_URL_PHISIC_STORES_FIND_BY_ZIP_CODE)	
	public ResponseEntity<T> findByZipCode(@Valid @NotEmpty @RequestBody T entity);
	
	@PutMapping(Mappers.MAPPER_URL_PHISIC_STORES_UPDATE)
	public ResponseEntity<Response<T>> udpate(@Valid @NotEmpty @RequestBody T entity);

	@PostMapping(Mappers.MAPPER_URL_PHISIC_STORES_REMOVE)
	public ResponseEntity<Object> remove(@Valid @NotEmpty @RequestBody T entity);

	@PostMapping(Mappers.MAPPER_URL_PHISIC_STORES_REMOVE_ALL)
	public ResponseEntity<Object> removeAll();

}