
package com.rea.robotsimulation.command.test;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rea.robotsimulation.command.RightCommand;
import com.rea.robotsimulation.robot.Robot;

/**
 * This class contains test cases for {@link RightCommand}.
 *
 */
public class RightCommandTest
{
    private Robot robot;
    private RightCommand command;

    @Before
    public void setUp()
    {
        command = new RightCommand();
        robot = Mockito.mock(Robot.class);
    }

    @Test
    public void test_execute()
    {
        // when execute we expect Robot's rotateRight() method to be invoked.
        Mockito.doNothing().when(robot).rotateRight();
        command.execute(robot, Optional.empty());
        Mockito.verify(robot).rotateRight();
    }
}
