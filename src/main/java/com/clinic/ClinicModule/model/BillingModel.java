package com.clinic.ClinicModule.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tbl_billing")
public class BillingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer physicianId;
	private Integer patientId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Double billAmount;
	private Date billedDatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public Date getBilledDatetime() {
		return billedDatetime;
	}

	public void setBilledDatetime(Date billedDatetime) {
		this.billedDatetime = billedDatetime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billAmount == null) ? 0 : billAmount.hashCode());
		result = prime * result + ((billedDatetime == null) ? 0 : billedDatetime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((patientId == null) ? 0 : patientId.hashCode());
		result = prime * result + ((physicianId == null) ? 0 : physicianId.hashCode());
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
		BillingModel other = (BillingModel) obj;
		if (billAmount == null) {
			if (other.billAmount != null)
				return false;
		} else if (!billAmount.equals(other.billAmount))
			return false;
		if (billedDatetime == null) {
			if (other.billedDatetime != null)
				return false;
		} else if (!billedDatetime.equals(other.billedDatetime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return true;
	}

	@Override
	public String toString() {
		return "BillingModel [id=" + id + ", physicianId=" + physicianId + ", patientId=" + patientId + ", billAmount="
				+ billAmount + ", billedDatetime=" + billedDatetime + "]";
	}

}
