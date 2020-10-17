
package com.rea.robotsimulation.robot;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.GridPoint;

/**
 * This defines a Scanner which a {@code Robot} can use to scan it's step.
 *
 */
public interface RobotStepScanner
{
    public boolean stepAheadSafe(GridPoint point, FacingDirection facingDirection);
}
