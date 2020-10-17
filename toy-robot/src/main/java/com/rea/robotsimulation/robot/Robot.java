
package com.rea.robotsimulation.robot;

import java.util.Optional;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.Grid;
import com.rea.robotsimulation.grid.GridPoint;

/**
 * This Interface defines accessible states and behaviours of a Robot who can be roam on top of a
 * {@link Grid}.
 */
public interface Robot
{
    /**
     * @return current position of the {@code Robot} on the grid.
     */
    public GridPoint getCurrentGridPoint();

    /**
     * Set the given grip point as the current position of the {@code Robot} on the Grid. This will
     * make the {@code Robot} to be placed at the given point.
     */
    public void setCurrentGridPoint(GridPoint currentGridPoint);

    /**
     * @return current facing direction of the {@code Robot}.
     */
    public FacingDirection getFacingDirection();

    /**
     * Set the given facing direction as the {@code Robot}'s facingDirection. This will make the
     * {@code Robot} to be turned to that {@code FacingDirection}.
     */
    public void setFacingDirection(FacingDirection facingDirection);

    /**
     * This method will move the {@code Robot} one step towards it's facing direction.
     *
     * @see #getFacingDirection()
     * @see #getCurrentGridPoint()
     */
    public void move();

    /**
     * This method will rotate the {@code Robot} opposite to clockwise for 90 degrees, from it's
     * facing direction.
     *
     * @see #getFacingDirection()
     * @see #getCurrentGridPoint()
     */
    public void rotateLeft();

    /**
     * This method will rotate the robot clockwise for 90 degrees, from it's facing direction.
     *
     * @see #getFacingDirection()
     * @see #getCurrentGridPoint()
     */
    public void rotateRight();

    /**
     * This method will return the report of the current position of the {@code Robot} on the grid
     * in the following format of,
     *
     * <pre>
     *  x, y, FacingDirection.
     * </pre>
     *
     * @return optional report with null if not placed yet.
     */
    public Optional<String> report();

    /**
     * This method checks if the {@code Robot} has successfully placed on a {@link Grid}. It will
     * not be considered as placed if any of the following conditions is not met,
     * <ul>
     * <li>{@code CurrentGridPoint} has not been set</li>
     * <li>{@code FacingDirection} has not been set</li>
     * <ul>
     *
     * @return true if the {@code Robot} has successfully placed on a Grid, false otherwise.
     */
    public default boolean isPlaced()
    {
        return (getCurrentGridPoint() != null) && (getFacingDirection() != null);
    }
}
