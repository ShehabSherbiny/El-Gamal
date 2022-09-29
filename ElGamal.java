import java.math.BigInteger;
import java.util.Random;

// The shared base g=666
// The shared prime p=6661
// Bob's public key PK: g^x mod p = 2227

// sk = 666^x mod 6661 = 2227

// Send the message '2000' to Bob


public class ElGamal{
    BigInteger g, p, sk, pk;

    //For Alice
    public ElGamal(BigInteger g, BigInteger p){
        this.g = g;
        this.p = p;
        sk = createSecretKey();
        pk = createPublicKey();
    }

    //For Bob
    public ElGamal(BigInteger g, BigInteger p, BigInteger sk){
        this.g = g;
        this.p = p;
        this.sk = sk;
        pk = createPublicKey();
    }


    /**Encryption:
    @param m: message to be encrypted
    */
    public BigInteger[] encrypt(BigInteger m, BigInteger pk){

        BigInteger c1 = g.modPow(sk,p);
        //BigInteger c2 = h.pow(r.intValue()).multiply(m).mod(p);
        BigInteger c2 = (m.multiply(pk.pow(sk.intValue()))).mod(p);
        BigInteger[] encrypted = {c1,c2};
        
        return encrypted;
    } 


    //Dencryption:
    //@param 
    
    public BigInteger decrypt(BigInteger c1, BigInteger c2){

        //BigInteger m = c2.divide(c1.pow(sk.intValue())).mod(p);
     
        //m = c2 * x^-1 mod p -> c2 * s^(p-2) mod p
        BigInteger s = c1.modPow(sk, p);
        BigInteger m = c2.multiply(s.pow(p.intValue()-2)).mod(p);

        // BigInteger s = c1.modPow(sk, p);
        // BigInteger i = s.modInverse(p);
        // BigInteger m = i.multiply(c2);
        // System.out.printf("s: %s\n", s);
        // System.out.printf("i: %s\n", i);
        // System.out.printf("m: %s\n", m);

        
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

    private BigInteger createSecretKey(){
        Random rand = new Random();
        BigInteger x = new BigInteger(String.format("%s", rand.nextInt(6661) + 1)) ; //random for key generation
        System.out.printf("x (secret key): %s\n",x);

        return x;
    }

    private BigInteger createPublicKey(){
        return g.modPow(sk, p);
    }
}



