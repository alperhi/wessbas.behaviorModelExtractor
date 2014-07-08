package net.sf.markov4jmeter.behaviormodelextractor.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

/**
 * This class provides helping methods for calculating the mean and deviation
 * values for <code>BigDecimal</code> values.
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class MathUtil {

    /** Precision of <code>BigDecimal</code> division operations, specifying
     *  the number of digits behind the comma. */
    private final static int PRECISION = 64;

    /**
     * Calculates the mean value for a given set of values.
     *
     * @param values  set of values whose mean value shall be calculated.
     *
     * @return a non-negative mean value.
     */
    public static BigDecimal computeMean (final List<BigDecimal> values) {

        BigDecimal sum = BigDecimal.ZERO;

        for (final BigDecimal value : values) {

            sum = sum.add(value);
        }

        return sum.divide(
                new BigDecimal( values.size() ),
                MathUtil.PRECISION,  // must be defined to avoid exceptions;
                RoundingMode.HALF_UP);
    }

    /**
     * Calculates the deviation value for a given set of values.
     *
     * @param values
     *     set of values whose deviation value shall be calculated.
     * @param mean
     *     mean of the given values.
     *
     * @return  a non-negative deviation value.
     */
    public static BigDecimal computeDeviation (final List<BigDecimal> values) {

        // sqrt( sum( (d_{ij} - mean)^2 ) / n );

        final LinkedList<BigDecimal> powers = new LinkedList<BigDecimal>();
        final BigDecimal mean = MathUtil.computeMean(values);

        for (final BigDecimal value : values) {

            final BigDecimal diff = value.subtract(mean);

            powers.add( diff.multiply(diff) );
        }

        final BigDecimal variance = MathUtil.computeMean(powers);

        return Util.sqrt(variance, RoundingMode.HALF_UP);
    }
}
