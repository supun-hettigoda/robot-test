
package com.rea.robotsimulation.command;

import com.rea.robotsimulation.robot.Robot;

/**
 * This interface defines a command which can be executed against a {@code Robot}.
 *
 */
public interface ExecutableRobotCommand
{
    /**
     * execute the command to the given {@code Robot}.
     */
    public void execute(Robot robot);
}
