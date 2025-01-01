import java.math.BigInteger;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();

        SortedSet<BigInteger> primes = new TreeSet<>();

        while(primes.size() < 20){
            BigInteger bigInteger = new BigInteger(2000, new Random());
            primes.add(bigInteger.nextProbablePrime());
        }

        BigInteger bigInteger = new BigInteger(2000, new Random());
        System.out.println("The integer is " + bigInteger);
        System.out.println("The next probable prime is " + bigInteger.nextProbablePrime());

        Long end = System.currentTimeMillis();
        System.out.println("The time taken was " + (end - start) + "ms.");

    }
}