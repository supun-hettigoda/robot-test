
package com.rea.robotsimulation.simulator.test;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rea.robotsimulation.robot.OneStepAheadGridScanner;
import com.rea.robotsimulation.robot.RobotTableTop;
import com.rea.robotsimulation.robot.SelfMovingRobot;
import com.rea.robotsimulation.simulator.InputCommandProvider;
import com.rea.robotsimulation.simulator.RobotSimulator;

/**
 * This class contains Acceptance test scenarios for a {@link SelfMovingRobot} move on a Standard
 * table top.
 *
 */
public class SelfMovingRobotSimulationAcceptanceTest
{
    private RobotSimulator simulator;
    private InputCommandProvider commandProvider;
    private Consumer<String> reporter;
    private SelfMovingRobot robot;

    @SuppressWarnings("unchecked") // Can't resolve the generics when mocking.
    @Before
    public void setUp()
    {
        commandProvider = Mockito.mock(InputCommandProvider.class);
        reporter = Mockito.mock(Consumer.class);
        simulator = new RobotSimulator(commandProvider, reporter);
        robot = new SelfMovingRobot(new OneStepAheadGridScanner(new RobotTableTop(5, 5)));
    }

    @Test
    public void test_oneMovement()
    {
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of("PLACE 0,0,NORTH"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("REPORT"))
                .thenReturn(Optional.empty());
        // @formatter:on

        simulator.start(robot);

        Mockito.verify(reporter).accept("0,1,NORTH");
        Mockito.verify(reporter).accept("No more commands shutting down...");
    }

    @Test
    public void test_ignoreEmpties()
    {
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of(""))
                .thenReturn(Optional.of(" "))
                .thenReturn(Optional.of("\t"))
                .thenReturn(Optional.of("\r"))
                .thenReturn(Optional.of("\n"))
                .thenReturn(Optional.of("PLACE 0,0,NORTH"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("REPORT"))
                .thenReturn(Optional.empty());
        // @formatter:on

        simulator.start(robot);

        Mockito.verify(reporter).accept("0,1,NORTH");
        Mockito.verify(reporter).accept("No more commands shutting down...");
    }

    @Test
    public void test_ignore_untilPlaced() throws IOException
    {
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of(""))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("RIGHT"))
                .thenReturn(Optional.of("LEFT"))
                .thenReturn(Optional.of("PLACE -1,0,NORTH")) // not a valid PLCE
                .thenReturn(Optional.of("REPORT"))
                .thenReturn(Optional.of("PLACE 0,0,NORTH"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("REPORT"))
                .thenReturn(Optional.empty());
        // @formatter:on

        simulator.start(robot);

        Mockito.verify(reporter).accept("0,1,NORTH");
        Mockito.verify(reporter).accept("No more commands shutting down...");
    }

    @Test
    public void test_move_turnRight() throws IOException
    {
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of("PLACE 2,2,NORTH"))
                .thenReturn(Optional.of("MOVE")) // 2,3,NORTH
                .thenReturn(Optional.of("RIGHT"))// EAST
                .thenReturn(Optional.of("RIGHT"))// SOUTH
                .thenReturn(Optional.of("REPORT"))
                .thenReturn(Optional.empty());
        // @formatter:on

        simulator.start(robot);

        Mockito.verify(reporter).accept("2,3,SOUTH");
        Mockito.verify(reporter).accept("No more commands shutting down...");
    }

    @Test
    public void test_move_turnLeft() throws IOException
    {
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of("PLACE 0,0,NORTH"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("LEFT"))
                .thenReturn(Optional.of("LEFT"))
                .thenReturn(Optional.of("LEFT"))
                .thenReturn(Optional.of("REPORT"))
                .thenReturn(Optional.empty());
        // @formatter:on

        simulator.start(robot);

        Mockito.verify(reporter).accept("0,1,EAST");
        Mockito.verify(reporter).accept("No more commands shutting down...");
    }

    @Test
    public void robot_ignoreFallingOffs() throws IOException
    {
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of("PLACE 0,0,NORTH"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("REPORT")) // 0,4,NORTH
                .thenReturn(Optional.of("RIGHT")) // turn to EAST and keep moving
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("REPORT")) // 4,4,EAST
                .thenReturn(Optional.of("LEFT")) // 4,4,NORTH
                .thenReturn(Optional.of("MOVE")) // try step
                .thenReturn(Optional.of("REPORT")) // 4,4,NORTH
                .thenReturn(Optional.empty());
        // @formatter:on

        simulator.start(robot);

        Mockito.verify(reporter).accept("0,4,NORTH");
        Mockito.verify(reporter).accept("4,4,EAST");
        Mockito.verify(reporter).accept("4,4,NORTH");
        Mockito.verify(reporter).accept("No more commands shutting down...");
    }

    @Test
    public void test_randomMoving() throws IOException
    {
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of("PLACE 2,1,NORTH"))
                .thenReturn(Optional.of("MOVE")) // 2,2,NORTH
                .thenReturn(Optional.of("LEFT")) // WEST
                .thenReturn(Optional.of("LEFT")) // SOUTH
                .thenReturn(Optional.of("LEFT")) // EAST
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("REPORT")) // 3,2,EAST
                .thenReturn(Optional.of("MOVE")) // 4,2,EAST
                .thenReturn(Optional.of("REPORT"))
                .thenReturn(Optional.empty());
        // @formatter:on

        simulator.start(robot);

        Mockito.verify(reporter).accept("3,2,EAST");
        Mockito.verify(reporter).accept("4,2,EAST");
        Mockito.verify(reporter).accept("No more commands shutting down...");

    }

    @Test
    public void test_randomMultiplePlaceCommands() throws IOException
    {
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of("PLACE 2,1,NORTH"))
                .thenReturn(Optional.of("REPORT")) // 2,1,NORTH
                .thenReturn(Optional.of("PLACE 2,4,WEST"))
                .thenReturn(Optional.of("REPORT")) // 2,4,WEST
                .thenReturn(Optional.of("LEFT")) // SOUTH
                .thenReturn(Optional.of("MOVE"))
                .thenReturn(Optional.of("REPORT")) // 2,3,SOUTH
                .thenReturn(Optional.of("PLACE 0,0,EAST"))
                .thenReturn(Optional.of("REPORT")) // 0,0,EAST
                .thenReturn(Optional.empty());
        // @formatter:on

        simulator.start(robot);

        Mockito.verify(reporter).accept("2,1,NORTH");
        Mockito.verify(reporter).accept("2,4,WEST");
        Mockito.verify(reporter).accept("2,3,SOUTH");
        Mockito.verify(reporter).accept("0,0,EAST");
        Mockito.verify(reporter).accept("No more commands shutting down...");
    }

    @Test
    public void test_ignoreOutBoundPlacing() throws IOException
    {
        // @formatter:off
        Mockito.when(commandProvider.nextCommand())
                .thenReturn(Optional.of("PLACE 5,5,NORTH"))// out bound
                .thenReturn(Optional.of("REPORT")) // ignored
                .thenReturn(Optional.of("PLACE 0,0,WEST"))
                .thenReturn(Optional.of("REPORT")) // 0,0,WEST
                .thenReturn(Optional.of("PLACE -1,3,WEST")) // out bound ignored
                .thenReturn(Optional.of("REPORT")) // 0,0,WEST
                .thenReturn(Optional.of("REPORT")) // 0,0,WEST
                .thenReturn(Optional.of("PLACE 0,5,EAST")) // out bound ignored
                .thenReturn(Optional.of("MOVE")) // ignored
                .thenReturn(Optional.of("LEFT")) // SOUTH
                .thenReturn(Optional.of("LEFT")) // EAST
                .thenReturn(Optional.of("MOVE")) // 1,0,EAST
                .thenReturn(Optional.of("REPORT")) // 1,0,EAST
                .thenReturn(Optional.empty());
        // @formatter:on

        simulator.start(robot);

        Mockito.verify(reporter, Mockito.times(3)).accept("0,0,WEST");
        Mockito.verify(reporter).accept("1,0,EAST");
        Mockito.verify(reporter).accept("No more commands shutting down...");
    }
}
