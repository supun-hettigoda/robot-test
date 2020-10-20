
package com.rea.robotsimulation.robot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.GridPoint;
import com.rea.robotsimulation.robot.RobotGridScanner;
import com.rea.robotsimulation.robot.SelfMovingRobot;
import com.rea.robotsimulation.util.Pair;

/**
 * This class contains test cases relevant for {@link SelfMovingRobot} reporting current state.
 */
@RunWith(Theories.class)
public class SelfMovingRobotReportTest
{
    @Test
    public void report_whenNotYetPlaced()
    {
        SelfMovingRobot notPlaced = new SelfMovingRobot(Mockito.mock(RobotGridScanner.class));
        assertFalse(notPlaced.report().isPresent());
    }

    @Theory
    public void report_whenPlaced(
            @FromDataPoints("reportingTestData") Pair<SelfMovingRobot, String> testdata)
    {
        assertEquals(testdata.getSecond(), testdata.getFirst().report().get());
    }

    @DataPoints("reportingTestData")
    public static List<Pair<SelfMovingRobot, String>> reportingTestData()
    {
        RobotGridScanner scanner = Mockito.mock(RobotGridScanner.class);

        SelfMovingRobot robot_1 = new SelfMovingRobot(scanner);
        Mockito.when(scanner.pointExist(GridPoint.of(0, 0))).thenReturn(true);
        robot_1.place(GridPoint.of(0, 0), FacingDirection.NORTH);

        SelfMovingRobot robot_2 = new SelfMovingRobot(scanner);
        Mockito.when(scanner.pointExist(GridPoint.of(2, 3))).thenReturn(true);
        robot_2.place(GridPoint.of(2, 3), FacingDirection.EAST);

        SelfMovingRobot robot_3 = new SelfMovingRobot(scanner);
        Mockito.when(scanner.pointExist(GridPoint.of(5, 7))).thenReturn(true);
        robot_3.place(GridPoint.of(5, 7), FacingDirection.SOUTH);

        SelfMovingRobot robot_4 = new SelfMovingRobot(scanner);
        Mockito.when(scanner.pointExist(GridPoint.of(6, 7))).thenReturn(true);
        robot_4.place(GridPoint.of(6, 7), FacingDirection.WEST);

        return Arrays.asList(
                Pair.of(robot_1, "0,0,NORTH"),
                Pair.of(robot_2, "2,3,EAST"),
                Pair.of(robot_3, "5,7,SOUTH"),
                Pair.of(robot_4, "6,7,WEST"));
    }
}
