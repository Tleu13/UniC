package com.tleu;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class SHA256Helper {

    public static String generateHash(String data){

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(data.getBytes("UTF-8"));

            StringBuffer hexadecimalString = new StringBuffer();

            for (int i=0;i<hash.length;i++){
                String hexadecimal = Integer.toHexString(0xff & hash[i]);
                if (hexadecimal.length() == 1) {
                    hexadecimalString.append('0');
                }
                hexadecimalString.append(hexadecimal);
            }

            return hexadecimalString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static KeyPair ellipticCurveCrypto() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA"
                    , "BC");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec params = new ECGenParameterSpec("secp192k1");
            keyPairGenerator.initialize(params, secureRandom);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException | NoSuchProviderException
                | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] applyECDSASignature(PrivateKey privateKey, String input) {
        Signature signature;
        byte[] output = new byte[0];
        try {
            signature = Signature.getInstance("ECDSA", "BC");
            signature.initSign(privateKey);
            byte[] strByte = input.getBytes();
            signature.update(strByte);
            byte[] realSignature = signature.sign();
            output = realSignature;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public static boolean verifyECDSASignature(PublicKey publicKey
            , String data, byte[] signature) {
        try {
            Signature ecdsaSignature = Signature.getInstance("ECDSA"
                    , "BC");
            ecdsaSignature.initVerify(publicKey);
            ecdsaSignature.update(data.getBytes());
            return ecdsaSignature.verify(signature);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
