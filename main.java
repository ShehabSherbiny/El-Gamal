import java.math.BigInteger;

/*
Calculation:
https://www.debjitbiswas.com/elgamal/
*/


public class main{
   
    public static void main(String[] args) {
        
        var elG = new ElGamal();
        var encryptMsg = elG.encrypt(new BigInteger("2000"));
        System.out.printf("C1: %s \nC2: %s \n", encryptMsg[0], encryptMsg[1]);
        
        
        System.out.printf("Private key found through interception: %s\n", elG.intercept(new BigInteger("666"), new BigInteger("6661"), new BigInteger("2227")));
    } 
}