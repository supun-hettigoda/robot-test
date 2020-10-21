
package com.au.robotsimulation.grid;

import java.util.Objects;

/**
 * This Interface defines a Grid, based on columns and rows.
 */
public interface Grid
{
    /**
     * @return the number of columns this grid has, and it will always be a positive integer.
     */
    public int getNumberOfColumns();

    /**
     * @return the number of rows this grid has, and it will always be a positive integer.
     */
    public int getNumberOfRows();

    /**
     * This method will check the given {@link GridPoint} is within the bound of this grid.
     *
     * @param gridPoint - the point which need to be checked against this grid and cannot be null.
     */
    public default boolean inBound(GridPoint gridPoint)
    {
        Objects.requireNonNull(gridPoint);
        return (gridPoint.getX() >= 0 && gridPoint.getX() < getNumberOfColumns())
                && (gridPoint.getY() >= 0 && gridPoint.getY() < getNumberOfRows());
    }
}
