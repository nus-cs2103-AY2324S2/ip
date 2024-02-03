package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

<<<<<<< HEAD
import duke.dukeexception.DukeNoSaveFile;
import duke.dukeexception.DukeSaveError;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.tasklist.TaskList;

=======
/**
 * Facilitates the loading and saving of TaskList data during start up and closing of NotDuke.
 */
>>>>>>> branch-A-JavaDoc
public class Storage {
    /** The file to load and save to. */
    private final File filePath;

    /**
     * Constructs tne storage controller with the given String as the target file location.
     * @param path The location to read and save the savefile
     */
    public Storage(String path) {
        filePath = new File(path);
    }

    /**
     * Returns an ArrayList containing all the tasks saved in the savefile.
     * @return an ArrayList of tasks
     * @throws DukeNoSaveFile if savefile cannot be found
     */
    public ArrayList<Task> load() throws DukeNoSaveFile {
        ArrayList<Task> saveFile = new ArrayList<>();
        try {
            Scanner s = new Scanner(filePath);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] inputs = line.split(" \\| ");
                boolean isDone = !inputs[1].equals("0");
                Task task;
                switch (inputs[0]) {
                case "T":
                    task = new ToDos(inputs[2], isDone);
                    saveFile.add(task);
                    break;
                case "D":
                    task = new Deadlines(inputs[2], LocalDateTime.parse(inputs[3]), isDone);
                    saveFile.add(task);
                    break;
                case "E":
                    String[] time = inputs[3].split("/");
                    task = new Events(inputs[2], LocalDateTime.parse(time[0]),
                            LocalDateTime.parse(time[1]), isDone);
                    saveFile.add(task);
                    break;
                default:
                    break;
                }
            }
            return saveFile;
        } catch (FileNotFoundException e) {
            throw new DukeNoSaveFile();
        }
    }

    /**
     * Saves data of TaskList in this session to the target savefile location.
     * @param taskList the TaskList of the session
     * @throws DukeSaveError if file cannot be saved
     */
    public void save(TaskList taskList) throws DukeSaveError {
        try {
            filePath.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(filePath);
            fw.write(taskList.saveFormat());
            fw.close();
        } catch (IOException e) {
            throw new DukeSaveError();
        }
    }
}
