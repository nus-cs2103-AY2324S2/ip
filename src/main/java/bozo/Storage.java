package bozo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
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
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                int i = Integer.parseInt(parts[1]);
                String s = parts[2];

                switch (type) {
                case "T":
                    Todo td = new Todo(s);
                    if (i == 1) {
                        td.isDone = true;
                    } else {
                        td.isDone = false;
                    }
                    list.add(td);
                    break;
                case "D":
                    Deadline d = new Deadline(s, parts[3]);
                    if (i == 1) {
                        d.isDone = true;
                    } else {
                        d.isDone = false;
                    }
                    list.add(d);
                    break;
                case "E":
                    Event e = new Event(s, parts[3], parts[4]);
                    if (i == 1) {
                        e.isDone = true;
                    } else {
                        e.isDone = false;
                    }
                    list.add(e);
                    break;
                }
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

}
