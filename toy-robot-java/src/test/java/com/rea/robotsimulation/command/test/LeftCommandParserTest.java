
package com.rea.robotsimulation.command.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.rea.robotsimulation.command.ExecutableRobotCommand;
import com.rea.robotsimulation.command.LeftCommand;
import com.rea.robotsimulation.command.LeftCommandParser;

/**
 * This class contains unit tests for {@link LeftCommandParser}.
 *
 */
@RunWith(Theories.class)
public class LeftCommandParserTest
{
    private LeftCommandParser parser;

    @Before
    public void setUp()
    {
        parser = new LeftCommandParser();
    }

    @DataPoints("parsableInputs")
    public static String[] parsableInputs = new String[]
    {
        "LEFT", "LEFT ", " LEFT", " LEFT ", "LEFT   "
    };

    @DataPoints("notParsableInputs")
    public static String[] notParsableInputs = new String[]
    {
        null, "", "left", "Left", "MOVEe", "LEFT 1", "PLACE", "MOVE", "DAMN"
    };

    @Theory
    public void test_parsableInputs(@FromDataPoints("parsableInputs") String input)
    {
        Optional<ExecutableRobotCommand> actual = parser.parse(input);
        assertThat(actual, instanceOf(Optional.class));
        assertThat(actual.get(), instanceOf(LeftCommand.class));
    }

    @Theory
    public void test_invalidInputs(@FromDataPoints("notParsableInputs") String input)
    {
        Optional<ExecutableRobotCommand> actual = parser.parse(input);
        assertEquals(Optional.empty(), actual);
    }
}
