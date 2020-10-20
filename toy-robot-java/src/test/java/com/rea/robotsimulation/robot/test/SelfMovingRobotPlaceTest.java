
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
import com.rea.robotsimulation.robot.RobotGridScanner;
import com.rea.robotsimulation.robot.SelfMovingRobot;

/**
 * This class contains test cases for {@link SelfMovingRobot} related to placing a {@code Robot}.
 *
 */
public class SelfMovingRobotPlaceTest
{
    private SelfMovingRobot notPlacedRobot;
    private RobotGridScanner scanner;

    @Before
    public void setUp()
    {
        scanner = Mockito.mock(RobotGridScanner.class);
        notPlacedRobot = new SelfMovingRobot(scanner);
    }

    @Test
    public void place_withNullPoint()
    {
        assertFalse(notPlacedRobot.isPlaced());
        notPlacedRobot.place(null, FacingDirection.NORTH);
        assertNull(notPlacedRobot.getCurrentGridPoint());
        assertNull(notPlacedRobot.getFacingDirection());
    }

    @Test
    public void place_withNullFacingDirection()
    {
        assertFalse(notPlacedRobot.isPlaced());
        notPlacedRobot.place(GridPoint.of(0, 0), null);
        assertNull(notPlacedRobot.getCurrentGridPoint());
        assertNull(notPlacedRobot.getFacingDirection());
    }

    @Test
    public void place_forInBoundPoint()
    {
        assertFalse(notPlacedRobot.isPlaced());
        Mockito.when(scanner.pointExist(GridPoint.of(0, 0))).thenReturn(true);
        notPlacedRobot.place(GridPoint.of(0, 0), FacingDirection.NORTH);
        Mockito.verify(scanner).pointExist(GridPoint.of(0, 0));
        assertEquals(GridPoint.of(0, 0), notPlacedRobot.getCurrentGridPoint());
        assertEquals(FacingDirection.NORTH, notPlacedRobot.getFacingDirection());
        assertTrue(notPlacedRobot.isPlaced()); // should be placed now
    }

    @Test
    public void place_forOutBoundPoint()
    {
        assertFalse(notPlacedRobot.isPlaced());
        Mockito.when(scanner.pointExist(GridPoint.of(0, 8))).thenReturn(false);
        notPlacedRobot.place(GridPoint.of(0, 8), FacingDirection.NORTH);
        Mockito.verify(scanner).pointExist(GridPoint.of(0, 8));
        assertNull(notPlacedRobot.getCurrentGridPoint());
        assertNull(notPlacedRobot.getFacingDirection());
        assertFalse(notPlacedRobot.isPlaced()); // still not placed
    }
}
