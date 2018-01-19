package org.ggolawski.security.utils.crypto;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import org.apache.commons.codec.binary.Hex;

public class DigitalSigning {
	public static void main(String[] args) throws GeneralSecurityException {
		String text = "Very secret information";
		System.out.println("Beginning; message to sign: " + text);
		
		// Generate public and private keys
		System.out.println("Generating key pair...");
	    KeyPair keyPair = Utils.generateKeyPair("EC", 256);
	    PublicKey publicKey = keyPair.getPublic();
	    PrivateKey privateKey = keyPair.getPrivate();
	    System.out.println("Public key: " + Hex.encodeHexString(publicKey.getEncoded()));
	    System.out.println("Private key: " + Hex.encodeHexString(privateKey.getEncoded()));
	    
	    Signature signature = Signature.getInstance("SHA256withECDSA");
	    
	    // Sign the message
	    System.out.println("Signing...");
	    signature.initSign(privateKey);
	    signature.update(text.getBytes());
	    byte[] messageSignature = signature.sign();
	    System.out.println("Signature: " + Hex.encodeHexString(messageSignature));
	    
	    // Alter the signature
//	    messageSignature[10] = 33;
	    
	    // Verify the signature
	    System.out.println("Verifying...");
	    signature.initVerify(publicKey);
	    signature.update(text.getBytes());
	    boolean valid = signature.verify(messageSignature);
	    System.out.println("Signature valid? " + valid);
	}
}
