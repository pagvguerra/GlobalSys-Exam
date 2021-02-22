package br.com.winecompany.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.winecompany.controller.interfaces.CrudController;
import br.com.winecompany.entity.PhisicStoresEntity;
import br.com.winecompany.exception.BusinessException;
import br.com.winecompany.exception.ObjectNotFoundException;
import br.com.winecompany.response.Response;
import br.com.winecompany.service.PhisicStoresService;
import br.com.winecompany.util.Mappers;
import br.com.winecompany.util.Messages;
import br.com.winecompany.util.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller Class for Rest Requests
 * This class is all mapped with SWAGGER
 * for automatic generation of documentation of
 * REST APIs
 * */
@RestController
@RequestMapping(Mappers.MAPPER_URL_PHISIC_STORES)
@Api(value = Messages.SWAGGER_MSG_CONTROLER)
@CrossOrigin(origins = "*")
public class PhisicStoresController implements CrudController<PhisicStoresEntity> {
	
	@Autowired
	private PhisicStoresService service;

	@Autowired
	private Validator validator;

	/**
 	 * This method retrieves the complete listing of stores
 	 * 
	 * @return ResponseEntity<List<PhisicStoresEntity>>
	 * @throws ObjectNotFoundException
	 */	
	@Override
	@ApiOperation(value = Messages.SWAGGER_MSG_FIND_ALL)
	public ResponseEntity<List<PhisicStoresEntity>> findAll() {
		try {
			return ResponseEntity.ok(this.service.findAll() );
		} catch (ObjectNotFoundException onfe) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}	
	}

	/**
 	 * This method retrieves the complete listing of stores in a paginated manner
 	 * 
 	 * @param Optional<Integer> page
	 * @return ResponseEntity<Page<PhisicStoresEntity>>
	 * @throws ObjectNotFoundException
	 */	
	@Override
	@ApiOperation(value = Messages.SWAGGER_MSG_FIND_ALL_PAGED)
	public ResponseEntity<Page<PhisicStoresEntity>> findAllPaged(Optional<Integer> page) {
		try {
			return ResponseEntity.ok(this.service.findAllPaged(page, (page.isPresent() ? page.get() : 0)) );
		} catch (ObjectNotFoundException onfe) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}	
	}

	/**
 	 * This method retrieves the store listing by store id
 	 * 
 	 * @param Long id
	 * @return ResponseEntity<PhisicStoresEntity>
	 * @throws ObjectNotFoundException
	 */	
	@Override
	@ApiOperation(value = Messages.SWAGGER_MSG_FIND_BY_ID)
	public ResponseEntity<PhisicStoresEntity> findById(Long id) {
		try {
			if(id == null || id == 0)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
			return ResponseEntity.ok(this.service.findById(id));
		} catch (ObjectNotFoundException onfe) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	/**
 	 * This method retrieves the store listing for a given range
 	 * 
 	 * @param PhisicStoresEntity entity
	 * @return ResponseEntity<PhisicStoresEntity>
	 * @throws ObjectNotFoundException
	 */
	@Override
	@ApiOperation(value = Messages.SWAGGER_MSG_FIND_PHISIC_STORES_BY_ZIP_CODE)
	public ResponseEntity<PhisicStoresEntity> findByZipCode(PhisicStoresEntity entity) {
		try {
			if(!validator.isValidRequest(entity, false, false))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			return ResponseEntity.ok(this.service.findByZipCode(entity));
		} catch (ObjectNotFoundException onfe) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	/**
 	 *  This method creates a new store ensuring that there is no overlap of range
 	 * 
 	 * @param PhisicStoresEntity entity
	 * @return ResponseEntity<Response<PhisicStoresEntity>>
	 */
	@Override
	@ApiOperation(value = Messages.SWAGGER_MSG_CREATE)
	public ResponseEntity<Response<PhisicStoresEntity>> create(PhisicStoresEntity entity) {
		try {
			if(!validator.isValidRequest(entity, true, false))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	
			if(this.service.create(entity) == null)
				return ResponseEntity.badRequest().body(new Response<PhisicStoresEntity>(Messages.ERROR_RANGE_NEEDS_TO_BE_DIFFERENT));
	
			return ResponseEntity.ok(new Response<PhisicStoresEntity>(Messages.ZIP_CODE_CREATED));
		} catch (BusinessException be) {
			return ResponseEntity.badRequest().body(new Response<PhisicStoresEntity>(Messages.ERROR_RANGE_NEEDS_TO_BE_CORRECT));			
		}
	}
	
	/**
 	 * This method updates an existing store ensuring that there is no overlapping range
 	 * 
 	 * @param PhisicStoresEntity entity
	 * @return ResponseEntity<Response<PhisicStoresEntity>>
	 */
	@Override
	@ApiOperation(value = Messages.SWAGGER_MSG_UPDATE)
	public ResponseEntity<Response<PhisicStoresEntity>> udpate(PhisicStoresEntity entity) {
		try {
			if(!validator.isValidRequest(entity, false, true))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			if(!this.service.update(entity))
				return ResponseEntity.badRequest().body(new Response<PhisicStoresEntity>(Messages.ERROR_RANGE_NEEDS_TO_BE_DIFFERENT));
			else
				return ResponseEntity.ok(new Response<PhisicStoresEntity>(Messages.ZIP_CODE_UPDATED));

		} catch (ObjectNotFoundException onfe) {
			return ResponseEntity.badRequest().body(new Response<PhisicStoresEntity>(Messages.ERROR_ENTITY_NOT_FOUND));			
		} catch (BusinessException be) {
			return ResponseEntity.badRequest().body(new Response<PhisicStoresEntity>(Messages.ERROR_RANGE_NEEDS_TO_BE_CORRECT));			
		}
	}

	/**
 	 * This method removes a specific store by id
 	 * 
 	 * @param PhisicStoresEntity entity
	 * @return ResponseEntity<Object>
	 */
	@Override
	@ApiOperation(value = Messages.SWAGGER_MSG_REMOVE)
	public ResponseEntity<Object> remove(PhisicStoresEntity entity) {
		if(entity.getId() == null || entity.getId() == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		this.service.remove(entity);
		return ResponseEntity.ok().build();
	}

	/**
 	 * This method removes all stores 
 	 * 
	 * @return ResponseEntity<Object>
	 */
	@Override
	@ApiOperation(value = Messages.SWAGGER_MSG_REMOVE_ALL)
	public ResponseEntity<Object> removeAll() {
		this.service.removeAll();
		return ResponseEntity.ok().build();
	}	
	
}