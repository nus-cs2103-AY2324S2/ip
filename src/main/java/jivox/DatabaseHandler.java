package jivox;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import jivox.exception.DataHandlerException;
import jivox.task.Deadline;
import jivox.task.Event;
import jivox.task.Task;
import jivox.task.TaskList;
import jivox.task.Todo;

/**
 * DatabaseHandler handles saving and loading tasks from a file database.
 */
public class DatabaseHandler {
    private File db = null;

    /**
     * Creates the handler for the given file path.
     *
     * @param filePath The path to the database file.
     */
    public DatabaseHandler(String filePath) {
        this.db = new File(filePath);
    }

    /**
     * Creates the database file if it doesn't exist.
     *
     * @throws DataHandlerException If there is an error creating the file.
     */
    public void create() throws DataHandlerException {
        Path path = Paths.get(db.getPath());
        try {
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new DataHandlerException(e.getMessage());
        }
    }

    /**
     * Saves the given task list to the database.
     *
     * @param tasks The task list to save.
     * @throws DataHandlerException If there is an error writing to the file.
     */
    public void save(TaskList tasks) throws DataHandlerException {
        assert tasks != null;
        try {
            if (!db.exists()) {
                create();
            }
            FileWriter fw = new FileWriter(db);
            for (int i = 0; i < tasks.getLength(); i++) {
                Task task = tasks.getTask(i);
                fw.write(task.saveFormat());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DataHandlerException(e.getMessage());
        }
    }

    /**
     * Loads the tasks from the database file.
     *
     * @return The list of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            if (!db.exists()) {
                return list;
            }
            FileReader fr = new FileReader(db);
            Scanner sc = new Scanner(fr);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split("\\|");
                switch (split[0].trim()) {
                case "D":
                    Deadline d = new Deadline(split[2].trim(),
                                LocalDateTime.parse(split[3].replaceFirst(" ", ""), formatter));
                    if (split[1].trim().equals("1")) {
                        d.mark();
                    }
                    list.add(d);
                    break;
                case "E":
                    String[] startend = split[3].split(" to ");
                    Event e = new Event(split[2].trim(),
                            LocalDateTime.parse(startend[0].replaceFirst(" ", ""), formatter),
                            LocalDateTime.parse(startend[1], formatter));
                    if (split[1].trim().equals("1")) {
                        e.mark();
                    }
                    list.add(e);
                    break;
                case "T":
                    Todo t = new Todo(split[2].trim());
                    if (split[1].trim().equals("1")) {
                        t.mark();
                    }
                    list.add(t);
                    break;
                default:
                    System.out.println("Invalid Entry");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }


}
