package br.com.winecompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.winecompany.business.PhisicStoresBusiness;
import br.com.winecompany.entity.PhisicStoresEntity;
import br.com.winecompany.repository.PhisicStoresRepository;
import br.com.winecompany.service.interfaces.CrudService;
import br.com.winecompany.util.Validator;

/**
 * System Class of Service
 */
@Service
public class PhisicStoresService implements CrudService<PhisicStoresEntity> {
	
	private static final int NUMBER_OF_PAGES = 5;

	@Autowired
	private PhisicStoresRepository repository;

	@Autowired
	private PhisicStoresBusiness business;

	@Autowired
	private Validator validator;

	/**
	 * Service method to retrieve the complete list
	 * 
	 * @return List<PhisicStoresEntity>
	 * @throws ObjectNotFoundException
	 */
	@Override
	public List<PhisicStoresEntity> findAll() {
		List<PhisicStoresEntity> list = this.repository.findAll();
		this.validator.validatesIfTheListIsNullOrEmpty(list);
		return list;
	}

	/**
	 * Service method to retrieve the complete list in a paginated way
	 * 
	 * @param Optional<Integer> page
	 * @param Integer pageNumber
	 * @return Page<PhisicStoresEntity> 
	 */
	@Override
	public Page<PhisicStoresEntity> findAllPaged(Optional<Integer> page, Integer pageNumber) {
		return this.repository.findAll(PageRequest.of(pageNumber, NUMBER_OF_PAGES));
	}

	/**
	 * Service method to retrieve the stores by Id
	 * 
	 * @param Long id
	 * @return PhisicStoresEntity
	 * @throws ObjectNotFoundException
	 */
	@Override
	public PhisicStoresEntity findById(Long id) {
		Optional<PhisicStoresEntity> optional = this.repository.findById(id);
		PhisicStoresEntity phisicStoresEntity = this.validator.validateIfExistsTheEntity(optional);
		return phisicStoresEntity;
	}

	/**
	 * Service method to retrieve the stores by Zip Code
	 * 
	 * @param PhisicStoresEntity entity
	 * @return PhisicStoresEntity
	 * @throws ObjectNotFoundException
	 */
	@Override
	public PhisicStoresEntity findByZipCode(PhisicStoresEntity entity) {
		List<PhisicStoresEntity> list = this.findAll();
		PhisicStoresEntity phisicStoresEntity = this.business.validatesIfExistsAStoredWithinTheGivenRange(entity, list);
		return phisicStoresEntity;
	}
	
	/**
	 * Service method to create a new stores
	 * 
	 * @param PhisicStoresEntity entity
	 * @return PhisicStoresEntity
	 */
	@Override
	public PhisicStoresEntity create(PhisicStoresEntity entity)
	{
		List<PhisicStoresEntity> list = this.repository.findAll();
		boolean hasConflictRange = this.business.validatesIfTheInformedRangeConflictAnotherInTheDatabase(entity, list);
		if(!hasConflictRange) {
			return this.repository.save(entity);			
		}
		return null;
	}
		
	/**
	 * Service method to update an exist stores
	 * 
	 * @param PhisicStoresEntity entity
	 * @return boolean
	 */
	public boolean update(PhisicStoresEntity entity) {
		PhisicStoresEntity phisicStoresEntity = this.findById(entity.getId());
		List<PhisicStoresEntity> list = this.findAll();
		boolean hasConflictRange = this.business.validatesIfTheInformedRangeConflictAnotherInTheDatabase(entity, list);
		
		if(!hasConflictRange) {
			BeanUtils.copyProperties(entity, phisicStoresEntity, "id");
			this.repository.save(entity);
			return true;
		}
		
		return false;
	}	
	
	/**
	 * Service method to remove an exist stores
	 * 
	 * @param PhisicStoresEntity entity
	 * @return void
	 */
	@Override
	public void remove(PhisicStoresEntity entity) {
		this.repository.delete(entity);
	}

	/**
	 * Service method to remove all stores
	 * 
	 * @return void
	 */
	@Override
	public void removeAll() {
		this.repository.deleteAll();		
	}

}