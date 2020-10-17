
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
import com.rea.robotsimulation.command.ReportCommand;
import com.rea.robotsimulation.command.ReportCommandParser;

/**
 * This class contains tests for {@link ReportCommandParser}.
 *
 */
@RunWith(Theories.class)
public class ReportCommandParserTest
{
    private ReportCommandParser parser;

    @Before
    public void setUp()
    {
        parser = new ReportCommandParser();
    }

    @DataPoints("parsableInputs")
    public static String[] parsableInputs = new String[]
    {
        "REPORT", "REPORT ", " REPORT", " REPORT ", "REPORT   "
    };

    @DataPoints("notParsableInputs")
    public static String[] notParsableInputs = new String[]
    {
        null, "report", "", "Report", "MOVEe", "REPORT 1", "PLACE", "LEFT", "DAMN"
    };

    @Theory
    public void test_parsableInputs(@FromDataPoints("parsableInputs") String input)
    {
        Optional<ExecutableRobotCommand> actual = parser.parse(input);
        assertThat(actual, instanceOf(Optional.class));
        assertThat(actual.get(), instanceOf(ReportCommand.class));
    }

    @Theory
    public void test_invalidInputs(@FromDataPoints("notParsableInputs") String input)
    {
        Optional<ExecutableRobotCommand> actual = parser.parse(input);
        assertEquals(Optional.empty(), actual);
    }
}
