
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
import com.rea.robotsimulation.command.MoveCommandParser;
import com.rea.robotsimulation.command.PlaceCommand;
import com.rea.robotsimulation.command.PlaceCommandParser;

/**
 * This class contains tests for {@link MoveCommandParser}.
 *
 */
@RunWith(Theories.class)
public class PlaceCommandParserTest
{
    private PlaceCommandParser parser;

    @Before
    public void setUp()
    {
        parser = new PlaceCommandParser();
    }

    @DataPoints("parsableInputs")
    public static String[] parsableInputs = new String[]
    {
        // @formatter:off
        "PLACE 0,0,NORTH",
        "PLACE  1, 1, NORTH",
        "PLACE 1,1,  NORTH",
        "PLACE 1,1,NORTH",
        " PLACE 9,0,SOUTH",
        " PLACE 9,0,WEST",
        "PLACE  1,0,EAST  ",
        // @formatter:on
    };

    @DataPoints("notParsableInputs")
    public static String[] notParsableInputs = new String[]
    {
        // @formatter:off
        null,
        "",
        "place 0,0,NORTH",
        "place 0, 0, NORTH",
        "place  0, 0, NORTH",
        "PLACE 0,0,north",
        "PLACE -9,-9,NORTH",
        "RIGHT 1",
        "PLACE",
        "LEFT 1,1,NORTH",
        "DAMN"
        // @formatter:on
    };

    @Theory
    public void test_parsableInputs(@FromDataPoints("parsableInputs") String input)
    {
        Optional<ExecutableRobotCommand> actual = parser.parse(input);
        assertThat(actual, instanceOf(Optional.class));
        assertThat(actual.get(), instanceOf(PlaceCommand.class));
    }

    @Theory
    public void test_invalidInputs(@FromDataPoints("notParsableInputs") String input)
    {
        Optional<ExecutableRobotCommand> actual = parser.parse(input);
        assertEquals(Optional.empty(), actual);
    }
}
