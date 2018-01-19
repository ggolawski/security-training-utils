package org.ggolawski.security.utils.crypto;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import org.apache.commons.codec.binary.Hex;

public class AES {
	public static void main(String[] args) throws GeneralSecurityException, IOException {
		String input = "Very secret text";
		System.out.println("Beginning; text to encrypt: " + input);

		// Initialize random and generate key
		System.out.println("Generating random symmetric key...");
		SecretKey key = Utils.generateKey("AES", 128);
		System.out.println("Secret key: " + Hex.encodeHexString(key.getEncoded()));

		// Encrypt
		System.out.println("Encrypting...");
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		GCMParameterSpec parameters = cipher.getParameters().getParameterSpec(GCMParameterSpec.class);
		int tagLength = parameters.getTLen();
		byte[] iv = parameters.getIV();
		byte[] cipherText = cipher.doFinal(input.getBytes());
		System.out.println("Initialization vector: " + Hex.encodeHexString(iv));
		System.out.println("Tag length: " + tagLength);
		System.out.println("Encrypted message: " + Hex.encodeHexString(cipherText));
		
		// Send tag length and initialization vector along with the encrypted message
		// Key must be shared securely

		// Decrypt
		parameters = new GCMParameterSpec(tagLength, iv);
		System.out.println("Decrypting...");
		cipher.init(Cipher.DECRYPT_MODE, key, parameters);

		byte[] plainText = cipher.doFinal(cipherText);
		System.out.println("Decrypted message: " + new String(plainText, Charset.forName("ASCII")));
	}

	
}
