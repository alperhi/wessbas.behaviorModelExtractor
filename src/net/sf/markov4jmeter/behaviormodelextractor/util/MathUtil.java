/***************************************************************************
 * Copyright (c) 2016 the WESSBAS project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/


package net.sf.markov4jmeter.behaviormodelextractor.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

/**
 * This class provides helping methods for calculating the mean and deviation
 * values for <code>BigDecimal</code> values. Furthermore, it provides several
 * rounding operations.
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class MathUtil {


    /* *****************************  constants  **************************** */


    /** Precision of <code>BigDecimal</code> division operations, specifying
     *  the number of digits behind the comma. */
    private final static int PRECISION = 64;


    /* **************************  public methods  ************************** */


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
                BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Calculates the deviation value for a given set of values.
     *
     * @param values  set of values whose deviation value shall be calculated.
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

    /**
     * Rounds a given <code>double</code> value to 4 digits behind the comma.
     *
     * @param value  value to be rounded.
     *
     * @return  the rounded value.
     */
    public static double round (final double value) {

        return ((double)Math.round(value * 10000 + 0.00005)) / 10000;
    }

    /**
     * Rounds a given <code>BigDecimal</code> value to 4 digits behind the
     * comma.
     *
     * @param value  value to be rounded.
     *
     * @return  the rounded value.
     */
    public static BigDecimal round (final BigDecimal value) {

        return new BigDecimal(MathUtil.round( value.doubleValue() ));
    }
}
