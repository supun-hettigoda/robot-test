
package com.rea.robotsimulation.simulator.test;

import java.util.Optional;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rea.robotsimulation.robot.Robot;
import com.rea.robotsimulation.simulator.InputCommandProvider;
import com.rea.robotsimulation.simulator.RobotSimulator;

/**
 * This class contains test cases for {@link RobotSimulator}.
 *
 */
public class RobotSimulatorTest
{
    private RobotSimulator simulator;
    private InputCommandProvider commandProvider;
    private Consumer<String> reporter;

    @SuppressWarnings("unchecked") // Can't resolve the generics when mocking.
    @Before
    public void setUp()
    {
        commandProvider = Mockito.mock(InputCommandProvider.class);
        reporter = Mockito.mock(Consumer.class);
        simulator = new RobotSimulator(commandProvider, reporter);
    }

    @Test
    public void startedSimulatorNeedsToContinuouslyAcceptInputCommands()
    {
        // start against a mocked Robot since we want be testing actual commands
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of("INVALID-1"))
                .thenReturn(Optional.of("INVALID-2"))
                .thenReturn(Optional.of("INVALID-3"))
                .thenReturn(Optional.empty());
        // @formatter:on
        Mockito.doNothing().when(reporter).accept("No more commands shutting down...");

        simulator.start(Mockito.mock(Robot.class));

        // verify next command executed 4 times
        Mockito.verify(commandProvider, Mockito.times(4)).nextCommand();
    }
}
