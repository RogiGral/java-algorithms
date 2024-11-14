package dev.rogigral.ciphers;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    private BigInteger modulus;
    private BigInteger privateKey;
    private BigInteger publicKey;

    public RSA(int bits) {
        generateKeys(bits);
    }

    public synchronized String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(publicKey, modulus).toString();
    }

    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    public synchronized String decrypt(String encryptedMessage) {
        return new String((new BigInteger(encryptedMessage)).modPow(privateKey, modulus).toByteArray());
    }

    public synchronized BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    public synchronized void generateKeys(int bits) {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bits / 2, 100, r);
        BigInteger q = new BigInteger(bits / 2, 100, r);
        modulus = p.multiply(q);

        BigInteger m = (p.subtract(BigInteger.valueOf(1L))).multiply(q.subtract(BigInteger.valueOf(1L)));
        publicKey = BigInteger.valueOf(3L);
        while (m.gcd(publicKey).intValue() > 1) {
            publicKey = publicKey.add(BigInteger.valueOf(2L));
        }
        privateKey = publicKey.modInverse(m);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(1024);
        String textToEncrypt = "Ala ma kota";

        String cipherText = rsa.encrypt(textToEncrypt);
        String decryptedText = rsa.decrypt(cipherText);
        System.out.println("textToEncrypt " + textToEncrypt);
        System.out.println("cipherText " + cipherText);
        System.out.println("decryptedText " + decryptedText);
    }


}
