
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

    // scanner for step ahead safety check
    private final RobotStepScanner scanner;

    public SelfMovingRobot(RobotStepScanner scanner)
    {
        super();

        // self moving robot must have a scanner to safe check.
        Objects.requireNonNull(scanner);
        this.scanner = scanner;
    }

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
        if (isPlaced())
        {
            // Robot is placed.
            // Check if it safe to move.
            if (!this.scanner.stepAheadSafe(this.currentGridPoint, this.facingDirection))
            {
                // TODO report ignore
                LOGGER.info("Not safe to move.");
                return;
            }

            setCurrentGridPoint(
                    this.facingDirection.getStepAheadGenerator().apply(this.currentGridPoint));
            LOGGER.info(
                    "Moving forward to new position [{},{}]",
                    this.currentGridPoint.getX(),
                    this.currentGridPoint.getY());
        }
        // TODO report ignore
    }

    @Override
    public void rotateLeft()
    {
        LOGGER.trace("Turn left instruction received.");
        if (isPlaced())
        {
            setFacingDirection(this.facingDirection.toLeft());
            LOGGER.info("New facing direction []", this.facingDirection);
        }
        // TODO report ignore
    }

    @Override
    public void rotateRight()
    {
        LOGGER.trace("Turn right instruction received.");
        if (isPlaced())
        {
            setFacingDirection(this.facingDirection.toRight());
            LOGGER.info("New facing direction []", this.facingDirection);
        }
        // TODO report ignore
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
