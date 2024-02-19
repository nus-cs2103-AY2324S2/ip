package bozo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage with the specified file path.
     *
     * @param filePath The file path of the file to be saved.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> loadFile() {
        assert Files.isReadable(Paths.get(filePath)) : "File must be readable";
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
        assert list != null : "List cannot be null";
        return list;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param list The list of tasks to be saved.
     */
    public void saveList(TaskList list) {
        assert Files.isWritable(Paths.get(filePath)) : "File must be writable";
        try {
            File txtFile = new File(filePath);
            txtFile.getParentFile().mkdirs();
            FileWriter f = new FileWriter(txtFile);
            assert list != null : "List cannot be null";
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
