
package com.rea.robotsimulation.robot;

import java.util.Objects;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.Grid;
import com.rea.robotsimulation.grid.GridPoint;

/**
 * This {@code RobotStepScanner} can scan a one step ahead on the {@code Grid}.
 *
 */
public class OneStepAheadGridScanner implements RobotStepScanner
{
    // the grid which this scanner can scan
    private final Grid grid;

    public OneStepAheadGridScanner(Grid grid)
    {
        super();

        // scanner should be initialised with a null grid.
        Objects.requireNonNull(grid);
        this.grid = grid;
    }

    @Override
    public boolean stepAheadSafe(GridPoint point, FacingDirection facingDirection)
    {
        Objects.requireNonNull(point);
        Objects.requireNonNull(facingDirection);
        return this.grid.inBound(facingDirection.getStepAheadGenerator().apply(point));
    }
}
