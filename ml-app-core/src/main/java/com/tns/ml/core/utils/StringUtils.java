package com.tns.ml.core.utils;

import java.util.Objects;

public final class StringUtils {

	private StringUtils() {

	}

	public static boolean isEmpty(String value) {
		return Objects.isNull(value) || value.length() < 1;
	}

	public static boolean hasText(String value) {
		return !isEmpty(value);
	}

}
