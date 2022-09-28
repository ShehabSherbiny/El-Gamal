import java.math.BigInteger;
import java.util.Random;

// The shared base g=666
// The shared prime p=6661
// Bob's public key PK: g^x mod p = 2227

// sk = 666^x mod 6661 = 2227

// Send the message '2000' to Bob


public class ElGamal{

    static BigInteger x;    

    /**Encryption:
    @param m: message to be encrypted
    */
    public static BigInteger[] encrypt(BigInteger m){

        //Base and Prime Number
        BigInteger g = new BigInteger("666");
        BigInteger p = new BigInteger("6661");

        // calc: h = g^random mod p
        Random rand = new Random();
        x = new BigInteger(String.format("%s", rand.nextInt(6661) + 1)) ; //random for key generation
        System.out.printf("x (secret key): %s\n",x);
        
        //Private Key ?
        BigInteger h = g.modPow(x, p);
        System.out.println("h: " + h);
        BigInteger r = new BigInteger(String.format("%s", rand.nextInt(6661) + 1)) ;//random for encryption
        System.out.println("r: " + r);
        BigInteger c1 = g.modPow(r,p);
        //BigInteger c2 = h.pow(r.intValue()).multiply(m).mod(p);
        BigInteger c2 = m.multiply(h.modPow(r, p)).mod(p);
        BigInteger[] encrypted = {c1,c2};
        
        return encrypted;
    } 


    //Dencryption:
    //@param 
    
    public static BigInteger decrypt(BigInteger sk, BigInteger c1, BigInteger c2){

        //BigInteger m = c2.divide(c1.pow(sk.intValue())).mod(new BigInteger("6661"));

        //Base and Prime number
        BigInteger g = new BigInteger("666");
        BigInteger p = new BigInteger("6661");
     
        //m = c2 * x^-1 mod p -> c2 * s^(p-2) mod p
        //BigInteger m = c2.multiply(s.modPow(new BigInteger("-1"), p));

        BigInteger s = c1.modPow(x, p); //sk should be h
        BigInteger i = s.modInverse(p);
        BigInteger m = i.multiply(c2).mod(p);
        System.out.printf("x (secret key): %s\n", x);
        System.out.printf("s: %s\n", s);
        System.out.printf("i: %s\n", i);
        System.out.printf("m: %s\n", m);

        
        return m;
    }
    
    
    public static BigInteger intercept(BigInteger g, BigInteger p, BigInteger pk){
        BigInteger secretKey = BigInteger.ZERO;
        for(BigInteger i = BigInteger.ZERO; i.compareTo(p) < 0 ; i= i.add(BigInteger.ONE)){
            
            if (g.modPow(i, p).equals(pk)){
                //System.out.printf("i: %s; g^x mod p: %s; pk:%s; equals %s \n", i, g.modPow(i, p), pk, g.modPow(i, p).equals(pk));
                secretKey = i;
                break;
            } 
        }
        
        return secretKey;
    }

}



