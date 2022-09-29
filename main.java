import java.math.BigInteger;

/*
Calculation:
https://www.debjitbiswas.com/elgamal/
*/


public class main{
   
    public static void main(String[] args) {
        
        var alice = new ElGamal(new BigInteger("666"), new BigInteger("6661"));

        var encryptMsg = alice.encrypt(new BigInteger("2000"), new BigInteger("2227"));
        System.out.printf("C1: %s \nC2: %s \n", encryptMsg[0], encryptMsg[1]);
        
        //Intercept:
        var bobSK = ElGamal.intercept(new BigInteger("666"), new BigInteger("6661"), new BigInteger("2227"));
        System.out.printf("Private key found through interception: %s\n", bobSK);

        var eve = new ElGamal(new BigInteger("666"), new BigInteger("6661"), bobSK);

        //Decrypt
        System.out.printf("sk: %s \nc1: %s \nc2: %s\n", bobSK, encryptMsg[0], encryptMsg[1]);
        System.out.printf("Message found through decryption: %s\n", eve.decrypt(encryptMsg[0], encryptMsg[1]));

    } 
}