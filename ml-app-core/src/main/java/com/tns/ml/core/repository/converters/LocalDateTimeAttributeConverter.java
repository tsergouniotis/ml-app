package com.tns.ml.core.repository.converters;

import static java.time.ZoneId.systemDefault;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.AttributeConverter;

public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Calendar> {

	@Override
	public Calendar convertToDatabaseColumn(LocalDateTime value) {
		return value == null ? null : GregorianCalendar.from(value.atZone(systemDefault()));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Calendar value) {
		return value == null ? null : LocalDateTime.ofInstant(value.toInstant(), systemDefault());
	}
}
