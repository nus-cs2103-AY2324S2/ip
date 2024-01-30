package nollid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import nollid.exceptions.NollidException;
import nollid.tasks.Deadline;
import nollid.tasks.Event;
import nollid.tasks.Task;
import nollid.tasks.Todo;

/**
 * Storage class handles loading and updating tasks from/to a file.
 */
public class Storage {

    /**
     * Unicode character U+2605 unlikely to be entered by user.
     */
    protected static final String DELIMITER = "\u2605";
    protected static final Path DEFAULT_FILEPATH = Paths.get(".", "data", "nollid.data");


    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a List of Tasks from the nollid.Storage's file path.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (Files.notExists(this.filePath)) {
                if (Files.notExists(this.filePath.getParent())) {
                    Files.createDirectories(this.filePath.getParent());
                }
                Files.createFile(this.filePath);
            }

            for (String line : Files.readAllLines(this.filePath)) {
                String[] lineArray = line.split(DELIMITER);

                Task taskToAdd;
                String taskDescription;
                try {
                    taskDescription = lineArray[2];
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Line that doesn't follow the format
                    continue;
                }

                switch (lineArray[0]) {
                case "T":
                    taskToAdd = new Todo(taskDescription);
                    break;
                case "D":
                    String deadlineString = lineArray[3];
                    try {
                        LocalDateTime deadline = Parser.getLocalDateTimeFromString(deadlineString);
                        taskToAdd = new Deadline(taskDescription, deadline);
                    } catch (DateTimeParseException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case "E":
                    String from = lineArray[3];
                    String to = lineArray[4];

                    try {
                        LocalDateTime fromDateTime = Parser.getLocalDateTimeFromString(from);
                        LocalDateTime toDateTime = Parser.getLocalDateTimeFromString(to);
                        taskToAdd = new Event(taskDescription, fromDateTime, toDateTime);
                    } catch (DateTimeParseException | NollidException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                default:
                    // Unknown first character, go to next line
                    continue;
                }
                String doneFlag = lineArray[1];
                if (doneFlag.equals("1")) {
                    taskToAdd.setDone(true);
                }

                taskList.add(taskToAdd);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return taskList;
    }

    /**
     * Updates the storage on disk based on the current state of the given task list.
     */
    public void update(TaskList taskList) {
        boolean isFirstLine = true;
        if (taskList.isEmpty()) {
            try {
                Files.write(this.filePath, "".getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        for (Task t : taskList) {
            String lineToWrite = "";
            try {
                if (t instanceof Todo) {
                    lineToWrite = "T" + DELIMITER + t.getStatusNumber() + DELIMITER + t.getDescription() + "\n";
                } else if (t instanceof Deadline) {
                    Deadline deadline = (Deadline) t;
                    lineToWrite = "D" + DELIMITER + deadline.getStatusNumber() + DELIMITER + deadline.getDescription()
                            + DELIMITER + deadline.getDeadline().format(Parser.SAVE_FORMAT) + "\n";
                } else if (t instanceof Event) {
                    Event event = (Event) t;
                    lineToWrite = "E" + DELIMITER + event.getStatusNumber() + DELIMITER + event.getDescription()
                            + DELIMITER + event.getFrom().format(Parser.SAVE_FORMAT)
                            + DELIMITER + event.getTo().format(Parser.SAVE_FORMAT) + "\n";
                }

                if (isFirstLine) {
                    Files.write(this.filePath, lineToWrite.getBytes());
                    isFirstLine = false;
                } else {
                    Files.write(this.filePath, lineToWrite.getBytes(), StandardOpenOption.APPEND);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
