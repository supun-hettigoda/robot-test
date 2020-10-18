
package com.rea.robotsimulation.command;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.rea.robotsimulation.util.FunctionUtils;

/**
 * This enum contains the all available command bindings which can parse a given input to a
 * executable.
 *
 * @see #toExecutable(String)
 */
public enum CommandBinding
{
    // command binding for place the robot
    PLACE(PlaceCommandParser::new),

    // command binding for move the robot one step towards the facing direction
    MOVE(MoveCommandParser::new),

    // command binding for left rotate the robot by 90 degrees
    LEFT(LeftCommandParser::new),

    // command binding for right rotate the robot by 90 degrees
    RIGHT(RightCommandParser::new),

    // command binding for report the current state of the robot
    REPORT(ReportCommandParser::new);

    private final Supplier<RobotCommandParser> parserSupplier;

    private CommandBinding(Supplier<RobotCommandParser> parserSupplier)
    {
        this.parserSupplier = parserSupplier;
    }

    /**
     * This method will parse the given input string to a matching executable.
     *
     * @return a {@code Optional} with the executable if the input could be resolved, empty
     *         otherwise.
     */
    public static Optional<ExecutableRobotCommand> toExecutable(String input)
    {
        // @formatter:off
        return Stream.of(values())
                .map(CommandBinding::getParser)
                .map(FunctionUtils.reduceToOneArgFunction(CommandBinding::doParse, input))
                .filter(Optional::isPresent)
                .findFirst().orElse(Optional.empty());
        // @formatter:on
    }

    private static RobotCommandParser getParser(CommandBinding binding)
    {
        return binding.parserSupplier.get();
    }

    private static Optional<ExecutableRobotCommand> doParse(RobotCommandParser parser, String input)
    {
        return parser.parse(input);
    }
}
