package trabalho;

import java.math.BigInteger;

public class Xorshift {

    public BigInteger xorshift(int numBits) { // adaptado de https://www.javamex.com/tutorials/random_numbers/xorshift.shtml, em si adaptado de Marsaglia (2003)
        BigInteger seed = BigInteger.TWO.pow(numBits);

        seed = seed.xor(seed.shiftLeft(21));
        seed = seed.xor(seed.shiftRight(35));
        seed = seed.xor(seed.shiftLeft(4)); // valores utilizados por Marsaglia no artigo

        return seed; // obtém um número pseudoaleatório a partir de uma seed e três XORs com shifts de bits
    }
}
