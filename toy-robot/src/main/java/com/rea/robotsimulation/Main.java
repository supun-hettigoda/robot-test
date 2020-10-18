
package com.rea.robotsimulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rea.robotsimulation.robot.OneStepAheadGridScanner;
import com.rea.robotsimulation.robot.RobotTableTop;
import com.rea.robotsimulation.robot.SelfMovingRobot;
import com.rea.robotsimulation.simulator.InputStreamCommandProvider;
import com.rea.robotsimulation.simulator.RobotSimulator;

/**
 * This main class will start the RobotSimulation application on standard input (command line).
 */
public class Main
{
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static final String MSG_SIMULATION_ACTIVE =
            "Robot Simulation waiting for your commands, press Ctrl-D to terminate";

    public static void main(String[] args)
    {
        LOGGER.info("Initializing Robot Simulator.");
        LOGGER.debug("Initializing Robot Simulator in DEBUG mode.");

        RobotSimulator robotSimulator =
                new RobotSimulator(new InputStreamCommandProvider(System.in), System.out::println);
        LOGGER.info("Simulator running...");
        System.out.println(MSG_SIMULATION_ACTIVE);
        robotSimulator
                .start(new SelfMovingRobot(new OneStepAheadGridScanner(new RobotTableTop(5, 5))));
    }
}
