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

import jivox.exception.JivoxDatabaseException;
import jivox.task.Deadline;
import jivox.task.Event;
import jivox.task.Tag;
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
     * @throws JivoxDatabaseException If there is an error creating the file.
     */
    public void create() throws JivoxDatabaseException {
        Path path = Paths.get(db.getPath());
        try {
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new JivoxDatabaseException();
        }
    }

    /**
     * Saves the given task list to the database.
     *
     * @param tasks The task list to save.
     * @throws JivoxDatabaseException If there is an error writing to the file.
     */
    public void save(TaskList tasks) throws JivoxDatabaseException {
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
            throw new JivoxDatabaseException();
        }
    }

    /**
     * Loads the tasks from the database file.
     *
     * @return The list of tasks loaded from the file.
     */
    public ArrayList<Task> load() throws JivoxDatabaseException {
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
                    Deadline d = getDeadline(split, formatter);
                    list.add(d);
                    break;
                case "E":
                    Event e = getEvent(split, formatter);
                    list.add(e);
                    break;
                case "T":
                    String[] split2 = split[2].split(" tag ");
                    Todo t = new Todo(split2[0].trim());
                    if (split[1].trim().equals("1")) {
                        t.mark();
                    }
                    list.add(t);
                    if (!split2[1].equals("None")) {
                        t.setTag(new Tag(split2[1].trim()));
                    }
                    break;
                default:
                    throw new JivoxDatabaseException();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    private static Deadline getDeadline(String[] split, DateTimeFormatter formatter) {
        String[] tagSplit = split[3].split(" tag ");
        String deadline = tagSplit[0].replaceFirst(" ", "")
                .replaceFirst("\\s++$", "");
        Deadline d = new Deadline(split[2].trim(),
                    LocalDateTime.parse(deadline, formatter));
        if (split[1].trim().equals("1")) {
            d.mark();
        }
        if (!tagSplit[1].equals("None")) {
            d.setTag(new Tag(tagSplit[1].trim()));
        }
        return d;
    }

    private static Event getEvent(String[] split, DateTimeFormatter formatter) {
        String[] startend = split[3].split(" to ");
        String[] endtag = startend[1].split(" tag ");
        String endDate = endtag[0];
        System.out.println(endDate);
        Event e = new Event(split[2].trim(),
                LocalDateTime.parse(startend[0].replaceFirst(" ", ""), formatter),
                LocalDateTime.parse(endDate, formatter));
        if (split[1].trim().equals("1")) {
            e.mark();
        }
        if (!endtag[1].equals("None")) {
            e.setTag(new Tag(endtag[1].trim()));
        }
        return e;
    }


}
