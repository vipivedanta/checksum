
public static String generateChecksum(String requestId, String validationString) 
throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException 
{
    IvParameterSpec iv = new IvParameterSpec(new byte[16]); /*iv creation*/
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); /*AES cypher instance*/

    MessageDigest mDigest = MessageDigest.getInstance("SHA-512"); 
    byte[] digestSeed = mDigest.digest(requestId.getBytes());
    byte[] seedEncArray = Arrays.copyOf(digestSeed, 32);
    SecretKeySpec skspec = new SecretKeySpec(seedEncArray, "AES"); /* secret key */
    
    cipher.init(Cipher.ENCRYPT_MODE, skspec, iv);

    byte[] finalByteArray = cipher.doFinal(validationString.getBytes());
    String finalValue = new String(Base64.encodeBase64(finalByteArray), "utf-8");
    return finalValue;
}