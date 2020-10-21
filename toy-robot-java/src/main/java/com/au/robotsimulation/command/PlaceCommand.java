
package com.au.robotsimulation.command;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import com.au.robotsimulation.grid.FacingDirection;
import com.au.robotsimulation.grid.GridPoint;
import com.au.robotsimulation.robot.Robot;

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
    public void execute(Robot robot, Optional<Consumer<String>> commandExecutionStateReporter)
    {
        robot.place(pointToPlace, facingDirectionToPlace);
    }
}
