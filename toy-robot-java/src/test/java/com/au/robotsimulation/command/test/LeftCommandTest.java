
package com.au.robotsimulation.command.test;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.au.robotsimulation.command.LeftCommand;
import com.au.robotsimulation.robot.Robot;

/**
 * This class contains test cases for {@link LeftCommand}.
 *
 */
public class LeftCommandTest
{
    private Robot robot;
    private LeftCommand command;

    @Before
    public void setUp()
    {
        command = new LeftCommand();
        robot = Mockito.mock(Robot.class);
    }

    @Test
    public void test_execute()
    {
        // when execute we expect Robot's rotateLeft() method to be invoked.
        Mockito.doNothing().when(robot).rotateLeft();
        command.execute(robot, Optional.empty());
        Mockito.verify(robot).rotateLeft();
    }
}
