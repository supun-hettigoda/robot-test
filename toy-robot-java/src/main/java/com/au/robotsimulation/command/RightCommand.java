
package com.au.robotsimulation.command;

import java.util.Optional;
import java.util.function.Consumer;

import com.au.robotsimulation.robot.Robot;

/**
 * Executable command to rotate the {@code Robot} right.
 *
 */
public class RightCommand implements ExecutableRobotCommand
{
    @Override
    public void execute(Robot robot, Optional<Consumer<String>> commandExecutionStateReporter)
    {
        robot.rotateRight();
    }
}
