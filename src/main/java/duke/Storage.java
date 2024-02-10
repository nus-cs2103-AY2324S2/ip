package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

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
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(this.file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String str = s.nextLine();
            char type = str.charAt(4);
            boolean isDone = str.charAt(1) == 'x';
            String task = str.substring(7);
            switch (type) {
                case 'T':
                    tasks.add(new Todo(task, isDone));
                    break;
                case 'D':
                    tasks.add(new Deadline(task, isDone));
                    break;
                case 'E':
                    tasks.add(new Event(task, isDone));
                    break;
            }
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks.getTasks()) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oink! Something went wrong... I can't save it!");
        }
    }
}