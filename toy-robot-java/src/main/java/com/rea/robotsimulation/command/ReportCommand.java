
package com.rea.robotsimulation.command;

import java.util.Optional;
import java.util.function.Consumer;

import com.rea.robotsimulation.robot.Robot;

/**
 * Executable command to report the {@code Robot}'s state.
 *
 */
public class ReportCommand implements ExecutableRobotCommand
{
    @Override
    public void execute(Robot robot, Optional<Consumer<String>> commandExecutionStateReporter)
    {
        Optional<String> report = robot.report();
        if (commandExecutionStateReporter.isPresent() && report.isPresent())
        {
            commandExecutionStateReporter.get().accept(report.get());
        }
    }
}
