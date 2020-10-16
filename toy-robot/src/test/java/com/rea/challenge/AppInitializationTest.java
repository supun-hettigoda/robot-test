
package com.rea.challenge;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test to cover the Main application initialisation.
 */
public class AppInitializationTest
{
    private static final String EOL = System.getProperty("line.separator");
    private PrintStream console;
    private ByteArrayOutputStream bytes;

    // TODO update the test cases here when more implementation details figured ongoing.

    @Before
    public void setUp()
    {
        // Set a accessible out put stream in the console,
        // so we can assert the out put string.
        bytes = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));
    }

    @After
    public void tearDown()
    {
        System.setOut(console);
    }

    @Test
    public void upAndRunningTest()
    {
        // we expect a success message on the console.
        Main.main(null);
        assertEquals((Main.MSG_SIMULATION_ACTIVE + EOL), bytes.toString());
    }

    @Test
    public void additionalArgumentsIgnored()
    {
        // at this point it is not required to handle any additional arguments.
        Main.main(new String[]
        {
            "arg1"
        });
        assertEquals((Main.MSG_SIMULATION_ACTIVE + EOL), bytes.toString());
    }
}
