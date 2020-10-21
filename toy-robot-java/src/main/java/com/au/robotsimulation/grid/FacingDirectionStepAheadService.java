
package com.au.robotsimulation.grid;

/**
 * This service class provides services to calculate/generate the {@link GridPoint}, step ahead
 * relative to a given {@code GridPoint}.
 *
 */
public class FacingDirectionStepAheadService
{
    /**
     * @return a new {@code GridPoint} which is one step ahead to North.
     */
    public static GridPoint stepAheadToNorth(GridPoint point)
    {
        return GridPoint.of(point.getX(), point.getY() + 1);
    }

    /**
     * @return a new {@code GridPoint} which is one step ahead to East.
     */
    public static GridPoint stepAheadToEast(GridPoint point)
    {
        return GridPoint.of(point.getX() + 1, point.getY());
    }

    /**
     * @return a new {@code GridPoint} which is one step ahead to West.
     */
    public static GridPoint stepAheadToWest(GridPoint point)
    {
        return GridPoint.of(point.getX() - 1, point.getY());
    }

    /**
     * @return a new {@code GridPoint} which is one step ahead to the South.
     */
    public static GridPoint stepAheadToSouth(GridPoint point)
    {
        return GridPoint.of(point.getX(), point.getY() - 1);
    }
}
