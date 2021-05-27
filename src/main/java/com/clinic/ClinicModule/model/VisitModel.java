package com.clinic.ClinicModule.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tbl_visit")
public class VisitModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date visitdatetime;
	private Integer physicianId;
	private Integer patientId;
	private String reason;
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

	public Date getVisitdatetime() {
		return visitdatetime;
	}

	public void setVisitdatetime(Date visitdatetime) {
		this.visitdatetime = visitdatetime;
	}

	public Integer getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(Integer physicianId) {
		this.physicianId = physicianId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result + ((modifiedDatetime == null) ? 0 : modifiedDatetime.hashCode());
		result = prime * result + ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result + ((physicianId == null) ? 0 : physicianId.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((visitdatetime == null) ? 0 : visitdatetime.hashCode());
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
		VisitModel other = (VisitModel) obj;
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
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		if (physicianId == null) {
			if (other.physicianId != null)
				return false;
		} else if (!physicianId.equals(other.physicianId))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (visitdatetime == null) {
			if (other.visitdatetime != null)
				return false;
		} else if (!visitdatetime.equals(other.visitdatetime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VisitModel [id=" + id + ", visitdatetime=" + visitdatetime + ", physicianId=" + physicianId
				+ ", patientId=" + patientId + ", reason=" + reason + ", createdDatetime=" + createdDatetime
				+ ", modifiedDatetime=" + modifiedDatetime + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ "]";
	}

}
