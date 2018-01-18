package org.ggolawski.security.utils.crypto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class PasswordHash {
	private static final String PASSWORD = "Pa$$w0rd";
	
	public static void main(String[] args) throws GeneralSecurityException, IOException {
		System.out.println("Password: " + PASSWORD);
		
		System.out.println("SHA-256 hash: " + hashSHA256(PASSWORD));
		System.out.println("PBKDF2 hash : " + hashPBKDF2(PASSWORD));
	}
	
	private static String hashSHA256(String password) throws NoSuchAlgorithmException {
		return Hex.encodeHexString(SHA256.hash(password));
	}
	
	private static String hashPBKDF2(String password) throws GeneralSecurityException, IOException {
		byte[] salt = Utils.randomBytes(32);
		int iterations = 10000;
		int keyLength = 256;
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        SecretKey key = skf.generateSecret(spec);
        return Hex.encodeHexString(salt) + "." + Hex.encodeHexString(key.getEncoded());
	}
}
