
package com.rea.robotsimulation.command.test;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rea.robotsimulation.command.ReportCommand;
import com.rea.robotsimulation.robot.Robot;

/**
 * This class contains test cases for {@link ReportCommand}.
 *
 */
public class ReportCommandTest
{
    private Robot robot;
    private ReportCommand command;

    @Before
    public void setUp()
    {
        command = new ReportCommand();
        robot = Mockito.mock(Robot.class);
    }

    @Test
    public void test_execute()
    {
        // when execute we expect Robot's rotateLeft() method to be invoked.
        Mockito.when(robot.report()).thenReturn(Optional.empty());
        // TODO what to do with the report ?
        command.execute(robot);
        Mockito.verify(robot).report();
    }
}
