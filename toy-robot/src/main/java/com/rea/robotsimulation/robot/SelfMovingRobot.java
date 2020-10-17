
package com.rea.robotsimulation.robot;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.GridPoint;

/**
 * This Robot can move one step ahead and rotate 90 degrees either clockwise or anti-clockwise.
 *
 * TODO - more java doc going forward.
 */
public class SelfMovingRobot implements Robot
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SelfMovingRobot.class.getName());

    // current position of the robot
    private GridPoint currentGridPoint;

    // which direction the robot currently facing
    private FacingDirection facingDirection;

    @Override
    public GridPoint getCurrentGridPoint()
    {
        return this.currentGridPoint;
    }

    @Override
    public FacingDirection getFacingDirection()
    {
        return this.facingDirection;
    }

    @Override
    public void setCurrentGridPoint(GridPoint currentGridPoint)
    {
        Objects.requireNonNull(currentGridPoint);
        this.currentGridPoint = currentGridPoint;
    }

    @Override
    public void setFacingDirection(FacingDirection facingDirection)
    {
        Objects.requireNonNull(facingDirection);
        this.facingDirection = facingDirection;
    }

    @Override
    public void move()
    {
        LOGGER.trace("Move forward instruction received.");
        // TODO report ignore
        if (isPlaced())
        {
            // TODO move forward safety check
            setCurrentGridPoint(
                    this.facingDirection.getStepAheadGenerator().apply(this.currentGridPoint));
            LOGGER.info(
                    "Moving forward to new position [{},{}]",
                    this.currentGridPoint.getX(),
                    this.currentGridPoint.getY());
        }
    }

    @Override
    public void rotateLeft()
    {
        LOGGER.trace("Turn left instruction received.");
        // TODO report ignore
        if (isPlaced())
        {
            setFacingDirection(this.facingDirection.toLeft());
            LOGGER.info("New facing direction []", this.facingDirection);
        }
    }

    @Override
    public void rotateRight()
    {
        LOGGER.trace("Turn right instruction received.");
        // TODO report ignore
        if (isPlaced())
        {
            setFacingDirection(this.facingDirection.toRight());
            LOGGER.info("New facing direction []", this.facingDirection);
        }
    }

    @Override
    public Optional<String> report()
    {
        LOGGER.trace("Report instruction received.");
        if (isPlaced())
        {
            LOGGER.info("Reporting the state.");
            return Optional.of(
                    String.format(
                            "%d,%d,%s",
                            this.currentGridPoint.getX(),
                            this.currentGridPoint.getY(),
                            this.facingDirection.name()));
        }

        return Optional.empty();
    }
}