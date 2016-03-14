package com.amoraesdev.auth.oauth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Password Encoder use to compare the typed password with the one retrieved from the database
 * @author Alessandro Moraes alessandro(at)amoraesdev.com
 */
public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if(md5(rawPassword.toString()).equals(encodedPassword)){
			return true;
		}
		return false;
	}

	private String md5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(input.getBytes(), 0, input.length());
        md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
