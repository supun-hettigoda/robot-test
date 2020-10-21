
package com.au.robotsimulation.command;

import java.util.Optional;
import java.util.regex.Pattern;

import com.au.robotsimulation.util.StringUtils;

/**
 * This {@code RobotCommandParser} knows how to parse a given input command to an
 * {@code ExecutableRobotCommand} which can perform a right action.
 *
 * @see RightCommand
 */
public class RightCommandParser implements RobotCommandParser
{
    private static final Pattern PATTERN = Pattern.compile("^" + CommandBinding.RIGHT.name() + "$");

    @Override
    public Optional<ExecutableRobotCommand> parse(String input)
    {
        if (!StringUtils.isEmpty(input) && PATTERN.matcher(input.trim()).matches())
        {
            return Optional.of(new RightCommand());
        }

        return Optional.empty();
    }
}
