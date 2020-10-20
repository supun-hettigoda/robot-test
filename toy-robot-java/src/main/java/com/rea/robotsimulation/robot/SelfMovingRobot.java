
package com.rea.robotsimulation.robot;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.GridPoint;

/**
 * This Robot can move itself freely and safely on top of a {@code Grid}.
 * <p>
 * use the {@link #place(GridPoint, FacingDirection)} command to place this robot on a Grid. Once
 * placed it will be able to move anywhere on the grid or rotate left or right.
 * </p>
 *
 * <p>
 * This has following methods,
 * <ul>
 * <li>{@link #move()} - to safely move the robot one step forward.</li>
 * <li>{@link #rotateLeft()} - left rotate it one unit anti-clockwise.</li>
 * <li>{@link #rotateRight()} - to rotate it one unit clockwise.</li>
 * <li>{@link #report()} - to report it's current position n the grid.</li>
 * </ul>
 * </p>
 *
 *
 * <p>
 * It will use a {@code RobotGridScanner} to scan the safety of the movements. And it will ignore
 * any unsafe commands.
 * </p>
 *
 * one step ahead and rotate 90 degrees either clockwise or anti-clockwise.
 *
 * @see RobotGridScanner
 */
public class SelfMovingRobot implements Robot
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SelfMovingRobot.class.getName());

    // current position of the robot
    private GridPoint currentGridPoint;

    // which direction the robot currently facing
    private FacingDirection facingDirection;

    // scanner for step ahead safety check
    private final RobotGridScanner scanner;

    public SelfMovingRobot(RobotGridScanner scanner)
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
    public void place(GridPoint point, FacingDirection facingDirection)
    {
        LOGGER.debug("Place instruction received.");
        if (!Objects.isNull(point) && !Objects.isNull(facingDirection)
                && this.scanner.pointExist(point))
        {
            this.currentGridPoint = point;
            this.facingDirection = facingDirection;
            LOGGER.info(
                    "Robot placed on the [" + point.toString() + "] "
                            + this.facingDirection.name());
        }
    }

    @Override
    public void move()
    {
        LOGGER.debug("Move forward instruction received.");
        if (isPlaced())
        {
            // Robot is placed.
            // Check if it is safe to move.
            if (!this.scanner.stepAheadSafe(this.currentGridPoint, this.facingDirection))
            {
                LOGGER.debug("move request ignored, not safe to move.");
                return;
            }

            this.currentGridPoint =
                    this.facingDirection.getStepAheadGenerator().apply(this.currentGridPoint);
            LOGGER.info(
                    "Moving forward to new position [{},{}]",
                    this.currentGridPoint.getX(),
                    this.currentGridPoint.getY());
        }
    }

    @Override
    public void rotateLeft()
    {
        LOGGER.debug("Turn left instruction received.");
        if (isPlaced())
        {
            this.facingDirection = this.facingDirection.toLeft();
            LOGGER.info("New facing direction []", this.facingDirection);
        }
    }

    @Override
    public void rotateRight()
    {
        LOGGER.debug("Turn right instruction received.");
        if (isPlaced())
        {
            this.facingDirection = this.facingDirection.toRight();
            LOGGER.info("New facing direction []", this.facingDirection);
        }
    }

    @Override
    public Optional<String> report()
    {
        LOGGER.debug("Report instruction received.");
        if (isPlaced())
        {
            String state = String.format(
                    "%d,%d,%s",
                    this.currentGridPoint.getX(),
                    this.currentGridPoint.getY(),
                    this.facingDirection.name());
            LOGGER.info("Reporting the state [" + state + "]");
            return Optional.of(state);
        }

        return Optional.empty();
    }
}
