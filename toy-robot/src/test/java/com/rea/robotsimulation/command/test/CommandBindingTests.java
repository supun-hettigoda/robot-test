
package com.rea.robotsimulation.command.test;

import java.util.Arrays;
import java.util.List;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.rea.robotsimulation.command.CommandBinding;
import com.rea.robotsimulation.command.ExecutableRobotCommand;
import com.rea.robotsimulation.command.ReportCommandParser;
import com.rea.robotsimulation.util.Pair;

/**
 * This class contains unit tests for {@link CommandBinding}.
 *
 */
@RunWith(Theories.class)
public class CommandBindingTests
{

    @Theory
    public void test_toExecutable()
    {

    }

    @DataPoint("matchedParsers")
    public List<Pair<ReportCommandParser, String>> matchedParsers()
    {
        return Arrays.asList(Pair.of(Mockito.mock(ExecutableRobotCommand.class), second));
    }
}
