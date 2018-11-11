import java.security.*;
import javax.crypto.*;
import java.security.spec.*;
import javax.crypto.spec.*;

public class myDesCbc
{
  public static void main (String[] args) throws Exception
  {

	String text = "Hello123Hello123Hello123";

    System.out.println("Original Text=" + text + "\n");


    // Create an 8-byte initialization vector (iv) as a byte array
      // using hardcoded method

       // ********  Add your code here ***********
      
      // using SecureRandom class method
      
      // ********  Add your code here ***********
      
      
    System.out.println("IV = " + asHex(iv) + ", lenght (byte) =" + iv.length );
    // Create the IV object
    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
      
    System.out.println("\nGenerating a 56-bit DES key...");
    // Create a 56-bit DES key
    KeyGenerator kg = KeyGenerator.getInstance("DES");

    // need to initialize with the keysize
    kg.init(56);
    Key mykey = kg.generateKey();

    System.out.println("The key is generated.");

      
    // Create a cipher object and use the generated key to initialize it
    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    
    cipher.init(Cipher.ENCRYPT_MODE, mykey, paramSpec);

    byte[] plaintext = text.getBytes("UTF8");

    // Print out the bytes of the plaintext
    System.out.println("\nPlaintext: ");
    for (int i=0;i<plaintext.length;i++) {

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

		if (chkEight(i)) {
			System.out.print("\n");
	    }
		System.out.print(ciphertext[i]+" ");
	}

    System.out.println("\n\nCiphertext (Hex): ");
    System.out.println(asHex(ciphertext));

    /************************************************
     *
     * To decrypt the message
     *
     ************************************************/
    // Change to decrypt mode, remember to add the paraspec and key (using init)

    // ********  Add your code here ***********

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
                strbuf.append(" ");
    	      }

           return strbuf.toString();
  }


  public static Boolean chkEight (int num) {
         int num1, rem;
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