
package com.au.robotsimulation.robot;

import com.au.robotsimulation.grid.Grid;

/**
 * This class define a {@code Grid} based play area where a Robot can move.
 */
public class RobotTableTop implements Grid
{
    private final int columns;
    private final int rows;

    public RobotTableTop(int columns, int rows)
    {
        super();
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public int getNumberOfColumns()
    {
        return this.columns;
    }

    @Override
    public int getNumberOfRows()
    {
        return this.rows;
    }
}
