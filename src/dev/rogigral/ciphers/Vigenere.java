package dev.rogigral.ciphers;


/**
 * A Java implementation of the Vigenere Cipher.
 * This is a type of substitution cipher where each letter in the plaintext
 * is shifted to a fixed number of positions down the alphabet.
 *
 * @author rogiGral
 */
public class Vigenere {

    public static String encrypt(final String message, final String key) {
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty.");
        }

        StringBuilder result = new StringBuilder();
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append((char) ((c + key.toUpperCase().charAt(j) - 2 * 'A') % 26 + 'A'));
                } else {
                    result.append((char) ((c + key.toLowerCase().charAt(j) - 2 * 'a') % 26 + 'a'));
                }
                j = ++j % key.length();
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(final String message, final String key) {
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty.");
        }

        StringBuilder result = new StringBuilder();
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append((char) ('Z' - (25 - (c - key.toUpperCase().charAt(j))) % 26));
                } else {
                    result.append((char) ('z' - (25 - (c - key.toLowerCase().charAt(j))) % 26));
                }
                j = ++j % key.length();
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String message = "Igor has a cat and a cat has Igor";
        String key = "cat";
        String encoded = encrypt(message,key);
        System.out.println(encoded);
        String decoded = decrypt(encoded,key);
        System.out.println(decoded);

    }
}
