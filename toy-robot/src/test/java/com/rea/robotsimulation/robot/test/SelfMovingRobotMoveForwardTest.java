
package com.rea.robotsimulation.robot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.GridPoint;
import com.rea.robotsimulation.robot.RobotStepScanner;
import com.rea.robotsimulation.robot.SelfMovingRobot;

/**
 * This class contains test cases relevant for {@link SelfMovingRobot} move forward.
 */
public class SelfMovingRobotMoveForwardTest
{
    private SelfMovingRobot notPlacedRobot;

    private SelfMovingRobot northFacingPlacedRobot;
    private SelfMovingRobot eastFasingPlacedRobot;

    private RobotStepScanner scanner;

    @Before
    public void setUp()
    {
        // mock the scanner so we can mock the safe scanning access.
        scanner = Mockito.mock(RobotStepScanner.class);

        notPlacedRobot = new SelfMovingRobot(scanner);

        northFacingPlacedRobot = new SelfMovingRobot(scanner);
        northFacingPlacedRobot.setCurrentGridPoint(new GridPoint(0, 0));
        northFacingPlacedRobot.setFacingDirection(FacingDirection.NORTH);
        eastFasingPlacedRobot = new SelfMovingRobot(scanner);
        eastFasingPlacedRobot.setCurrentGridPoint(new GridPoint(3, 4));
        eastFasingPlacedRobot.setFacingDirection(FacingDirection.EAST);
    }

    @Test
    public void moveForward_northFacingPlacedRobot()
    {
        // assert placed
        assertTrue(northFacingPlacedRobot.isPlaced());
        // mock safe to move
        Mockito.when(scanner.stepAheadSafe(GridPoint.of(0, 0), FacingDirection.NORTH))
                .thenReturn(true);

        // move forward should update the current Grid point of the robot by a one step
        northFacingPlacedRobot.move();
        assertEquals(GridPoint.of(0, 1), northFacingPlacedRobot.getCurrentGridPoint());
        Mockito.verify(scanner).stepAheadSafe(GridPoint.of(0, 0), FacingDirection.NORTH);
        assertTrue(northFacingPlacedRobot.isPlaced()); // still placed
    }

    @Test
    public void moveForward_northFacingPlacedRobot_whenNotSafe()
    {
        // assert placed
        assertTrue(northFacingPlacedRobot.isPlaced());
        // not safe to move
        Mockito.when(scanner.stepAheadSafe(GridPoint.of(0, 0), FacingDirection.NORTH))
                .thenReturn(false);

        // should not allow move forward if not safe
        northFacingPlacedRobot.move();
        assertEquals(GridPoint.of(0, 0), northFacingPlacedRobot.getCurrentGridPoint());
        Mockito.verify(scanner).stepAheadSafe(GridPoint.of(0, 0), FacingDirection.NORTH);
        assertTrue(northFacingPlacedRobot.isPlaced()); // still placed
    }

    @Test
    public void moveForward_eastFacingPlacedRobot()
    {
        // assert placed
        assertTrue(eastFasingPlacedRobot.isPlaced());
        // mock safe to move
        Mockito.when(scanner.stepAheadSafe(GridPoint.of(3, 4), FacingDirection.EAST))
                .thenReturn(true);

        // move forward should update the current Grid point of the robot by a one step
        eastFasingPlacedRobot.move();
        assertEquals(new GridPoint(4, 4), eastFasingPlacedRobot.getCurrentGridPoint());
        Mockito.verify(scanner).stepAheadSafe(GridPoint.of(3, 4), FacingDirection.EAST);
        assertTrue(eastFasingPlacedRobot.isPlaced()); // still placed
    }

    @Test
    public void moveForward_eastFacingPlacedRobot_whenNotSafe()
    {
        // assert placed
        assertTrue(eastFasingPlacedRobot.isPlaced());
        // not safe to move
        Mockito.when(scanner.stepAheadSafe(GridPoint.of(3, 4), FacingDirection.EAST))
                .thenReturn(false);

        // should not allow move forward if not safe
        eastFasingPlacedRobot.move();
        assertEquals(new GridPoint(3, 4), eastFasingPlacedRobot.getCurrentGridPoint());
        Mockito.verify(scanner).stepAheadSafe(GridPoint.of(3, 4), FacingDirection.EAST);
        assertTrue(eastFasingPlacedRobot.isPlaced()); // still placed
    }

    @Test
    public void moveForward_notPlacedRobot()
    {
        // assert not placed
        assertFalse(notPlacedRobot.isPlaced());
        notPlacedRobot.move();
        assertNull(notPlacedRobot.getCurrentGridPoint());
        assertFalse(notPlacedRobot.isPlaced()); // no change for the placed state
    }
}
