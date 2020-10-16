
package com.rea.robotsimulation.robot.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.rea.robotsimulation.grid.GridPoint;
import com.rea.robotsimulation.robot.RobotTableTop;

/**
 * This class contains unit tests for {@link RobotTableTop}.
 */
@RunWith(Theories.class)
public class RobotTableTopTest
{
    @Theory
    public void
            standardTableTop_inBound(@FromDataPoints("standardTableInBound") GridPoint gridPoint)
    {
        // 5,5 table top
        RobotTableTop tableTop = new RobotTableTop(5, 5);
        assertTrue(tableTop.inBound(gridPoint));
    }

    @Theory
    public void biggerTableTop_inBound(@FromDataPoints("biggerTableInBound") GridPoint gridPoint)
    {
        // 10,10 table top
        RobotTableTop tableTop = new RobotTableTop(10, 10);
        assertTrue(tableTop.inBound(gridPoint));
    }

    @Theory
    public void
            standardTableTop_outBound(@FromDataPoints("standardTableOutBound") GridPoint gridPoint)
    {
        // 5,5 table top
        RobotTableTop tableTop = new RobotTableTop(5, 5);
        assertFalse(tableTop.inBound(gridPoint));
    }

    @Theory
    public void biggerTableTop_outBound(@FromDataPoints("biggerTableOutBound") GridPoint gridPoint)
    {
        // 10,10 table top
        RobotTableTop tableTop = new RobotTableTop(10, 10);
        assertFalse(tableTop.inBound(gridPoint));
    }

    @DataPoints("standardTableInBound")
    public static List<GridPoint> standardTableInBoundData()
    {
        return Arrays.asList(new GridPoint(4, 4), new GridPoint(0, 0), new GridPoint(2, 3));
    }

    @DataPoints("standardTableOutBound")
    public static List<GridPoint> standardTableOutBoundData()
    {
        return Arrays.asList(new GridPoint(5, 1), new GridPoint(1, 5), new GridPoint(7, 0));
    }

    @DataPoints("biggerTableInBound")
    public static List<GridPoint> biggerTableInBoundData()
    {
        return Arrays.asList(new GridPoint(9, 9), new GridPoint(0, 0), new GridPoint(2, 3));
    }

    @DataPoints("biggerTableOutBound")
    public static List<GridPoint> biggerTableOutBoundData()
    {
        return Arrays.asList(new GridPoint(10, 10), new GridPoint(0, 12), new GridPoint(11, 0));
    }
}
