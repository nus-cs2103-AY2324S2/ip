package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Handles data storage using file IO.
 */
public class Storage {
    private String filePath = "./data/duke.txt";
    private FileWriter data;

    /**
     * Constructs a new <code>Storage</code> that stores tasks from specified file.
     *
     * @param specifiedFilePath File to be written.
     */
    protected Storage(String specifiedFilePath) {
        filePath = specifiedFilePath;
    }

    /**
     * Loads, parses and returns data from specified file.
     *
     * @return an <code>ArrayList</code> of tasks.
     * @throws IOException When <code>Scanner</code> does not find the file.
     */
    protected ArrayList<Task> load() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] token = sc.nextLine().split("\\|");
            if (token[0].equals("T")) {
                arr.add(new Todo(token[1], Boolean.parseBoolean(token[2])));
            } else if (token[0].equals("D")) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                arr.add(new Deadline(token[1], Boolean.parseBoolean(token[2]), LocalDate.parse(token[3], formatter)));
            } else if (token[0].equals("E")) {
                arr.add(new Event(token[1], Boolean.parseBoolean(token[2]), token[3], token[4]));
            }
        }
        sc.close();
        return arr;
    }

    /**
     * Saves current tasks in instance to specified file.
     *
     * @param taskList <code>ArrayList</code> of tasks to store.
     */
    protected void save(ArrayList<Task> taskList) throws DukeException {
        try {
            data = new FileWriter(filePath);
            for (Task task : taskList) {
                if (task instanceof Todo) {
                    data.write(String.format("T|%s|%s\n", task.getTaskName(), task.isDone()));
                } else if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    data.write(String.format("D|%s|%s|%s\n", d.getTaskName(), d.isDone(),
                            d.getBy().format(DateTimeFormatter.ISO_LOCAL_DATE)));
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    data.write(String.format("E|%s|%s|%s|%s\n",
                            e.getTaskName(), e.isDone(), e.getFrom(), e.getTo()));
                }
            }
            data.close();
        } catch (IOException ie) {
            throw new DukeException("Unable to save! Reason: " + ie.getMessage());
        }
    }
}
