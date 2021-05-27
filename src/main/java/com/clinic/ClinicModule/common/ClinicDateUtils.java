package com.clinic.ClinicModule.common;

import java.sql.Timestamp;

public class ClinicDateUtils {

	public static Timestamp getCurrentTimeStamp() {
		java.util.Date utilDate = new java.util.Date();
		return new java.sql.Timestamp(utilDate.getTime());
	}
}
