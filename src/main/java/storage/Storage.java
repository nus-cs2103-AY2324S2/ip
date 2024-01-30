package storage;

import model.TaskList;
import utils.RemiError;

import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    /**
     * Stores the string representation of the taskList in a designated file.
     */
    public static void store(TaskList taskList) {
        try (FileWriter out = new FileWriter("output.txt");){
            out.write(taskList.toString());
        } catch (IOException err) {
            // do nothing
        }
    }

    /**
     * Retrieves a previously stored data file and returns its representation as a TaskList object.
     *
     * @return the stored TaskList object
     */
    public static TaskList get() {
        TaskList output = new TaskList();

        return output;
    }
}
