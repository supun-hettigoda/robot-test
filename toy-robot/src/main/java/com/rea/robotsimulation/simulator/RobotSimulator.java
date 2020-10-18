
package com.rea.robotsimulation.simulator;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rea.robotsimulation.command.CommandBinding;
import com.rea.robotsimulation.command.ExecutableRobotCommand;
import com.rea.robotsimulation.robot.Robot;

/**
 * This class defines a simulator which can simulate a {@code Robot} movements.
 *
 * <p>
 * This will required a {@code InputCommandProvider} to provide command inputs which needs to be
 * executed against a {@code Robot}.
 * </p>
 *
 * <b>note:</b>If a {@code commandExecutionStateReporter} provided it will receive all state reports
 * sent via executable commands.
 *
 * @see CommandBinding
 * @see Robot
 * @see InputCommandProvider
 * @see ExecutableRobotCommand
 *
 */
public class RobotSimulator
{
    public static final Logger LOGGER = LoggerFactory.getLogger(RobotSimulator.class);

    // from which the simulator receive simulation commands.
    private final InputCommandProvider commandProvider;

    // whom to report command execution state.
    private final Optional<Consumer<String>> commandExecutionStateReporter;

    public RobotSimulator(InputCommandProvider commandProvider,
            Consumer<String> commandExecutionStateReporter)
    {
        super();
        Objects.requireNonNull(commandProvider);
        this.commandProvider = commandProvider;
        this.commandExecutionStateReporter = Optional.ofNullable(commandExecutionStateReporter);
    }

    /**
     * This method will start up the simulation process and continuously accept input commands
     * provided by the given {@code InputCommandProvider} until the provider has no command to send.
     */
    public void start(Robot robot)
    {
        LOGGER.info("Simulator starting up...");
        Objects.requireNonNull(robot);
        // continuously keep listening for new commands
        Optional<String> nextCommand = this.commandProvider.nextCommand();
        while (nextCommand.isPresent())
        {
            executeInputCommand(nextCommand.get(), robot);
            nextCommand = this.commandProvider.nextCommand();
        }
        reportShuttingDown();

    }

    private void reportShuttingDown()
    {
        if (this.commandExecutionStateReporter.isPresent())
        {
            this.commandExecutionStateReporter.get().accept("No more commands shutting down...");
        }
        LOGGER.info("Simuator terminated.");
    }

    private void executeInputCommand(String inputCommand, Robot robot)
    {
        LOGGER.debug("evaluating input command[" + inputCommand + "]");
        Optional<ExecutableRobotCommand> executable = CommandBinding.toExecutable(inputCommand);
        if (executable.isPresent())
        {
            LOGGER.debug("input match to the executable [" + executable.get().getClass() + "]");
            executable.get().execute(robot, this.commandExecutionStateReporter);
        }
    }
}
