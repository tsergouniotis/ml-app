package com.tns.ml.core.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public class JwtKeyGenerator implements KeyGenerator {

	@Override
	public Key generateKey() {
		String keyString = "simplekey";
		return new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
	}

}
