
package com.rea.robotsimulation.grid;

import java.util.Objects;
import java.util.stream.Stream;

import com.rea.robotsimulation.util.FunctionUtils;
import com.rea.robotsimulation.util.Predicates;

/**
 * This enum class represent all allowed directions anyone can be facing on top of a {@link Grid}.
 *
 */
public enum FacingDirection
{
    // define directions indexed to clockwise.
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private int index;

    private FacingDirection(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return this.index;
    }

    /**
     * Find the {@code FacingDirection} belong to the given index.
     *
     * @return the matching FacingDirection or, throw {@code IndexOutOfBoundsException} in case no
     *         match found for the given index.
     */
    public static FacingDirection of(int index)
    {
        return Stream.of(FacingDirection.values()).filter(
                Predicates.of(
                        FunctionUtils.reduceToOneArgFunction(FacingDirection::indexEquals, index)))
                .findFirst().orElseThrow(IndexOutOfBoundsException::new);
    }

    /**
     * Returns the FacingDirection on the left of the current one
     */
    public FacingDirection toLeft()
    {
        int allDirections = FacingDirection.values().length;
        return FacingDirection.of((this.index - 1 + allDirections) % allDirections);
    }

    /**
     * Returns the FacingDirection on the right of the current one
     */
    public FacingDirection toRight()
    {
        int allDirections = FacingDirection.values().length;
        return FacingDirection.of((this.index + 1) % allDirections);
    }

    private static boolean indexEquals(FacingDirection direction, int index)
    {
        Objects.requireNonNull(direction);
        return direction.getIndex() == index;
    }
}
