package trabalho;

import java.math.BigInteger;
import java.util.Random;

public class Xorshift {

    public static BigInteger xorshift(int numBits) { // adaptado de https://www.javamex.com/tutorials/random_numbers/xorshift.shtml, em si adaptado de Marsaglia (2003)
//        BigInteger seed = BigInteger.TWO.pow(numBits).subtract(BigInteger.ONE); // seed arbitrária
        Random rnd = new Random();
        BigInteger seed = new BigInteger(numBits, rnd); // utiliza o Random somente para gerar a seed limitada pelo número de bits

        seed = seed.xor(seed.shiftLeft(21));
        seed = seed.xor(seed.shiftRight(35));
        seed = seed.xor(seed.shiftLeft(4)); // valores utilizados por Marsaglia no artigo

        return seed; // obtém um número pseudoaleatório a partir de uma seed e três XORs com shifts de bits
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(xorshift(128));
        }
    }
}
