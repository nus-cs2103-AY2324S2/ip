package cappy.util;

/**
 * Utility class for logging messages at different log levels.
 *
 * <p>The {@code Logger} class provides methods to log messages at various log levels, allowing
 * developers to control the granularity of logging based on the severity of messages. The supported
 * log levels include DEBUG, INFO, WARN, and ERROR.
 *
 * <p><strong>Log Levels:</strong>
 *
 * <ul>
 *   <li>{@code DEBUG}: Fine-grained debugging information. Typically used for diagnosing issues
 *       during development.
 *   <li>{@code INFO}: General information about the application's state or significant events. Used
 *       for informative purposes.
 *   <li>{@code WARN}: Warning messages indicating potential issues that may require attention but
 *       do not disrupt normal operation.
 *   <li>{@code ERROR}: Critical error messages indicating a failure or issue that requires
 *       immediate attention.
 * </ul>
 *
 * <p>The log level can be dynamically set using the {@link #setLogLevel(LogLevel)} method.
 */
public class Logger {
    /** Enumeration representing different log levels. */
    public enum LogLevel {
        /**
         * Fine-grained debugging information. Typically used for diagnosing issues during
         * development.
         */
        DEBUG,

        /**
         * General information about the application's state or significant events. Used for
         * informative purposes.
         */
        INFO,

        /**
         * Warning messages indicating potential issues that may require attention but do not
         * disrupt normal operation.
         */
        WARN,

        /**
         * Critical error messages indicating a failure or issue that requires immediate attention.
         */
        ERROR
    }

    private static LogLevel logLevel = LogLevel.INFO;

    /**
     * Sets the log level for the Logger.
     *
     * <p>Messages below the specified log level will not be printed.
     *
     * @param logLevel The desired log level.
     */
    public static void setLogLevel(LogLevel logLevel) {
        Logger.logLevel = logLevel;
    }

    /**
     * Prints a general message to the standard output.
     *
     * @param message The message to be printed.
     */
    public static void print(String message) {
        System.out.println(message);
    }

    /**
     * Logs a debug message to the error stream if the log level allows it.
     *
     * @param message The debug message to be logged.
     */
    public static void debug(String message) {
        if (Logger.logLevel.compareTo(LogLevel.DEBUG) <= 0) {
            System.err.println("[DEBUG] " + message);
        }
    }

    /**
     * Logs an info message to the standard output if the log level allows it.
     *
     * @param message The info message to be logged.
     */
    public static void info(String message) {
        if (Logger.logLevel.compareTo(LogLevel.INFO) <= 0) {
            System.out.println("[INFO] " + message);
        }
    }

    /**
     * Logs a warning message to the error stream if the log level allows it.
     *
     * @param message The warning message to be logged.
     */
    public static void warn(String message) {
        if (Logger.logLevel.compareTo(LogLevel.WARN) <= 0) {
            System.err.println("[WARN] " + message);
        }
    }

    /**
     * Logs an error message to the error stream.
     *
     * @param message The error message to be logged.
     */
    public static void error(String message) {
        System.err.println("[ERROR] " + message);
    }

    /**
     * Logs an exception to the error stream.
     *
     * @param exception The exception to be logged.
     */
    public static void error(Exception exception) {
        System.err.println("[ERROR] " + exception.getMessage());
    }
}
