package Duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {

    /**
     * Checks if task is marked
     *
     * @param input
     * @return boolean
     */
    public static boolean isMarked(String input) {
        assert input != null : "Input string must not be null";
        return String.valueOf(input.charAt(4)).equals("X");
    }

    /**
     * Checks if there is tasks logged in a file and load in back on to the ArrayList
     * for use. Creates new file for logging of task if theres is no such file
     *
     * @param storage
     * @param filePath
     * @return Path of file used for logging tasks
     * @throws IOException
     */
    public static Path handleFile(TaskList storage, Path filePath) throws IOException {
        assert storage != null : "TaskList must not be null";
        assert filePath != null : "File path must not be null";
        try {
            List<String> taskList = Files.readAllLines(filePath);
            for (String s : taskList) {
                if (s.startsWith("[T]")) {
                    ToDo task = new ToDo(s.substring(8));
                    if (FileHandler.isMarked(s)) {
                        task.markTask();
                    }
                    storage.add(task);
                } else if (s.startsWith("[D]")) {
                    String msg = s.substring(8);
                    LocalDate date;
                    Deadline task;
                    Pattern p = Pattern.compile("(.+) \\| By: (.+)");
                    Matcher m = p.matcher(msg);
                    if (m.matches()) {
                        date = LocalDate.parse(m.group(2), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        msg = m.group(1);
                        task = new Deadline(msg, date);

                    } else {
                        continue;
                    }
                    if (FileHandler.isMarked(s)) {
                        task.markTask();
                    }
                    storage.add(task);
                } else if (s.startsWith("[E]")) {
                    String msg = s.substring(8);
                    LocalDate fromDate;
                    LocalDate toDate;
                    Event task;
                    Pattern p = Pattern.compile("(.+) \\| From: (.+) To: (.+)");
                    Matcher m = p.matcher(msg);
                    if (m.matches()) {
                        fromDate = LocalDate.parse(m.group(2), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        toDate = LocalDate.parse(m.group(3), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        task = new Event(m.group(1), fromDate, toDate);
                    } else {
                        continue;
                    }
                    if (FileHandler.isMarked(s)) {
                        task.markTask();
                    }
                    storage.add(task);
                }
            }
            return filePath;
        } catch (NoSuchFileException e) {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            return Files.createFile(filePath);
        }

    }
}
