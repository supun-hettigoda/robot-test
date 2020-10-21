
package com.au.robotsimulation.command;

import java.util.Optional;

/**
 * This interface defines a command parser which can parse the given input to a particular
 * {@code ExecutableRobotCommand}.
 *
 */
public interface RobotCommandParser
{
    /**
     * @return a Optional with {@code ExecutableRobotCommand} if the given input can be successfully
     *         parse, empty if not.
     */
    public Optional<ExecutableRobotCommand> parse(String inputCommand);
}
