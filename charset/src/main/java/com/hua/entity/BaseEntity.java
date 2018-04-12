/**
 * 描述: 
 * BaseEntity.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * BaseEntity
 */
public class BaseEntity implements Serializable {
	
	 /* long */
	private static final long serialVersionUID = 1L;
	
	/* apache commons log */
	protected Log log = LogFactory.getLog(this.getClass().getName());
	
	/* 对象唯一id */
	private Long oid;

	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @return
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		return result;
	}

	/**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (oid == null)
		{
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		return true;
	}
	
}
