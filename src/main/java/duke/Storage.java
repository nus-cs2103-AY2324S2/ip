package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * The Storage class handles the loading and saving of task data for the Duke chatbot.
 * It interacts with the file system to read and write tasks from and to a file.
 */
public class Storage {
    private File file;
    private String path;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param path The file path for loading and saving tasks.
     */
    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
        assert (file != null) : "file object should be initialised!";

        // set-up infrastructure
        try {
            File dir = new File(file.getParent());
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("File unable to be created in specified directory");
        }
    }

    /**
     * Loads tasks from existing file, if any.
     *
     * @return A TaskList containing the tasks loaded from the file.
     * @throws DukeException If the file is not found or there is an issue reading it.
     */
    public TaskList loadTasks() {
        try {
            TaskList tasks = new TaskList();

            Scanner s = new Scanner(this.file);
            while (s.hasNextLine()) {
                String[] taskInput = s.nextLine().split("\\s*\\|\\s*");
                String cmd = taskInput[0];

                if (cmd.equals("D")) {
                    Deadline dl = new Deadline(taskInput[2], taskInput[3]);
                    tasks.addTask(dl, taskInput[1].equals("1"));
                } else if (cmd.equals("E")) {
                    Event evt = new Event(taskInput[2], taskInput[3], taskInput[4]);
                    tasks.addTask(evt, taskInput[1].equals("1"));
                } else if (cmd.equals("T")) {
                    Todo td = new Todo(taskInput[2]);
                    tasks.addTask(td, taskInput[1].equals("1"));
                } else {
                    throw new DukeException("Task unable to be loaded; Task type not found");
                }
            }
            s.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }
    }

    /**
     * Writes current tasks to the file.
     *
     * @param tasks The TaskList containing the tasks to be written.
     */
    public void writeTasks(TaskList tasks) {
        try (BufferedWriter reset = new BufferedWriter(new FileWriter(this.path))) {
            reset.write("");
            tasks.getTasks().forEach((task) -> {
                try (BufferedWriter out = new BufferedWriter(
                        new FileWriter(this.path, true))) {
                    out.write(task.writeContent());
                    out.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
