
package com.rea.robotsimulation.command;

import java.util.Objects;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.GridPoint;
import com.rea.robotsimulation.robot.Robot;

/**
 * Executable command to place the {@code Robot} on a grid.
 *
 */
public class PlaceCommand implements ExecutableRobotCommand
{
    private final GridPoint pointToPlace;
    private final FacingDirection facingDirectionToPlace;

    public PlaceCommand(GridPoint pointToPlace, FacingDirection facingDirectionToPlace)
    {
        super();
        Objects.requireNonNull(pointToPlace);
        Objects.requireNonNull(facingDirectionToPlace);
        this.pointToPlace = pointToPlace;
        this.facingDirectionToPlace = facingDirectionToPlace;
    }

    @Override
    public void execute(Robot robot)
    {
        robot.place(pointToPlace, facingDirectionToPlace);
    }
}
