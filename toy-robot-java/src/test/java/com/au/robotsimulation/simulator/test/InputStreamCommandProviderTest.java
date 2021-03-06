
package com.au.robotsimulation.simulator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import com.au.robotsimulation.simulator.InputStreamCommandProvider;

/**
 * This class contains unit tests for {@link InputStreamCommandProvider}.
 *
 */
public class InputStreamCommandProviderTest
{
    @Test
    public void test_getNextCommand()
    {
        InputStream is = new ByteArrayInputStream("PLACE 1,1,NORTH\r\nMOVE".getBytes());
        InputStreamCommandProvider provider = new InputStreamCommandProvider(is);
        assertEquals("PLACE 1,1,NORTH", provider.nextCommand().get());
        assertEquals("MOVE", provider.nextCommand().get());

        // next attempt should result in an empty optional since the input stream only has two
        // lines of commands.
        assertFalse(provider.nextCommand().isPresent());
    }
}
