package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Scanner;

/**
 * Represents the file used to store the tasks.
 * Handles loading and writing tasks data to a file.
 */
public class Storage {
    private final String FILEPATH = "./src/main/data/tasks.txt";

    /**
     * Constructs Storage object.
     */
    public Storage() { }

    /**
     * Loads the tasks from the specified filepath into the task list.
     *
     * @param tasks TaskList to store the tasks.
     * @throws IOException If error occur when reading the file.
     */
    public void loadFile(TaskList tasks) throws IOException {
        File file = new File(FILEPATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] taskDescriptions = sc.nextLine().split("\\|");
            boolean done = Boolean.parseBoolean(taskDescriptions[2]);
            String taskName = taskDescriptions[1];
            switch (taskDescriptions[0]) {
            case "todo" :
                tasks.addTask(new ToDo(taskName, done));
                break;
            case "deadline" :
                if (taskDescriptions[3].split("T").length > 1) {
                    LocalDateTime time = LocalDateTime.parse(taskDescriptions[3]);
                    tasks.addTask(new Deadline(taskName, done, time));
                } else {
                    LocalDate time = LocalDate.parse(taskDescriptions[3]);
                    tasks.addTask(new Deadline(taskName, done, time));
                }
                break;
            case "event" :
                tasks.addTask(new Event(taskName, done, taskDescriptions[3], taskDescriptions[4]));
                break;
            default:
                break;
            }
        }
    }

    /**
     * Store the tasks in the task list into the specified file.
     *
     * @param tasks List of tasks to be stored.
     * @throws IOException If error occurred when writing to the file.
     */
    public void writeFile(TaskList tasks) throws IOException {
        File f = new File(this.FILEPATH);
        FileWriter writer = new FileWriter(f);

        writer.write(tasks.storeTasks());
        writer.close();
    }
}
