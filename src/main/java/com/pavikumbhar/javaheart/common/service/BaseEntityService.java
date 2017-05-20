/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.pavikumbhar.javaheart.common.service;

import com.pavikumbhar.javaheart.common.dto.BaseEntityDto;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

 import com.pavikumbhar.javaheart.common.entity.BaseEntity;
 import com.pavikumbhar.javaheart.common.dao.BaseEntityRepository;


/**
 *
 * @author pavikumbhar
 */  
//@Service
public abstract class BaseEntityService<E extends BaseEntity, D extends BaseEntityDto> {

	@Autowired
	BaseEntityRepository<E> repository;
	
	public List<E> findAll() {
		return getRepository().findAll();
	}

	public E findOne(Long id) {
		return getRepository().findOne(id);
	}

	public E save(E entity) {
		return getRepository().save(entity);
	}

	public boolean exists(Long id) {
		return getRepository().exists(id);
	}

	public void delete(Long id) {
		getRepository().delete(id);
	}

	public BaseEntityRepository<E> getRepository() {
		return repository;
	}
	
	public E convert(D dto, E entity) {
		entity.setVersion(dto.getVersion());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setLastModifiedDate(dto.getLastModifiedDate());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setLastModifiedBy(dto.getLastModifiedBy());
		entity.setActive(dto.isActive());
		return entity;
	}

	public D convert(E entity, D dto) {
		dto.setVersion(entity.getVersion());
		try {
			if(entity.getCreatedDate()!=null)
			dto.setCreatedDate(DateUtils.parseDateStrictly(DateFormatUtils.format(entity.getCreatedDate(), "dd/MM/yyyy"), "dd/MM/yyyy"));
			if(entity.getLastModifiedDate()!=null)
			dto.setLastModifiedDate(DateUtils.parseDateStrictly(DateFormatUtils.format(entity.getLastModifiedDate(), "dd/MM/yyyy"), "dd/MM/yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}