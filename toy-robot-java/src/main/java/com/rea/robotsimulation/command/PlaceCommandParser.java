
package com.rea.robotsimulation.command;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rea.robotsimulation.grid.FacingDirection;
import com.rea.robotsimulation.grid.GridPoint;
import com.rea.robotsimulation.util.StringUtils;

/**
 * This {@code RobotCommandParser} knows how to parse a given input command to an
 * {@code ExecutableRobotCommand} which can perform a place action.
 * <p>
 * Valid input for a place command should be on the following format,
 *
 * <pre>
 * PLACE [digit],[digit],[FacingDirection]
 * </pre>
 *
 * <b>note:</b> This will ignore any white spaces in between segments. After PLACE it need to have
 * at least a one white space. Provided two digits will be resolved to a point on the grid. Any
 * invalid point or facing direction inputs will result in no command return.
 * </p>
 *
 * @see FacingDirection for valid direction inputs.
 * @see PlaceCommand
 */
public class PlaceCommandParser implements RobotCommandParser
{
    // pattern to match PLACE [num],[num],[characters]
    public static Pattern PATTERN = Pattern.compile(
            "^" + CommandBinding.PLACE.name() + "\\s+(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\w+)$");

    @Override
    public Optional<ExecutableRobotCommand> parse(String input)
    {
        // early check before try to create a matcher.
        if (StringUtils.isEmpty(input))
        {
            return Optional.empty();
        }

        Matcher matcher = PATTERN.matcher(input.trim());
        if (matcher.find() && matcher.hitEnd())
        {
            // pattern make sure we don't need special handling for NumberFormat issues.
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            GridPoint point = GridPoint.of(x, y);
            Optional<FacingDirection> direction = parseDirection(matcher);

            if (direction.isPresent())
            {
                return Optional.of(new PlaceCommand(point, direction.get()));
            }
        }

        return Optional.empty();
    }

    private Optional<FacingDirection> parseDirection(Matcher matcher)
    {
        try
        {
            // try to get the DirectionFrom enum value.
            return Optional.of(FacingDirection.valueOf(matcher.group(3)));
        }
        catch (IllegalArgumentException e)
        {
            // else if the direction input cannot be parse to a valid FacingDirection
            return Optional.empty();
        }
    }
}
