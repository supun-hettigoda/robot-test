
package com.au.robotsimulation.robot;

import java.util.Objects;

import com.au.robotsimulation.grid.FacingDirection;
import com.au.robotsimulation.grid.Grid;
import com.au.robotsimulation.grid.GridPoint;

/**
 * This {@code RobotGridScanner} can scan a one step ahead on the {@code Grid}.
 *
 */
public class OneStepAheadGridScanner extends AbstractRobotGridScanner
{
    public OneStepAheadGridScanner(Grid grid)
    {
        super(grid);
    }

    @Override
    public boolean stepAheadSafe(GridPoint point, FacingDirection facingDirection)
    {
        Objects.requireNonNull(point);
        Objects.requireNonNull(facingDirection);
        return this.grid.inBound(facingDirection.getStepAheadGenerator().apply(point));
    }
}
