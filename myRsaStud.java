/*******
 *
 * Program Description:
 * ====================
 * Create a cipher using the generated public key to initialize it.
 * Then we use the Electronic Code Book (ECB) mode and PKCS1Padding.
 * ECB is suitable for encrypting small blocks of random data.
 * For example, like a key.
 *
 * PKCS1Padding is standard for most implementations of RSA
 *
 * Another is to use OAEP (Optimal Asymmetric Encryption Padding)
 * OAEP is an improvement on PKCS#1.
 *
 *******/

 import javax.crypto.*;
 import javax.crypto.spec.*;
 import java.security.*;
 import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
 import sun.misc.*;

 public class myRsaStud
 {
   public static void main (String[] args) throws Exception
   {
	// Print Header
    System.out.println("A Simple Program using RSA to encrypt a single symmetric key using");
    System.out.println("Advanced Encryption Standard (AES).\n");

    // Create an AES key to be encrypted
    System.out.println("Generating a symmetric (AES) key...");
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    Key AESKey = keyGenerator.generateKey();
    System.out.println("Format: "+AESKey.getFormat());

    // Create an RSA key pair
    System.out.println("Generating an RSA key...");
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(1024);
    // Insert your statement here
    KeyPair kp = keyPairGenerator.generateKeyPair();
    Key PublicKey = kp.getPublic();
    Key PrivateKey = kp.getPrivate();
    
    
    System.out.println("Done generating the key.\n");

    // Initialise the RSA cipher with PUBLIC key
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    // Insert your statement here
    cipher.init(Cipher.ENCRYPT_MODE, PublicKey);

    // Get the bytes of the AES key
    byte[] encryptedKey = AESKey.getEncoded();
    System.out.println("AES key:\n" + asHex(encryptedKey) + "\n");

    // Perform the actual encryption on those bytes
    byte[] cipherText = cipher.doFinal(encryptedKey);
    System.out.println("Encrypted key:\n" + asHex(cipherText) + "\n");

    // Re-initialize the RSA cipher with PRIVATE key
    // Insert your statement here
    cipher.init(Cipher.DECRYPT_MODE, PrivateKey);

    // Perform the decryption
    byte[] decryptedKey = cipher.doFinal(cipherText);
    System.out.println("Decrypted key:\n" + asHex(decryptedKey));

    // Create a new key from the decrypted bytes using SecretKeySpec
    SecretKey newAESKey = new SecretKeySpec(decryptedKey, "AES");
    // And your program continues from here
    // ....blah blah

	System.out.println("\nEnd of Program");

  }

  public static String asHex (byte buf[]) {

  //Obtain a StringBuffer object
      StringBuffer strbuf = new StringBuffer(buf.length * 2);
      int i;

      for (i = 0; i < buf.length; i++) {
          if (((int) buf[i] & 0xff) < 0x10)
             strbuf.append("0");
             strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
       }
       // Return result string in Hexadecimal format
       return strbuf.toString();
  }
}