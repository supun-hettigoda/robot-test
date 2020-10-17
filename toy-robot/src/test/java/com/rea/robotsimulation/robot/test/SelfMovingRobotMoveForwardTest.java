
package com.rea.robotsimulation.robot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.GridPoint;
import com.rea.robotsimulation.robot.SelfMovingRobot;

/**
 * This class contains test cases relevant for {@link SelfMovingRobot} move forward.
 */
public class SelfMovingRobotMoveForwardTest
{
    private SelfMovingRobot notPlacedRobot;

    private SelfMovingRobot northFacingPlacedRobot;
    private SelfMovingRobot eastFasingPlacedRobot;

    @Before
    public void setUp()
    {
        notPlacedRobot = new SelfMovingRobot();

        northFacingPlacedRobot = new SelfMovingRobot();
        northFacingPlacedRobot.setCurrentGridPoint(new GridPoint(0, 0));
        northFacingPlacedRobot.setFacingDirection(FacingDirection.NORTH);
        eastFasingPlacedRobot = new SelfMovingRobot();
        eastFasingPlacedRobot.setCurrentGridPoint(new GridPoint(3, 4));
        eastFasingPlacedRobot.setFacingDirection(FacingDirection.EAST);
    }

    @Test
    public void moveForward_northFacingPlacedRobot()
    {
        // assert placed
        assertTrue(northFacingPlacedRobot.isPlaced());
        // move forward should update the current Grid point of the robot by a one step
        northFacingPlacedRobot.move();
        assertEquals(new GridPoint(0, 1), northFacingPlacedRobot.getCurrentGridPoint());
        assertTrue(northFacingPlacedRobot.isPlaced()); // still placed
    }

    @Test
    public void moveForward_eastFacingPlacedRobot()
    {
        // assert placed
        assertTrue(eastFasingPlacedRobot.isPlaced());
        // move forward should update the current Grid point of the robot by a one step
        eastFasingPlacedRobot.move();
        assertEquals(new GridPoint(4, 4), eastFasingPlacedRobot.getCurrentGridPoint());
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
