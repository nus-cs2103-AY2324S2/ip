package duke.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.commons.exceptions.DukeException;
import duke.commons.exceptions.TaskDataNotFoundException;
import duke.task.TaskList;

public class PersistentStorageHandler {

    private static final String TASKLIST_PATH = "./tasklist.dat";

    public PersistentStorageHandler() { }

    public boolean ensureTaskFileExists() throws DukeException {
        File file = new File(TASKLIST_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
                return false;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new DukeException("Failed to create persistent task file.");
            }
        }
        return true;
    }

    private TaskList decode(ObjectInputStream ois) throws DukeException {
        try {
            TaskList taskList = (TaskList) ois.readObject();
            return taskList;
        } catch (Exception e) { // todo: look into what exceptions can occur here
            throw new DukeException("Failed to decode: " + TASKLIST_PATH);
        }
    }

    public TaskList readTaskFileFromDisc() throws DukeException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TASKLIST_PATH));
            return decode(ois);
        } catch (FileNotFoundException e) {
            throw new TaskDataNotFoundException("File: " + TASKLIST_PATH + " not found.\nWelcome to your new productivity journey.");
        } catch (Exception e) {
            throw new DukeException("Failed to read file: " + TASKLIST_PATH);
        }
    }

    public void writeTaskFileToDisc(TaskList taskList) throws DukeException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASKLIST_PATH));
            oos.writeObject(taskList);
            oos.close();
        } catch (Exception e) {
            System.out.println(">>: " + e.getMessage());
            throw new DukeException("Failed to write to file: " + TASKLIST_PATH);
        }
    }
}
