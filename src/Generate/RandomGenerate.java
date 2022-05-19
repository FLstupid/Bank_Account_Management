package Generate;

import java.util.Random;

public class RandomGenerate {

	public long RandomGenerateLongNumber() {

		Random random = new Random();
		char[] digits = new char[12];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < 12; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return Long.parseLong(new String(digits));

	}

	public String RandomGenerateIdTransaction() {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(20);
		for (int i = 0; i < 20; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

	public String RandomGeneratePassword() {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

}
