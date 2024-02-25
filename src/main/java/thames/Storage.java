package thames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import thames.task.Deadline;
import thames.task.Event;
import thames.task.Task;

/**
 * Storage class to save and load task lists.
 */
public class Storage {
    /** File path of task list */
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a saved task list in the file path.
     *
     * @return Saved task list.
     * @throws FileNotFoundException If task list cannot be found.
     */
    public Scanner load() throws FileNotFoundException {
        File f = new File(this.filePath);
        return new Scanner(f);
    }

    /**
     * Saves given task list into file path.
     *
     * @param taskList Task list to be saved.
     * @throws IOException If error occurs while saving.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String text = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Event) {
                text += "E,";
                text += task.isDone()? "X,":" ,";
                text += task.getName() + ",";
                text += ((Event) task).getFrom() + ",";
                text += ((Event) task).getTo() + "\n";
            } else if (task instanceof Deadline) {
                text += "D,";
                text += task.isDone()? "X,":" ,";
                text += task.getName() + ",";
                text += ((Deadline) task).getBy() + "\n";
            } else {
                text += "T,";
                text += task.isDone()? "X,":" ,";
                text += task.getName();
                text += "\n";
            }
        }
        fw.write(text);
        fw.close();
    }
}
