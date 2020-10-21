
package com.au.robotsimulation.robot;

import com.au.robotsimulation.grid.FacingDirection;
import com.au.robotsimulation.grid.GridPoint;

/**
 * This defines a Scanner which a {@code Robot} can use to scan it's step.
 *
 */
public interface RobotGridScanner
{
    /**
     * @return true if the step ahead is safe relative to the given point and facing direction.
     */
    public boolean stepAheadSafe(GridPoint point, FacingDirection facingDirection);

    /**
     * @return true if the given point is exist on the grid.
     */
    public boolean pointExist(GridPoint point);
}
