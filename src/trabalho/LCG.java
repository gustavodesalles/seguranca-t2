package trabalho;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class LCG {
    private BigInteger a = new BigInteger("16807");
    private BigInteger c;
    private BigInteger m;
    private BigInteger xo;
    private List<BigInteger> list = new ArrayList<>();

    public LCG(String aStr, String cStr, int numBits, String seed) {
        this.a = new BigInteger(aStr);
        this.c = new BigInteger(cStr);
        this.m = new BigInteger("2").pow(numBits);
        this.xo = new BigInteger(seed);
    }

    public LCG(BigInteger a, BigInteger c, BigInteger m, BigInteger xo) {
        this.a = a;
        this.c = c;
        this.m = m;
        this.xo = xo;
    }

    public void linearCongruentialGenerator() {
        for (int i = 0; i < 10; i++) { //gera dez números pseudo-aleatórios a partir da seed
            xo = xo.multiply(a).add(c).mod(m);
            list.add(xo);
        }
    }

    public static void main(String[] args) {
        LCG lcg = new LCG("1664525", "1013904223", 256, "23");
//        LCG lcg = new LCG(new BigInteger("7"), new BigInteger("4"), new BigInteger("9"), new BigInteger("3"));
        lcg.linearCongruentialGenerator();
        lcg.list.forEach(bigInteger -> System.out.println(bigInteger.toString()));
        System.out.println(lcg.m.toString());
    }

}
