
package com.au.robotsimulation.simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Implementation of {@link InputCommandProvider} which reads the commands from {@link InputStream}
 * of input.
 */
public class InputStreamCommandProvider implements InputCommandProvider
{
    private BufferedReader reader;

    /**
     * Input stream to read command from
     *
     * @param inputStream the input stream
     */
    public InputStreamCommandProvider(InputStream inputStream)
    {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public Optional<String> nextCommand()
    {
        try
        {
            // TIDY can we get rid of the unchecked style here
            // using functional interface conversion?
            return Optional.ofNullable(reader.readLine());
        }
        catch (IOException e)
        {
            return Optional.empty();
        }
    }
}
