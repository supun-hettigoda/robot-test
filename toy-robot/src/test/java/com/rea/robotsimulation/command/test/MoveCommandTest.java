
package com.rea.robotsimulation.command.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rea.robotsimulation.command.MoveCommand;
import com.rea.robotsimulation.robot.Robot;

/**
 * This class contains test cases for {@link MoveCommand}.
 *
 */
public class MoveCommandTest
{
    private Robot robot;
    private MoveCommand command;

    @Before
    public void setUp()
    {
        command = new MoveCommand();
        robot = Mockito.mock(Robot.class);
    }

    @Test
    public void test_execute()
    {
        // when execute we expect Robot's move() method to be invoked.
        Mockito.doNothing().when(robot).move();
        command.execute(robot);
        Mockito.verify(robot).move();
    }
}