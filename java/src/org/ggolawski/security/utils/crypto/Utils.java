package org.ggolawski.security.utils.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Utils {
	public static byte[] randomBytes(int length) throws NoSuchAlgorithmException {
		byte[] result = new byte[length];
		SecureRandom random = SecureRandom.getInstanceStrong();
		random.nextBytes(result);
		return result;
	}
}
