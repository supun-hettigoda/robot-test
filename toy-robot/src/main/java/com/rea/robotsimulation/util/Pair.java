
package com.rea.robotsimulation.util;

/**
 * This class represent a Pair of two instances.
 *
 * @param <F> type of the first instance
 * @param <S> type of the second instance
 */
public class Pair<F, S>
{
    private final F first;
    private final S second;

    public Pair(F first, S second)
    {
        this.first = first;
        this.second = second;
    }

    /**
     * @return the first
     */
    public F getFirst()
    {
        return first;
    }

    /**
     * @return the second
     */
    public S getSecond()
    {
        return second;
    }

    public static <F, S> Pair<F, S> of(F first, S second)
    {
        return new Pair<F, S>(first, second);
    }
}
