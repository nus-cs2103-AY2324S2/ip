package duke;

import static duke.DukeException.EVENT_ERROR;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The `Storage` class represents a tool to store tasks in file and read from file.
 * It provides methods to store tasks in file and read from file.
 */
public class Storage {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private final String filePath;

    /**
     * Constructor of a storage that stores items at a specified file path.
     *
     * @param filePath File path to store items.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads stored tasks from the specified file path.
     *
     * @return A list of tasks stored in the specified file path.
     * @throws IllegalArgumentException If error in accessing file to retrieve tasks.
     */
    public List<Task> load() throws DukeException {
        List<Task> items = new ArrayList<>();
        String home = System.getProperty("user.dir");
        String[] splitedFile = this.filePath.split("/");

        StringBuilder sb = new StringBuilder();
        sb.append(home);

        // Ensure that the file exist
        for (int i = 0; i < splitedFile.length; i++) {
            sb.append("/" + splitedFile[i]);
            Path path = Paths.get(sb.toString());

            // Folder not exist
            if (i != splitedFile.length - 1 && !Files.exists(path)) {
                try {
                    Files.createDirectory(path);
                } catch (Exception e) {
                    throw new DukeException(e.getMessage());
                }
            }

            // Folder exist but not folder
            if (i != splitedFile.length - 1 && !Files.isDirectory(path)) {
                throw new DukeException();
            }

            // File not exist
            if (i == splitedFile.length - 1 && !Files.exists(path)) {
                try {
                    Files.createFile(path);
                } catch (Exception e) {
                    throw new DukeException(e.getMessage());
                }
            }

            // File exist but not exist
            if (i == splitedFile.length - 1 && Files.isDirectory(path)) {
                throw new DukeException();
            }
        }

        Path path = Paths.get(home, this.filePath);

        // Read from file
        try {
            Scanner scanner = new Scanner(new File(path.toString()));
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                if (data.isBlank()) {
                    break;
                }

                String[] tasks = data.split("/end");
                for (String task: tasks) {
                    String[] splited = task.split("`");
                    switch (splited[0]) {
                    case "duke.task.Deadline": {
                        String content = splited[1];
                        boolean isDone = splited[2].equals("Y");
                        String deadline = splited[3];
                        String[] splitedDateTime = deadline.split(" ");

                        // Date and time
                        try {
                            if (splitedDateTime.length == 2) {
                                LocalDateTime parsedDateTime = LocalDateTime.parse(deadline, dateTimeFormatter);
                                Task newTask = new Deadline(content, isDone, parsedDateTime);
                                items.add(newTask);
                            } else {
                                LocalDate parsedDate = LocalDate.parse(deadline, dateFormatter);
                                Task newTask = new Deadline(content, isDone, parsedDate);
                                items.add(newTask);
                            }
                        } catch (Exception e) {
                            throw new DukeException("deadline");
                        }
                        break;
                    }
                    case "duke.task.Event": {
                        String content = splited[1];
                        boolean isDone = splited[2].equals("Y");
                        String from = splited[3];
                        String to = splited[4];

                        // From
                        String[] splitedFrom = from.split(" ");
                        String[] splitedTo = to.split(" ");

                        // Date and time
                        try {
                            Task newTask;
                            if (splitedFrom.length == 2 && splitedTo.length == 2) {
                                LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, dateTimeFormatter);
                                LocalDateTime parsedToDateTime = LocalDateTime.parse(to, dateTimeFormatter);
                                newTask = new Event(content, isDone, parsedFromDateTime, parsedToDateTime);
                            } else if (splitedFrom.length == 2 && splitedTo.length == 1) {
                                LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, dateTimeFormatter);
                                LocalDate parsedToDate = LocalDate.parse(to, dateFormatter);
                                newTask = new Event(content, isDone, parsedFromDateTime, parsedToDate);
                            } else if (splitedFrom.length == 1 && splitedTo.length == 2) {
                                LocalDate parsedFromDate = LocalDate.parse(from, dateFormatter);
                                LocalDateTime parsedToDateTime = LocalDateTime.parse(to, dateTimeFormatter);
                                newTask = new Event(content, isDone, parsedFromDate, parsedToDateTime);
                            } else {
                                LocalDate parsedFromDate = LocalDate.parse(from, dateFormatter);
                                LocalDate parsedToDate = LocalDate.parse(to, dateFormatter);
                                newTask = new Event(content, isDone, parsedFromDate, parsedToDate);
                            }
                            items.add(newTask);
                        } catch (Exception e) {
                            throw new DukeException(EVENT_ERROR);
                        }
                        break;
                    }
                    default: {
                        items.add(new Todo(splited[1], splited[2].equals("Y")));
                    }
                    }
                }
            }
        } catch (Exception e) {
            throw new DukeException("Error accessing / creating the file: " + e.getMessage());
        }

        return items;
    }

    /**
     * Store tasks to the specified file path.
     *
     * @param tasks A list of existing tasks.
     * @throws IllegalArgumentException If error in accessing file to store tasks.
     */
    public void saveChanges(TaskList tasks) throws DukeException {
        String home = System.getProperty("user.dir");
        Path filePath = Paths.get(home, this.filePath);

        StringBuilder textToAdd = new StringBuilder();
        for (Task t: tasks.getItems()) {
            switch (t.getClass().getName()) {
            case "duke.task.Deadline": {
                textToAdd.append(t.getClass().getName() + "`" + t.getFields()[0] + "`" + t.getFields()[1]
                        + "`" + t.getFields()[2] + "/end");
                break;
            }
            case "duke.task.Event": {
                textToAdd.append(t.getClass().getName() + "`" + t.getFields()[0] + "`" + t.getFields()[1]
                        + "`" + t.getFields()[2] + "`" + t.getFields()[3] + "/end");
                break;
            }
            default: {
                textToAdd.append(t.getClass().getName() + "`" + t.getFields()[0] + "`" + t.getFields()[1] + "/end");
            }
            }
        }

        try {
            FileWriter fw = new FileWriter(filePath.toString());
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving changes: " + e.getMessage());
        }
    }
}
