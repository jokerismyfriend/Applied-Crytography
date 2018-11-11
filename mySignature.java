/***************************************************************
 *
 * A Simple Program to verify the signature
 *
 ***************************************************************/

import java.security.Signature;
import java.security.SignatureException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import sun.misc.*;

public class mySignature {

   public static void main (String[] args) throws Exception {

	if (args.length != 1) {
	  System.err.println("Usage: java mySignature\"text-to-be-signed\"");
	  System.exit(1);
	}

	// Print Header
	System.out.println("A Simple Program to verify the signature\n");

    System.out.println("Generating RSA key pair...");
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(1024);
    KeyPair keyPair = kpg.genKeyPair();
    System.out.println("Done generating key pair.");

    // Get the bytes of the data from the first argument
    byte[] data = args[0].getBytes("UTF8");
    System.out.println("\nText:\n" + args[0]);

    // Get an instance of the Signature object and initialize it
    // Fill-in here



		// Use the private key for signing
    sig.initSign(keyPair.getPrivate());

    // Prepare to sign the data
    sig.update(data);

    // Actually sign it
    byte[] signed = sig.sign();

    System.out.println("\nSignature:\n" + asHex(signed));

    // Reinitialize the signature object
    // with the public key for verification.
    System.out.println("\nNow verifying the signature....");
    sig.initVerify(keyPair.getPublic());

    // Pass in the data that was signed
    sig.update(data);

    // Verify the signed[]
    // by declaring a variable "verified" as boolean
    boolean verified = false;
    try
    	{ verified = sig.verify(signed);  }
    catch (SignatureException se)
    	{ verified = false;  }

	 // print verification result
    if (verified)
    { System.out.println("\nSignature verified.");  }
    else
    { System.out.println("\nSignature did not match.");  }
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
