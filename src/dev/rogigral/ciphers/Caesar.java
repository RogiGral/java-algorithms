package dev.rogigral.ciphers;


/**
 * A Java implementation of the Caesar Cipher.
 * This is a type of substitution cipher where each letter in the plaintext
 * is shifted to a fixed number of positions down the alphabet.
 *
 * @author rogiGral
 */
public class Caesar {

    public static String encode(String message, Integer shift) {
        StringBuilder encoded = new StringBuilder();
        shift %= 26;
        final Integer length = message.length();
        for (int i = 0; i < length; i++) {
            char current = message.charAt(i);
            if (isCapitalLatinLetter(current)) {
                current += shift;
                encoded.append((char) (current > 'Z' ? current - 26 : current)); // 26 = number of latin letters
            } else if (isSmallLatinLetter(current)) {
                current += shift;
                encoded.append((char) (current > 'z' ? current - 26 : current)); // 26 = number of latin letters
            } else {
                encoded.append(current);
            }
        }
        return encoded.toString();
    };

    public static String decode(String encryptedMessage, Integer shift) {
        StringBuilder decoded = new StringBuilder();
        shift %= 26;
        final Integer length = encryptedMessage.length();
        for (int i = 0; i < length; i++) {
            char current = encryptedMessage.charAt(i);
            if (isCapitalLatinLetter(current)) {
                current -= shift;
                decoded.append((char) (current < 'A' ? current + 26 : current)); // 26 = number of latin letters
            } else if (isSmallLatinLetter(current)) {
                current -= shift;
                decoded.append((char) (current < 'a' ? current + 26 : current)); // 26 = number of latin letters
            } else {
                decoded.append(current);
            }
        }
        return decoded.toString();
    }

    private static boolean isCapitalLatinLetter(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private static boolean isSmallLatinLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static void main(String[] args) {
        String message = "Ala ma kota a kot ma ale";
        Integer shift = 4;
        String encoded = encode(message,shift);
        System.out.println(encoded);
        String decoded = decode(encoded,shift);
        System.out.println(decoded);

    }
}
