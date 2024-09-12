package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the class that is in charge of reading and writing to and from local file.
 */
public class Storage {
    /*
     * Represents the path of the file that stores the task list.
     */
    private String filePath;


    /**
     * Instantiates the file if it does not exists.
     *
     * @param filePath Represents the path of the file that stores the task list.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        assert this.filePath.length() != 0 : "File Path must be defined";

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                new DukeException(e.getMessage());
            }
        }
    }

    /**
     * Saves the task list into the file by writing it in the specified filePath.
     *
     * @param instrList The updated list of tasks from the users.
     * @throws DukeException Thrown when there is an invalid item in the task list.
     */
    public void saveTaskList(ArrayList<Task> instrList) throws DukeException {
        try {
            assert this.filePath.length() != 0 : "File Path must be defined";

            FileWriter fw = new FileWriter(this.filePath);
            for (Task tsk : instrList) {
                fw.write(tsk.toSave());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Invalid Item in Task List, please rectify!!!");
        }
    }

    /**
     * Reads from the specified filePath and writes into a new ArrayList.
     *
     * @return Returns an ArrayList containing the list of tasks from local file.
     * @throws DukeException Thrown when the file is not accessible in the specifiec filepath.
     */
    public ArrayList<Task> loadTaskList() throws DukeException {
        ArrayList<Task> tskList = new ArrayList<>();
        File f = new File(this.filePath);
        try {
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                Task tsk = deconstruct(sc.nextLine());
                tskList.add(tsk);
            }

            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File '" + this.filePath
                + "' not found in local drive. Please check your working folder!");
        }
        return tskList;
    }

    /**
     * Deconstructs the line from cache file to new Task.
     *
     * @param fileStr The line from the cache file.
     * @return A task representing the work to do.
     */
    public Task deconstruct(String fileStr) {
        String[] arr = fileStr.split("\\|");
        Boolean isDone = arr[1].equals("1") ? true : false;

        if (arr[0].equals("T")) {
            return new Todo(arr[2], isDone);
        } else if (arr[0].equals("D")) {
            return new Deadline(arr[2], arr[3], isDone);
        } else if (arr[0].equals("E")) {
            return new Event(arr[2], arr[3], arr[4], isDone);
        }

        return null;
    }
}
