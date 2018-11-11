import java.security.*;
import javax.crypto.*;

public class myAesEcb
{
  public static void main (String[] args) throws Exception
  {
    int keysize =  128;  // keysize of the AES
	String text = "Hello123Hello123Hello123";

    System.out.println("Original Text=" + text + "\n");
    System.out.println("Generating a " + keysize + "-bit AES key...");

    // Create a 128-bit AES key
    KeyGenerator kg = KeyGenerator.getInstance("AES");

    // need to initialize with the keysize
    kg.init(keysize);
    Key mykey = kg.generateKey();
    System.out.println("The key is generated.");
      
    // Write your code here to print out
    // the AES key generated as a string
    // Consider using asHex() method



    // Create a cipher object and use the generated key to initialize it
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, mykey);

    byte[] plaintext = text.getBytes("UTF8");

    // Print out the bytes of the plaintext
    System.out.println("\nPlaintext: ");
    for (int i=0;i<plaintext.length;i++) {
		System.out.print(plaintext[i]+" ");
	}

    // Now encrypt the text
    byte[] ciphertext = cipher.doFinal(plaintext);

	// Print the ciphertext
    System.out.println("\n\nCiphertext: ");
    for (int i=0;i<ciphertext.length;i++) {
		System.out.print(ciphertext[i]+" ");
	}

    System.out.println("\n\nCiphertext (Hex): ");
    System.out.println(asHex(ciphertext));

    // Decryption

    // Change to decrypt mode
    cipher.init(Cipher.DECRYPT_MODE  , mykey  );

    // Now decrypt the text
    byte[] decryptedText = cipher.doFinal(ciphertext);

    // Print the message
    System.out.println("\nPlaintext=" + new String(decryptedText ,"UTF8" ));
  }


  public static String asHex (byte buf[]) {
         StringBuffer strbuf = new StringBuffer(buf.length * 2);
         int i;

           for (i = 0; i < buf.length; i++) {
              if (((int) buf[i] & 0xff) < 0x10)
       	        strbuf.append("0");

    	        strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
    	      }

           return strbuf.toString();
  }


}
