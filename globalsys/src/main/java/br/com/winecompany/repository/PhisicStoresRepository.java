package br.com.winecompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.winecompany.entity.PhisicStoresEntity;

@Repository
public interface PhisicStoresRepository extends JpaRepository<PhisicStoresEntity, Long>, PagingAndSortingRepository<PhisicStoresEntity, Long>, JpaSpecificationExecutor<PhisicStoresEntity> {    

}
