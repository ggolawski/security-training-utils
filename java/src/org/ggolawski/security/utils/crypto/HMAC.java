package org.ggolawski.security.utils.crypto;

import java.security.GeneralSecurityException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Hex;

public class HMAC {
	private static final String ALGORITHM = "HmacSHA256";
	
	public static void main(String[] args) throws GeneralSecurityException {
		String input = "Very secret text";
		System.out.println("Beginning; text to HMAC: " + input);
		
		// Generate random secret key
		System.out.println("Generating secret key...");
		SecretKey key = Utils.generateKey(ALGORITHM, 128);
		System.out.println("Secret key: " + Hex.encodeHexString(key.getEncoded()));
		
		Mac mac = Mac.getInstance(ALGORITHM);
		
		// Generate HMAC
		System.out.println("Generating HMAC...");
		mac.init(key);
		mac.update(input.getBytes());
		byte[] code = mac.doFinal();
		System.out.println("HMAC: " + Hex.encodeHexString(code));
	}
}
