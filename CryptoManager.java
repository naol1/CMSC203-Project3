/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple substitution cipher where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager {
    
    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

    /**
     * This method determines if a string is within the allowable bounds of ASCII codes 
     * according to the LOWER_RANGE and UPPER_RANGE characters
     * @param plainText a string to be encrypted, if it is within the allowable bounds
     * @return true if all characters are within the allowable bounds, false if any character is outside
     */
    public static boolean isStringInBounds(String plainText) {
        for (char ch : plainText.toCharArray()) {
            if (ch < LOWER_RANGE || ch > UPPER_RANGE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
     * and each character in plainText is replaced by the character "offset" away from it 
     * @param plainText an uppercase string to be encrypted.
     * @param key an integer that specifies the offset of each character
     * @return the encrypted string
     */
    public static String caesarEncryption(String plainText, int key) {
        if (!isStringInBounds(plainText)) {
            return "The selected string is not in bounds, Try again.";
        }

        StringBuilder encryptedText = new StringBuilder();

        for (char ch : plainText.toCharArray()) {
            if (ch >= LOWER_RANGE && ch <= UPPER_RANGE) {
                int offset = (ch - LOWER_RANGE + key) % RANGE;
                if (offset < 0) {
                    offset += RANGE;
                }
                encryptedText.append((char) (LOWER_RANGE + offset));
            } else {
                encryptedText.append(ch);
            }
        }

        return encryptedText.toString();
    }

    /**
     * Decrypts a string according to the Caesar Cipher. The integer key specifies an offset
     * and each character in encryptedText is replaced by the character "offset" away from it 
     * @param encryptedText an uppercase string to be decrypted.
     * @param key an integer that specifies the offset of each character
     * @return the decrypted string
     */
    public static String caesarDecryption(String encryptedText, int key) {
        if (!isStringInBounds(encryptedText)) {
            return "The selected string is not in bounds, Try again.";
        }

        StringBuilder decryptedText = new StringBuilder();

        for (char ch : encryptedText.toCharArray()) {
            if (ch >= LOWER_RANGE && ch <= UPPER_RANGE) {
                int offset = (ch - LOWER_RANGE - key + RANGE) % RANGE;
                if (offset < 0) {
                    offset += RANGE;
                }
                decryptedText.append((char) (LOWER_RANGE + offset));
            } else {
                decryptedText.append(ch);
            }
        }

        return decryptedText.toString();
    }




    
    /**
     * Encrypts a string according to the Bellaso Cipher.  Each character in plainText is offset 
     * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
     * to correspond to the length of plainText
     * @param plainText an uppercase string to be encrypted.
     * @param bellasoStr an uppercase string that specifies the offsets, character by character.
     * @return the encrypted string
     */
    public static String bellasoEncryption(String plainText, String bellasoStr) {
        StringBuilder encryptedText = new StringBuilder();

        // Extend the key word to the length of the plain text
        StringBuilder extendedKey = new StringBuilder();
        int keyLength = bellasoStr.length();
        for (int i = 0; i < plainText.length(); i++) {
            extendedKey.append(bellasoStr.charAt(i % keyLength));
        }

        // Encrypt each character in the plain text
        for (int i = 0; i < plainText.length(); i++) {
            char plainChar = plainText.charAt(i);
            char keyChar = extendedKey.charAt(i);

            int offset = (plainChar + keyChar - LOWER_RANGE) % RANGE;
            if (offset < 0) {
                offset += RANGE; // Wrap around if the offset is negative
            }

            encryptedText.append((char) (LOWER_RANGE + offset));
        }

        return encryptedText.toString();
    }

    public static String bellasoDecryption(String encryptedText, String bellasoStr) {
        StringBuilder decryptedText = new StringBuilder();

        // Extend the key word to the length of the encrypted text
        StringBuilder extendedKey = new StringBuilder();
        int keyLength = bellasoStr.length();
        for (int i = 0; i < encryptedText.length(); i++) {
            extendedKey.append(bellasoStr.charAt(i % keyLength));
        }

        // Decrypt each character in the encrypted text
        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedChar = encryptedText.charAt(i);
            char keyChar = extendedKey.charAt(i);

            int offset = (encryptedChar - keyChar - LOWER_RANGE) % RANGE;
            if (offset < 0) {
                offset += RANGE; // Wrap around if the offset is negative
            }

            decryptedText.append((char) (LOWER_RANGE + offset));
        }

        return decryptedText.toString();
    }

    // Other methods for encryption and decryption can be added here.

 
}
