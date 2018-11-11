import java.security.*;
import javax.crypto.*;

public class myDesEcb2
{
  public static void main (String[] args) throws Exception
  {

	String text = "Hello123Hello123Hello123";

    System.out.println("Original Text=" + text + "\n");
    System.out.println("Generating a 56-bit DES key...");

    // Create a 56-bit DES key
    KeyGenerator kg = KeyGenerator.getInstance("DES");

    // need to initialize with the keysize
    kg.init(56);
    Key mykey = kg.generateKey();

    System.out.println("The key is generated.");

    // Create a cipher object and use the generated key to initialize it
    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, mykey);

    byte[] plaintext = text.getBytes("UTF8");

    // Print out the bytes of the plaintext
    System.out.println("\nPlaintext: ");
    for (int i=0;i<plaintext.length;i++) {

		// call the function to check with blocks makekup of multiple of eight bytes
		// if true, insert a "carriage return" (or commonly known as ENTER)
		if (chkEight(i)) {
			System.out.print("\n");
		}
		System.out.print(plaintext[i]+" ");
	}

    // Now encrypt the text
    byte[] ciphertext = cipher.doFinal(plaintext);

    // Print the ciphertext
    System.out.println("\n\nCiphertext: ");
    for (int i=0;i<ciphertext.length;i++) {

		// call the function to check with blocks makekup of multiple of eight bytes
		// if true, insert a "carriage return" (or commonly known as ENTER)
		if (chkEight(i)) {
			System.out.print("\n");
		}
		System.out.print(ciphertext[i]+" ");
	}

    System.out.println("\n\nCiphertext (Hex): ");
    System.out.println(asHex(ciphertext));

    /************************************************
     *
     * Can you decrypt the message?
     *
     ************************************************/
    // Change to decrypt mode
    cipher.init(Cipher.DECRYPT_MODE, mykey);

    // Now decrypt the text
    byte[] decryptedText = cipher.doFinal(ciphertext);
    String plain = new String(decryptedText,"UTF8");

    // Print the message
    System.out.println("\nPlaintext=" + plain);

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

  public static Boolean chkEight (int num) {

	  // write your code here
	  // check whether is multiple of eight bytes
	  // if yes, return TRUE
	  // else return FALSE
         int num1;
         num1 = num % 8;
         if(num1== 0) {
             return true;
         }
         else
         {
			 return false;
         }
}
}