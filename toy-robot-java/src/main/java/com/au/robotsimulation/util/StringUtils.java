
package com.au.robotsimulation.util;

/**
 * This class contains utility methods related to various {@code String} operation.
 *
 */
public class StringUtils
{
    /**
     * Check if the given String <strong>looks</strong> empty. In other words, this will return
     * {@code true} if there is only white space present, if anything.
     *
     * @return true if {@code (s == null || s.trim().isEmpty())}
     */
    public static boolean isEmpty(CharSequence s)
    {
        return s == null || s.toString().trim().isEmpty();
    }
}
