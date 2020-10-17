
package com.rea.robotsimulation.command;

import com.rea.robotsimulation.robot.Robot;

/**
 * Executable command to move the {@code Robot} towards facing direction.
 *
 */
public class MoveCommand implements ExecutableRobotCommand
{
    @Override
    public void execute(Robot robot)
    {
        robot.move();
    }
}
