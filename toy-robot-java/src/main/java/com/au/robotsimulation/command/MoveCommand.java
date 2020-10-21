
package com.au.robotsimulation.command;

import java.util.Optional;
import java.util.function.Consumer;

import com.au.robotsimulation.robot.Robot;

/**
 * Executable command to move the {@code Robot} towards facing direction.
 *
 */
public class MoveCommand implements ExecutableRobotCommand
{
    @Override
    public void execute(Robot robot, Optional<Consumer<String>> commandExecutionStateReporter)
    {
        robot.move();
    }
}
