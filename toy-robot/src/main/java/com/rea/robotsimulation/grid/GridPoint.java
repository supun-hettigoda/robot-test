
package com.rea.robotsimulation.grid;

/**
 * This class define a accessible point in a Grid.
 *
 */
public class GridPoint
{
    // x axis position (horizontal)
    int x;

    // x axis position (vertical)
    int y;

    public GridPoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the column index which this point is located on. It will always be a positive integer
     *         with 0 as the starting column index.
     */
    public int getX()
    {
        return this.x;
    }

    /**
     * @return the row index which this point is located on. It will always be a positive integer
     *         with 0 as the starting row index.
     */
    public int getY()
    {
        return this.y;
    }
}
