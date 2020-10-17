
package com.rea.robotsimulation.command;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.GridPoint;
import com.rea.robotsimulation.robot.Robot;

/**
 * Executable command to place the {@code Robot}.
 *
 */
public class PlaceCommand implements ExecutableRobotCommand
{
    private final GridPoint pointToPlace;
    private final FacingDirection facingDirectionToPlace;

    public PlaceCommand(GridPoint pointToPlace, FacingDirection facingDirectionToPlace)
    {
        super();
        this.pointToPlace = pointToPlace;
        this.facingDirectionToPlace = facingDirectionToPlace;
    }

    @Override
    public void execute(Robot robot)
    {
        // updat t
        robot.setCurrentGridPoint(pointToPlace);
        robot.setFacingDirection(facingDirectionToPlace);
    }
}
