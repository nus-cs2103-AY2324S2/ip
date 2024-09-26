package duke.utils;

import static duke.constants.Constant.DATE_FORMATTER;
import static duke.constants.Constant.DATE_TIME_FORMATTER;
import static duke.constants.Constant.RELATIVE_PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.tasks.TaskList;

/**
 * Utility methods for common tasks.
 */
public class Utils {
    /**
     * Removes extra spaces from the input string.
     *
     * @param input the input string
     * @return the string with extra spaces removed
     */
    public static String removeExtraSpaces(String input) {
        return input.trim();
    }

    /**
     * Converts time string to LocalDateTime object, accepted date time format is yyyy-MM-dd HHmm
     * @param timeStr for convert to LocalDateTime
     * @return time in LocalDateTime object
     */
    public static LocalDateTime convertToLocalDateTime(String timeStr) {
        return LocalDateTime.parse(timeStr, DATE_TIME_FORMATTER);
    }

    /**
     * Converts time string to LocalDate object, accepted date format is yyyy-MM-dd
     * @param timeStr for convert to LocalDate
     * @return time in LocalDate object
     */
    public static LocalDate convertToLocalDate(String timeStr) {
        return LocalDate.parse(timeStr, DATE_FORMATTER);
    }

    /**
     * Saves the task list to the file at the RELATIVE_PATH
     * @param tasks where to save
     */
    public static void save(TaskList tasks) {
        Path path = Paths.get(RELATIVE_PATH);
        List<String> taskStrList = new ArrayList<String>();
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            for (int i = 0; i < tasks.size(); i++) {
                String str = tasks.get(i).convertToFileFormat(DATE_TIME_FORMATTER);
                taskStrList.add(str);
            }
            String tasksStr = String.join("\n", taskStrList);
            Files.writeString(path, tasksStr);
        } catch (IOException e) {
            System.err.println("There are some error in saving. Try again");
        }
    }
}
