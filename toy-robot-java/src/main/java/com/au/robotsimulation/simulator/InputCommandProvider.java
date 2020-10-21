
package com.au.robotsimulation.simulator;

import java.util.Optional;

/**
 * Input command provider definition.
 *
 */
public interface InputCommandProvider
{
    /**
     * @return the next command that need to be executed, empty means no more commands will be
     *         provided.
     */
    public Optional<String> nextCommand();
}
