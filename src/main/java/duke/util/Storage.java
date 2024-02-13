package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Represents a storage mechanism.
 */
public class Storage {
    private File f;
    private FileWriter fw;
    private Scanner s;

    /**
     * Constructor to create an instance that perform IO operations on file in parent.
     *
     * @param file Name of file to write to.
     */
    public Storage(String file) throws IOException {
        f = new File(file);
        Path path = Paths.get(file);
        Path parentDir = path.getParent();
        if (parentDir != null) {
            if (!Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("Parent directory created: " + parentDir);
            } else {
                System.out.println("Parent directory already exists: " + parentDir);
            }
        } else {
            System.out.println("Parent directory not specified in the file path.");
        }
    }

    /**
     * Writes String of tasks to file.
     *
     * @param list List of tasks to be written.
     */
    public void writeToFile(TaskList list) {
        try {
            this.fw = new FileWriter(f);
            String data = write(list);
            this.fw.write(data);
            this.fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns list of Tasks in readable format.
     *
     * @param taskList Holds the list of tasks.
     * @return Structured String of all Tasks in taskList.
     */
    private String write(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if (i < list.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns ArrayList of tasks read from file.
     *
     * @return ArrayList of Task.
     * @throws DukeException If file not found due to no existing data saved.
     */
    public ArrayList<Task> readFromFile() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        if (f.exists()) {
            try {
                this.s = new Scanner(this.f);
                while (this.s.hasNext()) {
                    list.add(read(this.s.nextLine()));
                }
            } catch (FileNotFoundException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return list;
    }

    /**
     * Converts String into corresponding instances of Task.
     *
     * @param s Describes a Task.
     * @return Task instance created based on s.
     */
    private Task read(String s) {
        String[] cols = s.split(" \\| ");
        Task t = null;
        if (cols.length == 3) {
            t = new Todo(cols[2]);

        } else if (cols.length == 4) {
            t = new Deadline(cols[2], cols[3]);
        } else if (cols.length == 5) {
            t = new Event(cols[2], cols[3], cols[4]);
        }
        if (cols[1].equals("1")) {
            t.done();
        } else {
            t.undo();
        }

        return t;
    }

}
