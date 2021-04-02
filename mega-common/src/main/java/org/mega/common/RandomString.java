package org.mega.common;

import java.util.Random;

public class RandomString {

	private static final char[] symbols = "123456789ABCDEFGHKLMNPQRSTUVWXYZabcdefghkmnpqrstuvwxyz".toCharArray();
	private final Random random = new Random();

	private final char[] buf;

	public RandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		buf = new char[length];
	}

	public String nextString() {
		for (int i = 0; i < buf.length; i++) {
			buf[i] = symbols[random.nextInt(symbols.length)];
		}
		return new String(buf);
	}

}
