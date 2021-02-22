package br.com.winecompany.business;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.winecompany.entity.PhisicStoresEntity;
import br.com.winecompany.exception.ObjectNotFoundException;

/**
 * Business Class to carry out the business rules of the project
 */
@Component
public class PhisicStoresBusiness {
	
	
	/**
	 * Validates if there is one stored within the given range
	 * 
	 * @param PhisicStoresEntity entity
	 * @param List<PhisicStoresEntity> list
	 * @return PhisicStoresEntity
	 */
	public PhisicStoresEntity validatesIfExistsAStoredWithinTheGivenRange(PhisicStoresEntity entity, List<PhisicStoresEntity> list) {
		if(list != null && !list.isEmpty()) {
			for(PhisicStoresEntity phisicStoresEntity : list) {
				if ((phisicStoresEntity.getInitialRange() <= entity.getInitialRange() && entity.getInitialRange() <= phisicStoresEntity.getFinalRange() ) && 
					(phisicStoresEntity.getInitialRange() <= entity.getFinalRange()  && entity.getFinalRange() <= phisicStoresEntity.getFinalRange()) ) {	
					return phisicStoresEntity;
				}	
			}
		}	
		throw new ObjectNotFoundException("");
	}
	

	/**
	 * Validates whether the reported range already exists in the database 
	 * 
	 * @param PhisicStoresEntity entity
	 * @param List<PhisicStoresEntity> list
	 * @return boolean
	 */
	public boolean validatesIfTheInformedRangeConflictAnotherInTheDatabase(PhisicStoresEntity entity, List<PhisicStoresEntity> list) 
	{
		
		if(list != null && !list.isEmpty()) {
		
			Long entityInitialRange = entity.getInitialRange();
			Long entityFinalRange =  entity.getFinalRange();
			
			for(PhisicStoresEntity phisicStoresEntity : list) {

				Long faixaInicialPhisicStores = phisicStoresEntity.getInitialRange();
				Long faixaFinalPhisicStores = phisicStoresEntity.getFinalRange();
				
				if( (faixaInicialPhisicStores >= entityInitialRange && faixaInicialPhisicStores <= entityFinalRange) || 
					(faixaInicialPhisicStores <= entityInitialRange && faixaFinalPhisicStores >= entityFinalRange) || 
					(faixaFinalPhisicStores >= entityInitialRange && faixaFinalPhisicStores <= entityFinalRange) 
				) {
					return true;
				}
			}
		}
		
		return false;
	}

}