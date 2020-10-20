
package com.rea.robotsimulation.grid.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.rea.robotsimulation.grid.FacingDirectionStepAheadService;
import com.rea.robotsimulation.grid.GridPoint;
import com.rea.robotsimulation.util.Pair;

/**
 * This class contains unit tests for the {@link FacingDirectionStepAheadService}.
 */
@RunWith(Theories.class)
public class FacingDirectionStepAheadServiceTest
{
    @Theory
    public void test_stepAheadNorth(
            @FromDataPoints("stepAheadNorthData") Pair<GridPoint, GridPoint> points)
    {
        assertEquals(
                points.getSecond(),
                FacingDirectionStepAheadService.stepAheadToNorth(points.getFirst()));
    }

    @Theory
    public void test_stepAheadEast(
            @FromDataPoints("stepAheadEastData") Pair<GridPoint, GridPoint> points)
    {
        assertEquals(
                points.getSecond(),
                FacingDirectionStepAheadService.stepAheadToEast(points.getFirst()));
    }

    @Theory
    public void test_stepAheadWest(
            @FromDataPoints("stepAheadWestData") Pair<GridPoint, GridPoint> points)
    {
        assertEquals(
                points.getSecond(),
                FacingDirectionStepAheadService.stepAheadToWest(points.getFirst()));
    }

    @Theory
    public void test_stepAheadSouth(
            @FromDataPoints("stepAheadSouthData") Pair<GridPoint, GridPoint> points)
    {
        assertEquals(
                points.getSecond(),
                FacingDirectionStepAheadService.stepAheadToSouth(points.getFirst()));
    }

    @DataPoints("stepAheadNorthData")
    public static List<Pair<GridPoint, GridPoint>> stepAheadNorthdata()
    {
        // step ahead to North should be x, y+1
        return Arrays.asList(
                Pair.of(new GridPoint(4, 4), new GridPoint(4, 5)),
                Pair.of(new GridPoint(6, 5), new GridPoint(6, 6)),
                Pair.of(new GridPoint(0, 0), new GridPoint(0, 1)));
    }

    @DataPoints("stepAheadEastData")
    public static List<Pair<GridPoint, GridPoint>> stepAheadEastData()
    {
        // step ahead to East should be x+1, y
        return Arrays.asList(
                Pair.of(new GridPoint(4, 4), new GridPoint(5, 4)),
                Pair.of(new GridPoint(6, 5), new GridPoint(7, 5)),
                Pair.of(new GridPoint(0, 0), new GridPoint(1, 0)));
    }

    @DataPoints("stepAheadWestData")
    public static List<Pair<GridPoint, GridPoint>> stepAheadWestData()
    {
        // step ahead to West should be x-1, y
        return Arrays.asList(
                Pair.of(new GridPoint(4, 4), new GridPoint(3, 4)),
                Pair.of(new GridPoint(6, 5), new GridPoint(5, 5)),
                Pair.of(new GridPoint(0, 0), new GridPoint(-1, 0)));
    }

    @DataPoints("stepAheadSouthData")
    public static List<Pair<GridPoint, GridPoint>> stepAheadSouthData()
    {
        // step ahead to East should be x, y-1
        return Arrays.asList(
                Pair.of(new GridPoint(4, 4), new GridPoint(4, 3)),
                Pair.of(new GridPoint(6, 5), new GridPoint(6, 4)),
                Pair.of(new GridPoint(0, 0), new GridPoint(0, -1)));
    }
}
