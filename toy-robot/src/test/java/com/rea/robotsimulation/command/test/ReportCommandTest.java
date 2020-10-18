
package com.rea.robotsimulation.command.test;

import java.util.Optional;
import java.util.function.Consumer;

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
    private Consumer<String> reporter;

    // We can't help with generic level mocking is not supported.
    @SuppressWarnings("unchecked")
    @Before
    public void setUp()
    {
        command = new ReportCommand();
        robot = Mockito.mock(Robot.class);
        reporter = Mockito.mock(Consumer.class);
    }

    @Test
    public void test_execute()
    {
        // when execute we expect Robot's rotateLeft() method to be invoked.
        Mockito.when(robot.report()).thenReturn(Optional.of("My current position is awesome"));
        // here we expect the command execution result reported to the mock reporter.
        Mockito.doNothing().when(reporter).accept("My current position is awesome");

        // actual call
        command.execute(robot, Optional.of(reporter));

        // verify mock expectations
        Mockito.verify(robot).report();
        Mockito.verify(reporter).accept("My current position is awesome");
    }

    @Test
    public void test_executeAndIgnored()
    {
        // when execute we expect Robot's rotateLeft() method to be invoked.
        Mockito.when(robot.report()).thenReturn(Optional.empty());
        // ignored command wont return any state,
        // so we don't expect any result reported to the mock reporter.

        // actual call
        command.execute(robot, Optional.of(reporter));

        // verify mock expectations
        Mockito.verify(robot).report();
    }
}
