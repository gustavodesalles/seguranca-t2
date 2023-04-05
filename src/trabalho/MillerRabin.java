package trabalho;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {
    private static final BigInteger menosUm = new BigInteger("-1");
    private static final BigInteger zero = new BigInteger("0");
    private static final BigInteger um = new BigInteger("1");
    private static final BigInteger dois = new BigInteger("2");

    private void factorNumber(BigInteger integer, BigInteger s, int d) {
        s = zero;
        BigInteger aux = integer.subtract(um);

        while (aux.mod(dois).equals(zero)) {
            aux = aux.divide(dois);
            s.add(um);
        }

        d = aux.intValue();
    }

    private BigInteger randomBigInteger(BigInteger n) { // https://stackoverflow.com/questions/2290057/how-to-generate-a-random-biginteger-value-in-java
        Random rand = new Random();
        BigInteger result = new BigInteger(n.bitLength(), rand);
        while( result.compareTo(n) >= 0 ) {
            result = new BigInteger(n.bitLength(), rand);
        }
        return result;
    }

    public String millerRabinTest(BigInteger integer, int numRounds) { //adaptado do Cormen
        BigInteger s = null;
        int d = 0;

        if (integer.mod(dois).equals(zero)) return "composto";
        factorNumber(integer, s, d);

        BigInteger a = randomBigInteger(integer).add(dois);
        BigInteger b = a.pow(d).mod(integer);

        if (b.mod(integer).equals(um)) return "provavelmente primo";

        for (BigInteger i = zero; i.equals(s.subtract(um)); i.add(um)) {
            if (b.mod(integer).equals(menosUm)) {
                return "provavelmente primo";
            } else {
                b = b.pow(2).mod(integer);
            }
        }

        return "composto";
    }
}