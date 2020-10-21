
package com.au.robotsimulation.command;

import java.util.Optional;
import java.util.function.Consumer;

import com.au.robotsimulation.robot.Robot;

/**
 * This interface defines a command which can be executed against a {@code Robot}.
 *
 */
public interface ExecutableRobotCommand
{
    /**
     * execute the command on the given {@code Robot}, and report the state of the execution if the
     * reporter is present.
     */
    public void execute(Robot robot, Optional<Consumer<String>> commandExecutionStateReporter);
}
