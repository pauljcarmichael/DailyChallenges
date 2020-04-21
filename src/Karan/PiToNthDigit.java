package Karan;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class PiToNthDigit {
    private static MathContext mc = new MathContext(1100);
    private static BigDecimal denom1 = new BigDecimal(9801, mc);
    private static BigDecimal numer1 = new BigDecimal(1103, mc);
    private static BigDecimal numer2 = new BigDecimal(26390, mc);
    private static BigDecimal denom2 = new BigDecimal(396, mc);
    private static BigDecimal one = new BigDecimal(1, mc);
    private static BigDecimal two = new BigDecimal(2, mc);
    private static BigDecimal four = new BigDecimal(4, mc);


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many decimal places would you like pi presented to? (Maximum 1000)");
        int numberOfDigits = Integer.valueOf(scanner.nextLine());
        System.out.println(srini().toString().substring(0,numberOfDigits+2));
    }

    public static BigDecimal srini() { // Uses Srinivasa Ramanujan's method
        BigDecimal pi = new BigDecimal(0.0, mc);

        BigDecimal sum = new BigDecimal(0.0, mc);

        for (int i = 0; i < 200; i++) {
            BigDecimal k = new BigDecimal(i, mc);
            BigDecimal numerator;
            numerator = calculateFactorial(i * 4).multiply(numer1.add(numer2.multiply(k)));

            BigDecimal denominator;
            denominator = calculateFactorial(i).pow(4).multiply(denom2.pow(4 * i));

            sum = sum.add(numerator.divide(denominator, 1100, RoundingMode.HALF_UP));
        }

        BigDecimal intermediateValue = two.multiply(herron()).divide(denom1, 1100, RoundingMode.HALF_UP).multiply(sum);
        pi = one.divide(intermediateValue, 1100, RoundingMode.HALF_UP);
        return pi;
    }

    private static BigDecimal calculateFactorial(int number) {
        BigDecimal factorial = BigDecimal.ONE;
        for (int i = 1; i <= number; i++) {
            factorial = factorial.multiply(new BigDecimal(i));
        }
        return factorial;
    }

    public static BigDecimal herron() { // Returns sqrt 2.
        BigDecimal x = new BigDecimal(1.4, mc);
        BigDecimal two = new BigDecimal(2, mc);
        BigDecimal half = new BigDecimal(0.5, mc);

        for (int i = 0; i < 10; i++) {
            x = half.multiply(x.add(two.divide(x, 1100, RoundingMode.HALF_EVEN)));
        }
        return x;
    }
}
