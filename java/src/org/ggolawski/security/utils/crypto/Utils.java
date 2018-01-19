package org.ggolawski.security.utils.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Utils {
	public static byte[] randomBytes(int length) throws NoSuchAlgorithmException {
		byte[] result = new byte[length];
		SecureRandom random = SecureRandom.getInstanceStrong();
		random.nextBytes(result);
		return result;
	}
	
	public static KeyPair generateKeyPair(String algorithm, int keyLength) throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
	    keyPairGenerator.initialize(keyLength);
	    KeyPair keyPair = keyPairGenerator.genKeyPair();
		return keyPair;
	}
	
	public static SecretKey generateKey(String algorithm, int keyLength) throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
		keyGen.init(keyLength);
		return keyGen.generateKey();
	}
}
