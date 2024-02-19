package bozo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the tasks.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> loadFile() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File txtFile = new File(filePath);
            Scanner sc = new Scanner(txtFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseTask(line);
                assert(task != null) : "Task should not be null";
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No tasks saved :-((");
        }
        return list;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param list The list of tasks to be saved.
     */
    public void saveList(TaskList list) {
        try {
            File txtFile = new File(filePath);
            txtFile.getParentFile().mkdirs();
            FileWriter f = new FileWriter(txtFile);
            for (Task task : list) {
                f.write(task.save());
                f.write(System.lineSeparator());
            }
            f.close();
        } catch (IOException e) {
            System.out.println("I can't save ur list :((");
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        int i = Integer.parseInt(parts[1]);
        String s = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = createTodoTask(i, s);
            break;
        case "D":
            task = createDeadlineTask(i, s, parts[3]);
            break;
        case "E":
            task = createEventTask(i, s, parts[3], parts[4]);
            break;
        }
        return task;
    }

    private Todo createTodoTask(int i, String s) {
        Todo td = new Todo(s);
        setDone(td, i);
        return td;
    }

    private Deadline createDeadlineTask(int i, String s, String by) {
        Deadline d = new Deadline(s, by);
        setDone(d, i);
        return d;
    }

    private Event createEventTask(int i, String s, String from, String to) {
        Event e = new Event(s, from, to);
        setDone(e, i);
        return e;
    }

    private void setDone(Task task, int i) {
        if (i == 1) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }
}

