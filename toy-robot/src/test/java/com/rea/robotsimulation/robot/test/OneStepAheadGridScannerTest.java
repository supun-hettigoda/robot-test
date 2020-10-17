
package com.rea.robotsimulation.robot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.Grid;
import com.rea.robotsimulation.grid.GridPoint;
import com.rea.robotsimulation.robot.OneStepAheadGridScanner;
import com.rea.robotsimulation.robot.RobotTableTop;
import com.rea.robotsimulation.util.Pair;

/**
 * This class contains test cases for {@link OneStepAheadGridScanner}.
 *
 */
@RunWith(Theories.class)
public class OneStepAheadGridScannerTest
{
    private OneStepAheadGridScanner scanner;
    private Grid grid;

    @Before
    public void setUp()
    {
        grid = new RobotTableTop(5, 5);
        scanner = new OneStepAheadGridScanner(grid);
    }

    @Theory
    public void test_stepAheadSafe(
            @FromDataPoints("stepAheadSafeData") Pair<GridPoint, FacingDirection> testData)
    {
        assertTrue(scanner.stepAheadSafe(testData.getFirst(), testData.getSecond()));
    }

    @Theory
    public void test_stepAheadNotSafe(
            @FromDataPoints("stepAheadNotSafeData") Pair<GridPoint, FacingDirection> testData)
    {
        assertFalse(scanner.stepAheadSafe(testData.getFirst(), testData.getSecond()));
    }

    @Theory
    public void
            test_pointExist(@FromDataPoints("pointsExistData") Pair<GridPoint, Boolean> testData)
    {
        assertEquals(testData.getSecond(), scanner.pointExist(testData.getFirst()));
    }

    @DataPoints("stepAheadSafeData")
    public static List<Pair<GridPoint, FacingDirection>> stepAheadSafeData()
    {
        return Arrays.asList(
                Pair.of(GridPoint.of(0, 0), FacingDirection.NORTH),
                Pair.of(GridPoint.of(0, 0), FacingDirection.EAST),
                Pair.of(GridPoint.of(4, 4), FacingDirection.WEST),
                Pair.of(GridPoint.of(4, 4), FacingDirection.SOUTH),
                Pair.of(GridPoint.of(0, 4), FacingDirection.EAST),
                Pair.of(GridPoint.of(0, 4), FacingDirection.SOUTH),
                Pair.of(GridPoint.of(4, 0), FacingDirection.WEST),
                Pair.of(GridPoint.of(4, 0), FacingDirection.NORTH));

    }

    @DataPoints("stepAheadNotSafeData")
    public static List<Pair<GridPoint, FacingDirection>> stepAheadNotSafeData()
    {
        return Arrays.asList(
                Pair.of(GridPoint.of(0, 0), FacingDirection.SOUTH),
                Pair.of(GridPoint.of(0, 0), FacingDirection.WEST),
                Pair.of(GridPoint.of(4, 4), FacingDirection.EAST),
                Pair.of(GridPoint.of(4, 4), FacingDirection.NORTH),
                Pair.of(GridPoint.of(0, 4), FacingDirection.WEST),
                Pair.of(GridPoint.of(0, 4), FacingDirection.NORTH),
                Pair.of(GridPoint.of(4, 0), FacingDirection.EAST),
                Pair.of(GridPoint.of(4, 0), FacingDirection.SOUTH));
    }

    @DataPoints("pointsExistData")
    public static List<Pair<GridPoint, Boolean>> pointsExistData()
    {
        return Arrays.asList(
                Pair.of(GridPoint.of(0, 0), true),
                Pair.of(GridPoint.of(0, 4), true),
                Pair.of(GridPoint.of(4, 0), true),
                Pair.of(GridPoint.of(4, 4), true),
                Pair.of(GridPoint.of(0, 5), false),
                Pair.of(GridPoint.of(5, 4), false),
                Pair.of(GridPoint.of(-4, 0), false),
                Pair.of(GridPoint.of(4, -1), false));
    }
}
