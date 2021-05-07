package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint;

    public static BigInteger hashOf(String entity) {

        // Task: Hash a given string using MD5 and return the result as a BigInteger.

        // we use MD5 with 128 bits digest

        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");
            md.update(entity.getBytes());
            byte[] digest = md.digest();

            // convert the hash into hex format
            String hex = toHex(digest);

            // convert the hex into BigInteger
            hashint = new BigInteger(hex, 16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        // return the BigInteger

        return hashint;
    }

    public static BigInteger addressSize() {

        // Task: compute the address size of MD5

        MessageDigest md;
        int digestLength= 0;
        try {
            md = MessageDigest.getInstance("MD5");
            digestLength = md.getDigestLength();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // get the digest length

        // compute the number of bits = digest length * 8
        int numberOfBits = digestLength * 8;
        // compute the address size = 2 ^ number of bits
        BigInteger two = new BigInteger("2");
        BigInteger addressSize = two.pow(numberOfBits);

        // return the address size

        return addressSize;
    }

    public static int bitSize() {

        int digestlen = 0;

        // find the digest length
        try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			digestlen = md.getDigestLength();

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
        
        return digestlen * 8;
    }

    public static String toHex(byte[] digest) {
        StringBuilder strbuilder = new StringBuilder();
        for (byte b : digest) {
            strbuilder.append(String.format("%02x", b & 0xff));
        }
        return strbuilder.toString();
    }

}
