
package com.rea.robotsimulation.command;

import com.rea.robotsimulation.robot.Robot;

/**
 * Executable command to rotate the {@code Robot} right.
 *
 */
public class RightCommand implements ExecutableRobotCommand
{
    @Override
    public void execute(Robot robot)
    {
        robot.rotateRight();
    }
}
