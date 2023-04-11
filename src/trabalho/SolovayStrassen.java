package trabalho;

import java.math.BigInteger;
import java.util.Random;

public class SolovayStrassen {

    // Adaptado do seguinte link: http://math.ucdenver.edu/~wcherowi/courses/m5410/ctcprime.html
    public static boolean solovayStrassenTest(BigInteger n, int numRounds) {

        for (int i = 0; i < numRounds; i++) {
            //Gera um BigInteger aleatório, denominado "a", entre 1 e n
            Random rnd = new Random();
            BigInteger a = new BigInteger(n.bitLength(), rnd).add(BigInteger.ONE);

            if (a.divide(n).equals(a.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.TWO), n))) {
                return false; // n é composto
            }
        }

        return true; // n provavelmente é primo
    }
}
