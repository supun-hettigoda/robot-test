
package com.rea.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This main class will start the RobotSimulation application on standard input (command line).
 */
public class Main
{
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static final String MSG_SIMULATION_ACTIVE = "REA Robot Simulation Up And Running.";

    public static void main(String[] args)
    {
        LOGGER.info("Initializing Robot Simulator.");
        LOGGER.debug("Initializing Robot Simulator in DEBUG mode.");

        // TODO initialise the application.

        // out put a message to user on successful initialisation.
        LOGGER.debug("Robot Simulator successfully initialised.");
        System.out.println(MSG_SIMULATION_ACTIVE);
    }
}
