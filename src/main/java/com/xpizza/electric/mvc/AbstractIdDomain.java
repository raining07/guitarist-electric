package com.xpizza.electric.mvc;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @ClassName: AbstractIdDomain 
 * @Description: ID实体基类
 * @author: Xpizza
 * @date: Nov 7, 2017 1:31:16 PM
 */
@MappedSuperclass
public abstract class AbstractIdDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AbstractIdDomain [id=" + id + "]";
	}

}
