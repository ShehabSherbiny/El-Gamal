import java.math.BigInteger;
import java.util.Random;
import java.util.random.*;

// The shared base g=666
// The shared prime p=6661
// Bob's public key PK: g^x mod p = 2227

// sk = 666^x mod 6661 = 2227

// Send the message '2000' to Bob


public class ElGamal{


    /**Encryption:
    @param m: message to be encrypted
    */
    public static BigInteger[] encrypt(BigInteger m){

        //Base and Prime Number
        BigInteger g = new BigInteger("666");
        BigInteger p = new BigInteger("6661");

        // calc: h = g^random mod p
        Random rand = new Random();
        BigInteger x = new BigInteger(String.format("%s", rand.nextInt(6661) + 1)) ; //random for key generation

        //Private Key ?
        BigInteger h = g.modPow(x, p);
        
        BigInteger r = new BigInteger(String.format("%s", rand.nextInt(6661) + 1)) ; //random for encryption
        BigInteger c1 = g.modPow(r, p);
        BigInteger c2 = h.pow(r.intValue()).multiply(m).mod(p);
        BigInteger[] encrypted = {c1,c2};
        
        return encrypted;
    } 


    /**Dencryption:
    @param 
    
    public static BigInteger decrypt(BigInteger c1, BigInteger c2){

        //Base and Prime number
        BigInteger g = new BigInteger("666");
        BigInteger p = new BigInteger("6661");

        //PrivateKey ?
        BigInteger x = g.modPow(pk, p);

        //s = c1^x mod p
        BigInteger s = c1.modPow(x, p);

        //m = c2 * x^-1 mod p -> c2 * s^(p-2) mod p
        BigInteger m = c2.multiply(s.modPow(p.subtract(new BigInteger("2")), p));

        return m;
        
    }
    */
 

}



