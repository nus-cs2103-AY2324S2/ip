package SamuelBot;
import java.util.logging.Logger;

public class Assertions {

    private static final Logger LOGGER = Logger.getLogger(Assertions.class.getName());

    public static void assertTaskListNotNull(Samuelbot samuelbot) {
        if (samuelbot.taskList == null) {
            LOGGER.severe("Assertion failed: Task list is null.");
            throw new AssertionError("Task list is null.");
        }
        LOGGER.info("Assertion passed: Task list is not null.");
    }

    public static void assertUiNotNull(Samuelbot samuelbot) {
        if (samuelbot.ui == null) {
            LOGGER.severe("Assertion failed: UI is null.");
            throw new AssertionError("UI is null.");
        }
        LOGGER.info("Assertion passed: UI is not null.");
    }

    public static void assertStorageNotNull(Samuelbot samuelbot) {
        if (samuelbot.storage == null) {
            LOGGER.severe("Assertion failed: Storage is null.");
            throw new AssertionError("Storage is null.");
        }
        LOGGER.info("Assertion passed: Storage is not null.");
    }

    public static void assertFilePathValid(Samuelbot samuelbot) {
        if (samuelbot.file_path == null || samuelbot.file_path.isEmpty()) {
            LOGGER.severe("Assertion failed: File path is null or empty.");
            throw new AssertionError("File path is null or empty.");
        }
        LOGGER.info("Assertion passed: File path is valid.");
    }
}
//
