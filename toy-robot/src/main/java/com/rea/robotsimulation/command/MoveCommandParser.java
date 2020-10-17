
package com.rea.robotsimulation.command;

import java.util.Optional;
import java.util.regex.Pattern;

import com.rea.robotsimulation.util.StringUtils;

/**
 * This {@code RobotCommandParser} knows how to parse a given input command to an
 * {@code ExecutableRobotCommand} which can perform the move action.
 *
 * @see LeftCommand
 */
public class MoveCommandParser implements RobotCommandParser
{
    private static final Pattern PATTERN = Pattern.compile("^" + CommandBinding.MOVE.name() + "$");

    @Override
    public Optional<ExecutableRobotCommand> parse(String input)
    {
        if (!StringUtils.isEmpty(input) && PATTERN.matcher(input.trim()).matches())
        {
            return Optional.of(new MoveCommand());
        }

        return Optional.empty();
    }
}
