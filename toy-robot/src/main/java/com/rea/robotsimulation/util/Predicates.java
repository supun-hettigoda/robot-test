
package com.rea.robotsimulation.util;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This class contains convenience methods to create Predicates from various other functional
 * interfaces.
 */
public class Predicates
{
    /**
     * Convert the given function to a Predicate.
     *
     * @param <T> the type of the first argument to the function
     * @param <R> the type of the result of the function
     * @param function the function that need to be converted to a predicate.
     * @return Predicate which represent the function.
     */
    public static <T, R> Predicate<T> of(Function<T, Boolean> function)
    {
        return new Predicate<T>()
        {
            @Override
            public boolean test(T t)
            {
                return function.apply(t);
            }
        };
    }
}
