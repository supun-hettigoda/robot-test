
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
 * This class contains test cases relevant for {@link SelfMovingRobot} rotating moves.
 */
public class SelfMovingRobotRotateTest
{
    private SelfMovingRobot notPlacedRobot;
    private SelfMovingRobot placedRobot;

    @Before
    public void setUp()
    {
        notPlacedRobot = new SelfMovingRobot(Mockito.mock(RobotStepScanner.class));

        placedRobot = new SelfMovingRobot(Mockito.mock(RobotStepScanner.class));
        placedRobot.setCurrentGridPoint(new GridPoint(0, 0));
        placedRobot.setFacingDirection(FacingDirection.NORTH);
    }

    @Test
    public void rotateLeft_whenPlaced()
    {
        assertTrue(placedRobot.isPlaced());
        // when rotate left placedRobot's facing direction must be updated to the West.
        placedRobot.rotateLeft();
        assertEquals(FacingDirection.WEST, placedRobot.getFacingDirection());

        // try again for fun
        placedRobot.rotateLeft();
        assertEquals(FacingDirection.SOUTH, placedRobot.getFacingDirection());

        // Rotating a notPlacedRobot is fun isn't it ?
        placedRobot.rotateLeft();
        assertEquals(FacingDirection.EAST, placedRobot.getFacingDirection());
        assertTrue(placedRobot.isPlaced()); // still placed
    }

    @Test
    public void rotateLeft_whenNotYetPlaced()
    {
        assertFalse(notPlacedRobot.isPlaced());
        // assert not placed yet
        assertNull(notPlacedRobot.getFacingDirection());
        // try rotate
        notPlacedRobot.rotateLeft();
        // shouldn't make the robot to be placed
        assertNull(notPlacedRobot.getFacingDirection());

        // try again shouldn't break anything
        notPlacedRobot.rotateLeft();
        assertNull(notPlacedRobot.getFacingDirection());
        assertFalse(notPlacedRobot.isPlaced()); // still not placed
    }

    @Test
    public void rotateRight_whenPlaced()
    {
        assertTrue(placedRobot.isPlaced());
        // when rotate left placedRobot's facing direction must be updated to the West.
        placedRobot.rotateRight();
        assertEquals(FacingDirection.EAST, placedRobot.getFacingDirection());

        // try again for fun
        placedRobot.rotateRight();
        assertEquals(FacingDirection.SOUTH, placedRobot.getFacingDirection());

        // Rotating a notPlacedRobot is fun isn't it ?
        placedRobot.rotateRight();
        assertEquals(FacingDirection.WEST, placedRobot.getFacingDirection());
        assertTrue(placedRobot.isPlaced()); // still placed
    }

    @Test
    public void rotateRight_whenNotYetPlaced()
    {
        assertFalse(notPlacedRobot.isPlaced());
        // assert not placed yet
        assertNull(notPlacedRobot.getFacingDirection());
        // try rotate
        notPlacedRobot.rotateRight();
        // shouldn't make the robot to be placed
        assertNull(notPlacedRobot.getFacingDirection());

        // try again shouldn't break anything
        notPlacedRobot.rotateRight();
        assertNull(notPlacedRobot.getFacingDirection());
        assertFalse(notPlacedRobot.isPlaced()); // still not placed
    }
}
