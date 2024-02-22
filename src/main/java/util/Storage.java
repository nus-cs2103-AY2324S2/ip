package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * The storage class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filepath = "";
    private File listFile = null;

    /**
     * Constructor for Storage class with filepath.
     *
     * @param filePath of the saved list
     */
    public Storage(String filePath) {
        this.filepath = filePath;
    }

    /**
     * Loads saved tasks from the existing file and returns them in a new task list.
     * If file cannot be found, a new directory with a new file is created and
     * a new empty task list is returned.
     *
     * @return a task list containing previously saved tasks or an empty list.
     * @throws FileNotFoundException if program cannot find the filepath.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            this.listFile = new File(this.filepath);
            Scanner s = new Scanner(this.listFile); // create a Scanner using the File as the source
            // Update the task list with tasks.
            while (s.hasNext()) {
                String str = s.nextLine();
                assert !str.isEmpty() : "str should not be empty";
                char type = str.charAt(4);
                boolean isDone = str.charAt(1) == 'x';
                String task = str.substring(7);
                switch (type) {
                case 'T':
                    taskList.add(new Todo(task, isDone));
                    break;
                case 'D':
                    taskList.add(new Deadline(task, isDone));
                    break;
                case 'E':
                    taskList.add(new Event(task, isDone));
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            // Create new directory and new file.
            new File("./data").mkdirs();
            this.listFile = new File(filepath);
        }
        return taskList;
    }

    /**
     * Saves the tasks into the file when the chatbot exits.
     *
     * @param taskList of tasks to be saved.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.listFile);
            for (Task task : taskList.getTaskList()) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oink! Something went wrong... I can't save it!");
        }
    }
}
