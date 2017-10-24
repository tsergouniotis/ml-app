package com.tns.ml.rest.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(String dateInput) throws Exception {
		return LocalDate.parse(dateInput, DateTimeFormatter.ISO_DATE);
	}

	@Override
	public String marshal(LocalDate localDate) throws Exception {
		return DateTimeFormatter.ISO_DATE.format(localDate);
	}
}
