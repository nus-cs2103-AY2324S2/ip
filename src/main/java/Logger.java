public class Logger {
    public enum LogLevel {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    private static LogLevel logLevel = LogLevel.INFO;

    public static void setLogLevel(LogLevel logLevel) {
        Logger.logLevel = logLevel;
    }

    public static void print(String message) {
        System.out.println(message);
    }

    public static void debug(String message) {
        if (Logger.logLevel.compareTo(LogLevel.DEBUG) <= 0) {
            System.err.println("[DEBUG] " + message);
        }
    }

    public static void info(String message) {
        if (Logger.logLevel.compareTo(LogLevel.INFO) <= 0) {
            System.out.println("[INFO] " + message);
        }
    }

    public static void warn(String message) {
        if (Logger.logLevel.compareTo(LogLevel.WARN) <= 0) {
            System.err.println("[WARN] " + message);
        }
    }

    public static void error(String message) {
        if (Logger.logLevel.compareTo(LogLevel.ERROR) <= 0) {
            System.err.println("[ERROR] " + message);
        }
    }
}
