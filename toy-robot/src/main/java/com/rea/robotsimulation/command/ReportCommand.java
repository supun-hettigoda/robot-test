
package com.rea.robotsimulation.command;

import java.util.Optional;

import com.rea.robotsimulation.robot.Robot;

/**
 * Executable command to report the {@code Robot}'s state.
 *
 */
public class ReportCommand implements ExecutableRobotCommand
{
    @Override
    public void execute(Robot robot)
    {
        Optional<String> report = robot.report();
        // TODO what to do with the reporting.
    }
}
