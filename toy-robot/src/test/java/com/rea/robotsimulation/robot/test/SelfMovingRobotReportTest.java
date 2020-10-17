
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
import com.rea.robotsimulation.robot.RobotStepScanner;
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
        SelfMovingRobot notPlaced = new SelfMovingRobot(Mockito.mock(RobotStepScanner.class));
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
        SelfMovingRobot robot_1 = new SelfMovingRobot(Mockito.mock(RobotStepScanner.class));
        robot_1.setCurrentGridPoint(new GridPoint(0, 0));
        robot_1.setFacingDirection(FacingDirection.NORTH);

        SelfMovingRobot robot_2 = new SelfMovingRobot(Mockito.mock(RobotStepScanner.class));
        robot_2.setCurrentGridPoint(new GridPoint(2, 3));
        robot_2.setFacingDirection(FacingDirection.EAST);

        SelfMovingRobot robot_3 = new SelfMovingRobot(Mockito.mock(RobotStepScanner.class));
        robot_3.setCurrentGridPoint(new GridPoint(5, 7));
        robot_3.setFacingDirection(FacingDirection.SOUTH);

        SelfMovingRobot robot_4 = new SelfMovingRobot(Mockito.mock(RobotStepScanner.class));
        robot_4.setCurrentGridPoint(new GridPoint(6, 7));
        robot_4.setFacingDirection(FacingDirection.WEST);

        return Arrays.asList(
                Pair.of(robot_1, "0,0,NORTH"),
                Pair.of(robot_2, "2,3,EAST"),
                Pair.of(robot_3, "5,7,SOUTH"),
                Pair.of(robot_4, "6,7,WEST"));
    }
}
