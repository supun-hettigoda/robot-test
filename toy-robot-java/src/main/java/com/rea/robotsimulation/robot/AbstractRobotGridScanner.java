
package com.rea.robotsimulation.robot;

import java.util.Objects;

import com.rea.robotsimulation.grid.Grid;
import com.rea.robotsimulation.grid.GridPoint;

/**
 * This abstract implementation provide the basic behaviours of a {@code RobotGridScanner}.
 *
 */
public abstract class AbstractRobotGridScanner implements RobotGridScanner
{
    // the grid which this scanner can scan
    protected final Grid grid;

    public AbstractRobotGridScanner(Grid grid)
    {
        // scanner should be initialised with a null grid.
        Objects.requireNonNull(grid);
        this.grid = grid;
    }

    @Override
    public boolean pointExist(GridPoint point)
    {
        return this.grid.inBound(point);
    }
}
