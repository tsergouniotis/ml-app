package com.tns.ml.core.converters.test;

import org.junit.Test;

import com.tns.ml.core.repository.converters.JPACryptoConverter;

public class JPAConvetersTestCase {

	@Test
	public void testJPACryptoConverter() {
		JPACryptoConverter converter = new JPACryptoConverter();
		String string = converter.convertToDatabaseColumn("D3us4all!");
		System.out.println(string);
		string = converter.convertToEntityAttribute(string);
		System.out.println(string);
	}

}
