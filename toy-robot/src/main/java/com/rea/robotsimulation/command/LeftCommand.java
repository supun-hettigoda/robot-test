
package com.rea.robotsimulation.command;

import com.rea.robotsimulation.robot.Robot;

/**
 * Executable command to rotate the {@code Robot} left.
 *
 */
public class LeftCommand implements ExecutableRobotCommand
{
    @Override
    public void execute(Robot robot)
    {
        robot.rotateLeft();
    }
}
