
package com.rea.robotsimulation.grid;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import com.rea.robotsimulation.util.FunctionUtils;
import com.rea.robotsimulation.util.Predicates;

/**
 * This enum class represent all allowed directions a particular object can be facing on top of a
 * {@link Grid}.
 *
 * <P>
 * These enum values are indexed to be clockwise position and {@link #of(int)} static method can be
 * used to get the enum value by that index.
 * </p>
 *
 * @see #getStepAheadGenerator()
 */
public enum FacingDirection
{
    // define directions indexed to clockwise.
    NORTH(0, FacingDirectionStepAheadService::stepAheadToNorth),
    EAST(1, FacingDirectionStepAheadService::stepAheadToEast),
    SOUTH(2, FacingDirectionStepAheadService::stepAheadToSouth),
    WEST(3, FacingDirectionStepAheadService::stepAheadToWest);

    private final int index;
    private final Function<GridPoint, GridPoint> stepAheadGenerator;

    private FacingDirection(int index, Function<GridPoint, GridPoint> stepAheadGenerator)
    {
        this.index = index;
        this.stepAheadGenerator = stepAheadGenerator;
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
        // @formatter:off
        return Stream.of(FacingDirection.values())
                .filter(Predicates.of(
                        FunctionUtils.reduceToOneArgFunction(FacingDirection::indexEquals, index)))
                .findFirst()
                .orElseThrow(IndexOutOfBoundsException::new);
        // @formatter:on
    }

    private static boolean indexEquals(FacingDirection direction, int index)
    {
        Objects.requireNonNull(direction);
        return Objects.equals(direction.getIndex(), index);
    }

    /**
     * Returns the FacingDirection on the left of the current one
     */
    public FacingDirection toLeft()
    {
        // since values are indexed to clockwise we can use
        // left rotation algorithm to work out the next on left.
        int valuesLength = values().length;
        return FacingDirection.of((this.index - 1 + valuesLength) % valuesLength);
    }

    /**
     * Returns the FacingDirection on the right of the current one
     */
    public FacingDirection toRight()
    {
        // since values are indexed to clockwise we can use
        // right rotation algorithm to work out the next on right.
        int valuesLength = values().length;
        return FacingDirection.of((this.index + 1) % valuesLength);
    }

    /**
     * Returns a function that can generate the {@link GridPoint} which is one step ahead to this
     * facing direction.
     */
    public Function<GridPoint, GridPoint> getStepAheadGenerator()
    {
        return this.stepAheadGenerator;
    }
}
