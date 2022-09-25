import java.math.BigInteger;

/*
Calculation:
https://www.debjitbiswas.com/elgamal/
*/


public class main{
   
    public static void main(String[] args) {
        
        var elG = new ElGamal();
        var encryptMsg = elG.encrypt(new BigInteger("2000"));
        System.out.printf("C1: %s \nC2: %s", encryptMsg[0], encryptMsg[1]);
    } 
}