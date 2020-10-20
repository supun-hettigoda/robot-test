
package com.rea.robotsimulation.util;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * This class contains useful methods for dealing with Java 8 functional interfaces and types such
 * as {@link Function}, {@link Supplier}, {@link Consumer}, {@link Predicate}, etc.
 */
public class FunctionUtils
{
    /**
     * This method can be used to reduce a {@link BiFunction} to a {@link Function}.
     *
     * @param <T> the type of the first argument to the function
     * @param <U> the type of the second argument to the function
     * @param <R> the type of the result of the function
     * @param twoArgFunction the function that need to be reduced.
     * @param parameter - additional parameter.
     *
     * @return reduced Function.
     */
    public static <T, U, R> Function<T, R>
            reduceToOneArgFunction(BiFunction<T, U, R> twoArgFunction, U parameter)
    {
        return new Function<T, R>()
        {
            @Override
            public R apply(T t)
            {
                return twoArgFunction.apply(t, parameter);
            }
        };
    }
}
