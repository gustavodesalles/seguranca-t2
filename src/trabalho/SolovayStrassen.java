package trabalho;

import java.math.BigInteger;
import java.util.Random;

public class SolovayStrassen {

    // Adaptado do seguinte link: http://math.ucdenver.edu/~wcherowi/courses/m5410/ctcprime.html
    public static boolean solovayStrassenTest(BigInteger n, int numRounds) {
        // casos base
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) return true; // caso seja 2 ou 3, é primo
        if (n.equals(BigInteger.ONE) || n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return false; // caso seja 1 ou par maior que 2, não é primo

        for (int i = 0; i < numRounds; i++) {
            // Gera um BigInteger aleatório, denominado "a", entre 1 e n-1
            Random rnd = new Random();
            BigInteger a = new BigInteger(n.bitLength(), rnd).mod(n.subtract(BigInteger.ONE)).add(BigInteger.ONE);

            int jacobi = jacobiSymbol(a, n);

            BigInteger y = a.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.TWO), n);
            if (jacobi == 0 || !y.equals(BigInteger.valueOf(jacobi).mod(n))) {
                return false; // n é composto
            }
        }

        return true; // n provavelmente é primo
    }

    private static int jacobiSymbol(BigInteger a, BigInteger n) {
        // Calcula o símbolo de Jacobi (a/n). Retirado de https://www.johndcook.com/blog/2019/02/12/computing-jacobi-symbols/, em si adaptado de Bach e Shallit
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("O módulo n deve ser ímpar");
        }
        int result = 1;
        while (!a.equals(BigInteger.ZERO)) {
            while (a.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                a = a.divide(BigInteger.TWO);
                if (n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(3)) || n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(5))) {
                    result = -result;
                }
            }
            BigInteger temp = a;
            a = n;
            n = temp;
            if (a.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) && n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) {
                result = -result;
            }
            a = a.mod(n);
        }
        if (n.equals(BigInteger.ONE)) {
            return result;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(solovayStrassenTest(BigInteger.TEN, 10));
        System.out.println(solovayStrassenTest(BigInteger.TWO, 3));
        System.out.println(solovayStrassenTest(BigInteger.valueOf(7), 1));
        System.out.println(solovayStrassenTest(BigInteger.valueOf(23), 4));
    }
}
