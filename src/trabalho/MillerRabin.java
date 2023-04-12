package trabalho;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {

    // Adaptado do Cormen, com auxílio do ChatGPT; retorna "false" caso seja composto e "true" se provavelmente primo
    public static boolean millerRabinTest(BigInteger n, int numRounds) {
        // Casos mais simples (1 e 3)
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return false;
        } else if (n.compareTo(new BigInteger("3")) <= 0) {
            return true;
        }

        // Escreve n-1 no formato d * 2^s fatorando 2
        int s = 0;
        BigInteger d = n.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
            s++;
        }

        // Realiza os testes
        Random rnd = new Random();
        for (int i = 0; i < numRounds; i++) {
            BigInteger a;
            do {
                a = new BigInteger(n.bitLength(), rnd); // gera um número aleatório de 2 a n-2
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(n.subtract(BigInteger.TWO)) > 0); // se a < 2 ou a > n-2, tenta de novo

            // x = a^d mod n
            BigInteger x = a.modPow(d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) continue;

            // Repete s vezes
            boolean composto = true;
            for (int j = 0; j < s; j++) {
                x = x.modPow(BigInteger.TWO, n); // x = x² mod n

                if (x.equals(BigInteger.ONE)) {
                    return false;
                } else if (x.equals(n.subtract(BigInteger.ONE))) { // se x ≠ 1 e x ≠ n-1, é composto
                    composto = false; // provavelmente primo
                    break;
                }
            }

            if (composto) {
                return false;
            }
        }

        return true; // provavelmente é primo
    }

    public static void main(String[] args) {
//        System.out.println(millerRabinTest(BigInteger.TEN, 4));
//        System.out.println(millerRabinTest(BigInteger.TWO, 1));
        System.out.println(millerRabinTest(new BigInteger("23"), 5));
    }
}
