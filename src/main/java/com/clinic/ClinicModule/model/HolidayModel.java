package com.clinic.ClinicModule.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tbl_holiday")
public class HolidayModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date holidaydate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifiedDatetime;
	private String createdBy;
	private String modifiedBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getHolidaydate() {
		return holidaydate;
	}

	public void setHolidaydate(Date holidaydate) {
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Date getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Date modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDatetime == null) ? 0 : createdDatetime.hashCode());
		result = prime * result + ((holidaydate == null) ? 0 : holidaydate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result + ((modifiedDatetime == null) ? 0 : modifiedDatetime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HolidayModel other = (HolidayModel) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdDatetime == null) {
			if (other.createdDatetime != null)
				return false;
		} else if (!createdDatetime.equals(other.createdDatetime))
			return false;
		if (holidaydate == null) {
			if (other.holidaydate != null)
				return false;
		} else if (!holidaydate.equals(other.holidaydate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedDatetime == null) {
			if (other.modifiedDatetime != null)
				return false;
		} else if (!modifiedDatetime.equals(other.modifiedDatetime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HolidayModel [id=" + id + ", name=" + name + ", holidaydate=" + holidaydate + ", createdDatetime="
				+ createdDatetime + ", modifiedDatetime=" + modifiedDatetime + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + "]";
	}

}
