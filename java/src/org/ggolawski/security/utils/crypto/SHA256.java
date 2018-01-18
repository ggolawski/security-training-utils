package org.ggolawski.security.utils.crypto;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;

public class SHA256 {
	public static void main(String[] args) throws GeneralSecurityException {
		String text = RandomStringUtils.randomAlphanumeric(10, 100);
		System.out.println("Beginning; text to hash: " + text);
		
		System.out.println("Hashing...");
		byte[] hash = hash(text);
		System.out.println("Hash: " + Hex.encodeHexString(hash));
	}
	
	public static byte[] hash(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes());
		return md.digest();
	}
}
