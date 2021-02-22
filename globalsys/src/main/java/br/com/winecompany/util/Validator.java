package br.com.winecompany.util;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.winecompany.entity.PhisicStoresEntity;
import br.com.winecompany.exception.ObjectNotFoundException;

/**
 * Utility class for general validations
 */
@Component
public class Validator {

	/**
 	 * This method validates the structure of the incoming request
	 * If the update parameter comes, the id is tested
	 * If the create parameter comes, storesCode is tested
	 * For all cases, all other parameters are tested
	 * 
	 * @param PhisicStoresEntity entity
	 * @param boolean isCreate
	 * @param boolean isUpdate
	 * @return boolean
	 */
	public boolean isValidRequest(PhisicStoresEntity entity, boolean isCreate, boolean isUpdate) {
		if(isUpdate) {
			if(entity.getId() == null ||entity.getId() == 0) {
				return false;
			}	
		} else if (isCreate) {
			if(entity.getStoresCode() == null || entity.getStoresCode().equals("") || entity.getId() != null || (entity.getId()!= null &&  entity.getId() != 0)) {
				return false;
			}	
		} if(entity.getInitialRange() == null || entity.getFinalRange() == null || entity.getInitialRange() == 0 || entity.getFinalRange() == 0 || entity.getFinalRange() <= entity.getInitialRange()) {
			return false;
		}
		
		return true;
	}


	/**
	 * This method is to validate if the list is Null Or Empty
	 * 
	 * @param List<PhisicStoresEntity> list
	 * @return List<PhisicStoresEntity>
	 * @throws ObjectNotFoundException
	 */
	public List<PhisicStoresEntity> validatesIfTheListIsNullOrEmpty(List<PhisicStoresEntity> list) {
		if(list == null || (list != null && list.isEmpty()))
			throw new ObjectNotFoundException("");
		return list;
	}
	
	
	/**
	 * This method is to validate if the entity exists
	 * 
	 * @param Optional<PhisicStoresEntity> optional
	 * @return PhisicStoresEntity
	 * @throws ObjectNotFoundException
	 */
	public PhisicStoresEntity validateIfExistsTheEntity(Optional<PhisicStoresEntity> optional) {
		if (!optional.isPresent())
			throw new ObjectNotFoundException("");
		return optional.get();
	}
	

}
