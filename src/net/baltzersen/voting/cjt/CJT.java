package net.baltzersen.voting.cjt;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by J.K. Baltzersen on 04.08.2018.
 */
public class CJT
{
    public void calculateGroupCompetence(long groupSize, double individualCompetence)
    {
        //long groupSize = 10001;//161;
        //double individualCompetence = 0.49;
        double inverseIndividualCompetence = 1 - individualCompetence;

        double accumulatedCompetence = 0.0;
        long smallestMajority = (long) Math.ceil((groupSize+1)/2);

        for (long i = groupSize; i >= smallestMajority; --i)
        {
            long theOtherEnd = groupSize - i;
            BigInteger initial1 = BigInteger.valueOf(groupSize);
            BigInteger initial2 = BigInteger.valueOf(i);
            BigInteger initial3 = BigInteger.valueOf(theOtherEnd);
            BigInteger dividend = this.factorial(initial1);
            BigInteger multiplier = this.factorial(initial2);
            BigInteger multiplicand = this.factorial(initial3);
            BigInteger divisor = multiplier.multiply(multiplicand);
            BigDecimal quotient = (new BigDecimal(dividend)).divide(new BigDecimal(divisor), 20, BigDecimal.ROUND_HALF_UP);

            BigDecimal base1 = BigDecimal.valueOf(individualCompetence);
            int exponent1 = (int) i;
            //BigDecimal power1 = this.power(base1, exponent1);
            BigDecimal power1 = base1.pow(exponent1);

            BigDecimal base2 = BigDecimal.valueOf(inverseIndividualCompetence);
            int exponent2 = (int) theOtherEnd;
            //BigDecimal power2 = this.power(base2, exponent2);;
            BigDecimal power2 = base2.pow(exponent2);

            BigDecimal powpow = power1.multiply(power2);
            BigDecimal iterationResult = quotient.multiply(powpow);

                accumulatedCompetence += iterationResult.doubleValue();
        }

        System.out.println("groupCompetence = " + accumulatedCompetence);
    }

    private BigInteger factorial(BigInteger n)
    {
        if (n.equals(BigInteger.ONE))
        {
            return n;
        }
        if (n.equals(BigInteger.ZERO))
        {
            return BigInteger.ONE;
        }
        if (n.signum() == -1)
        {
            throw new IllegalArgumentException();
        }
        long longVal = n.intValue();
        BigInteger val = BigInteger.ONE;
        for (long i = longVal; i >= 1; --i)
        {
            val = val.multiply(BigInteger.valueOf(i));
        }
        return val;
    }
}
