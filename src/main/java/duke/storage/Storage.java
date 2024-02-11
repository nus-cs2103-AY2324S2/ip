package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;


/**
 * A utility class for handling storage operations, such as loading and saving tasks to a file.
 *
 * <p>The {@code Storage} class provides methods for reading tasks from a file and writing tasks
 * to a file. It handles the conversion between task objects and their string representations
 * suitable for storage in a file. It also defines the file path where tasks are stored.</p>
 */
public class Storage {
    private static final String pathName = "data/tasks.txt";
    private static final File storageFile = new File(pathName);

    /**
     * Loads tasks from the storage file into a TaskList object.
     *
     * @return A {@link TaskList} containing tasks loaded from the storage file.
     * @throws FileNotFoundException If the storage file is not found.
     */
    public TaskList load() throws FileNotFoundException {
        TaskList list = new TaskList();
        if (!storageFile.exists()) {
            return list;
        }

        Scanner sc = new Scanner(storageFile);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine().strip();
            String[] args = instruction.split("\\s\\|\\s");

            String taskType = args[0];
            boolean isDone = args[1].equals("[X]");
            String description = args[2];

            if (taskType.equals("[T]")) {
                ToDo todo = new ToDo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                list.addTask(todo);
            } else if (taskType.equals("[D]")) {
                String dateString = args[3];
                LocalDateTime dateTime = LocalDateTime.parse(dateString,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                Deadline deadline = new Deadline(description, dateTime);
                if (isDone) {
                    deadline.markAsDone();
                }
                list.addTask(deadline);
            } else {
                String fromString = args[3];
                String toString = args[4];
                LocalDateTime fromDateTime = LocalDateTime.parse(fromString,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime toDateTime = LocalDateTime.parse(toString,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                Event event = new Event(description, fromDateTime, toDateTime);
                if (isDone) {
                    event.markAsDone();
                }
                list.addTask(event);
            }
        }
        return list;
    }


    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }


    /**
     * Saves tasks from a TaskList object to the storage file.
     *
     * @param list The {@link TaskList} containing tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the storage file.
     */
    public void save(TaskList list) throws IOException {
        StringBuilder record = new StringBuilder();
        for (int i = 0; i < list.getNoOfTasks(); i++) {
            Task task = list.getTask(i);
            record.append(task.storageString()).append(System.lineSeparator());
        }
        writeToFile(pathName, record.toString());
    }
}
