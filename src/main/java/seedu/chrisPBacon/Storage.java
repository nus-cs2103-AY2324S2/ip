package seedu.chrisPBacon;

import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Task;
import seedu.task.TaskList;
import seedu.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The storage class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filepath = "";
    private File listFile = null;

    public Storage(String filePath) {
        this.filepath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            this.listFile = new File(this.filepath);
            Scanner s = new Scanner(this.listFile); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String str = s.nextLine();
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
                }
            }
        } catch (FileNotFoundException e) {
            new File("./data").mkdirs();
            File dataFile = new File(filepath);
        }
        return taskList;
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.listFile);
            for (Task task : taskList.getTasks()) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oink! Something went wrong... I can't save it!");
        }
    }
}