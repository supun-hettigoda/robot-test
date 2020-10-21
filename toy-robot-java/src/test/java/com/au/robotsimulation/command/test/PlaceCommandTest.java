
package com.au.robotsimulation.command.test;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.au.robotsimulation.command.PlaceCommand;
import com.au.robotsimulation.grid.FacingDirection;
import com.au.robotsimulation.grid.GridPoint;
import com.au.robotsimulation.robot.Robot;

/**
 * This class contains test cases for {@link PlaceCommand}.
 *
 */
public class PlaceCommandTest
{
    private Robot robot;
    private GridPoint point;
    private FacingDirection facingDirection;
    private PlaceCommand command;

    @Before
    public void setUp()
    {
        point = Mockito.mock(GridPoint.class);
        facingDirection = FacingDirection.SOUTH;
        robot = Mockito.mock(Robot.class);

        command = new PlaceCommand(point, facingDirection);
    }

    @Test
    public void test_execute()
    {
        // when execute we expect Robot's place() method to be invoked.
        Mockito.doNothing().when(robot).place(point, facingDirection);
        command.execute(robot, Optional.empty());
        Mockito.verify(robot).place(point, facingDirection);
    }
}
