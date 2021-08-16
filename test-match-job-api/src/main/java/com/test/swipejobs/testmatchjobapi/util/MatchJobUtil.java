package com.test.swipejobs.testmatchjobapi.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MatchJobUtil {
	
	/**
	 * Returns distance between to co-ordinates in the unit passed, defaults to Miles.
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @param unit
	 * @return double 
	 */
	public static double getDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit.startsWith("K") || unit.startsWith("k")) {
			dist = dist * 1.609344;
		} else if (unit.startsWith("N") || unit.startsWith("n")) {
			dist = dist * 0.8684;
		}
		return dist;
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
	
	/**
	 * return the day in text for the date passed.
	 * @param startDate
	 * @return
	 */
	public static String getDayOfWeek(String startDate) {
		LocalDate localDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE_TIME);
		java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		return dayOfWeek.toString();
	}

}
