package blu.utils;

import java.util.logging.Logger;

/**
 * The {@code BluLogger} class encapsulates the Java Logging API for the application.
 * It provides a simplified interface for logging informational, warning, and severe messages.
 */
public class BluLogger {
    private static final Logger LOGGER = Logger.getLogger("BluLogger");

    /**
     * Logs an informational message at the INFO level.
     *
     * @param message The message to be logged.
     */
    public static void info(String message) {
        LOGGER.info(message);
    }

    /**
     * Logs a warning message at the WARNING level.
     *
     * @param message The message to be logged.
     */
    public static void warning(String message) {
        LOGGER.warning(message);
    }

    /**
     * Logs a severe message at the SEVERE level.
     *
     * @param message The message to be logged.
     */
    public static void severe(String message) {
        LOGGER.severe(message);
    }
}
