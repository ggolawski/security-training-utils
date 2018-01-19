package org.ggolawski.security.utils.crypto;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;

public class RSAEncryption {
	public static void main(String[] args) throws GeneralSecurityException {
		String text = "Very secret information";
		System.out.println("Beginning; text to encrypt: " + text);
		
		// Generate public and private keys
		System.out.println("Generating key pair...");
	    KeyPair keyPair = Utils.generateKeyPair("RSA", 2048);
	    PublicKey publicKey = keyPair.getPublic();
	    PrivateKey privateKey = keyPair.getPrivate();
	    System.out.println("Public key: " + Hex.encodeHexString(publicKey.getEncoded()));
	    System.out.println("Private key: " + Hex.encodeHexString(privateKey.getEncoded()));
	    
	    Cipher cipher = Cipher.getInstance("RSA");
	    
	    // Encrypt the message
	    System.out.println("Encrypting...");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        byte[] cipherText = cipher.doFinal(text.getBytes());
        System.out.println("Encrypted message: " + Hex.encodeHexString(cipherText));
        
        // Decrypt the message
        System.out.println("Decrypting...");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("Decrypted message: " + new String(plainText));
	}

	
}
