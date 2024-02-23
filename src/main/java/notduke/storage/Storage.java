package notduke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import notduke.notdukeexception.NotDukeNoSaveFile;
import notduke.notdukeexception.NotDukeSaveError;
import notduke.task.Deadlines;
import notduke.task.Events;
import notduke.task.Task;
import notduke.task.ToDos;
import notduke.tasklist.TaskList;

/**
 * Facilitates the loading and saving of TaskList data during start up and closing of NotDuke.
 */
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
     * @throws NotDukeNoSaveFile if savefile cannot be found
     */
    public ArrayList<Task> load() throws NotDukeNoSaveFile {
        ArrayList<Task> saveFile = new ArrayList<>();

        try {
            Scanner s = new Scanner(filePath);
            while (s.hasNextLine()) {
                String currLine = s.nextLine();
                Task savedTask = createTask(currLine);
                if (savedTask == null) {
                    continue;
                }
                saveFile.add(savedTask);
            }
            return saveFile;
        } catch (FileNotFoundException e) {
            throw new NotDukeNoSaveFile();
        }
    }

    private Task createTask(String savedTask) {
        String[] inputs = savedTask.split(" \\| ");
        boolean isDone = !inputs[1].equals("0");
        Task task = null;

        switch (inputs[0]) {
        case "T":
            task = new ToDos(inputs[2], isDone);
            break;
        case "D":
            task = new Deadlines(inputs[2], LocalDateTime.parse(inputs[3]), isDone);
            break;
        case "E":
            String[] time = inputs[3].split("/");
            task = new Events(inputs[2], LocalDateTime.parse(time[0]),
                    LocalDateTime.parse(time[1]), isDone);
            break;
        default:
            break;
        }
        return task;
    }

    /**
     * Saves data of TaskList in this session to the target savefile location.
     * @param taskList the TaskList of the session
     * @throws NotDukeSaveError if file cannot be saved
     */
    public void save(TaskList taskList) throws NotDukeSaveError {
        try {
            filePath.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(filePath);
            fw.write(taskList.saveFormat());
            fw.close();
        } catch (IOException e) {
            throw new NotDukeSaveError();
        }
    }
}
