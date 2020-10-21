
package com.au.robotsimulation.grid;

/**
 * This class define a accessible point in a Grid.
 */
public class GridPoint
{
    // x axis position (horizontal)
    private final int x;

    // x axis position (vertical)
    private final int y;

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

    /**
     * Convenience static method to create a new point by coordinates.
     */
    public static GridPoint of(int x, int y)
    {
        return new GridPoint(x, y);
    }

    @Override
    public String toString()
    {
        return "GridPoint [x=" + x + ", y=" + y + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        GridPoint other = (GridPoint) obj;
        if (x != other.x)
        {
            return false;
        }
        if (y != other.y)
        {
            return false;
        }
        return true;
    }
}
