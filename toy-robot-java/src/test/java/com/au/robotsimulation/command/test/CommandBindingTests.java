
package com.au.robotsimulation.command.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.au.robotsimulation.command.CommandBinding;
import com.au.robotsimulation.command.ExecutableRobotCommand;
import com.au.robotsimulation.command.LeftCommand;
import com.au.robotsimulation.command.MoveCommand;
import com.au.robotsimulation.command.PlaceCommand;
import com.au.robotsimulation.command.ReportCommand;
import com.au.robotsimulation.command.RightCommand;
import com.au.robotsimulation.util.Pair;

/**
 * This class contains unit tests for {@link CommandBinding}.
 *
 */
@RunWith(Theories.class)
public class CommandBindingTests
{
    @DataPoints("notParsableInputs")
    public static String[] notParsableInputs = new String[]
    {
        // @formatter:off
        null,
        "",
        "place 0,0,NORTH",
        "rigth",
        "RE PORT",
        "RIGHT 1",
        "PLACE",
        "left",
        "DAMN"
        // @formatter:on
    };

    @Theory
    public void test_commandReturnsForValidInputs(
            @FromDataPoints("validInputsAndCommand") Pair<String, Class<? extends ExecutableRobotCommand>> testData)
    {
        Optional<ExecutableRobotCommand> actual = CommandBinding.toExecutable(testData.getFirst());
        assertThat(actual, instanceOf(Optional.class));
        assertThat(actual.get(), instanceOf(testData.getSecond()));
    }

    @Theory
    public void
            test_noCommandReturnForInvalidInputs(@FromDataPoints("notParsableInputs") String input)
    {
        Optional<ExecutableRobotCommand> actual = CommandBinding.toExecutable(input);
        assertEquals(Optional.empty(), actual);
    }

    @DataPoints("validInputsAndCommand")
    public static List<Pair<String, Class<? extends ExecutableRobotCommand>>>
            validInputsAndCommand()
    {
        return Arrays.asList(
                Pair.of("MOVE", MoveCommand.class),
                Pair.of("LEFT", LeftCommand.class),
                Pair.of("REPORT", ReportCommand.class),
                Pair.of("PLACE 1,2,NORTH", PlaceCommand.class),
                Pair.of("RIGHT", RightCommand.class));
    }
}
