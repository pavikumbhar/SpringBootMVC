/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.pavikumbhar.javaheart.common.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author pavikumbhar
 */
@MappedSuperclass
public abstract class BaseEntity {

	@Version
	private int version = 0;

	private String createdBy;

	private String lastModifiedBy;

	private Date createdDate;

	private Date lastModifiedDate;

	private boolean active;

	public int getVersion() {
		return version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@PrePersist
	public void onPrePersist() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
//			setCreatedBy(currentPrincipalName);
//			setLastModifiedBy(currentPrincipalName);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		setCreatedDate(Calendar.getInstance().getTime());
//		setLastModifiedDate(Calendar.getInstance().getTime());
	}

	@PreUpdate
	public void onPreUpdate() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
//			setLastModifiedBy(currentPrincipalName);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		setLastModifiedDate(Calendar.getInstance().getTime());
	}

}