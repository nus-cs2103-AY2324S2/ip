package pookie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pookie.tasks.Deadline;
import pookie.tasks.Event;
import pookie.tasks.Task;
import pookie.tasks.TaskList;
import pookie.tasks.ToDo;

/**
 * Represents the storage of the task list.
 */
public class Storage {
    /**
     * The file path of the file to be written to.
     */
    public static final String FILE_PATH = "./data/pookie.Pookie.txt";

    /**
     * Constructor for the storage of the task list.
     */
    public Storage() {
    }

    /**
     * Loads the contents of the file into the task list.
     *
     * @param list The task list to be loaded into.
     * @throws PookieException If there is a problem with reading the file.
     */
    public static void loadFileContents(TaskList list) throws PookieException {
        File f = new File(FILE_PATH);
        assert list != null : "Task list should not be null";
        try {
            if (f.exists()) {
                try (Scanner scanner = new Scanner(f)) {
                    while (scanner.hasNextLine()) {
                        String s = scanner.nextLine();
                        try {
                            loadLine(s, list);
                        } catch (PookieException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            } else {
                throw new PookieException("eh walao i cant find the file sia where u put");
            }
        } catch (IOException e) {
            throw new PookieException("omg i cant read");
        }
    }

    /**
     * Loads a line from the file.
     *
     * @param original The original line from the file.
     * @param list     The task list to be loaded into.
     * @throws PookieException If there is a problem with loading the line.
     */
    public static void loadLine(String original, TaskList list) throws PookieException {
        assert list != null : "Task list should not be null";
        String[] inputParts = original.split("\\s+");

        switch (inputParts[0]) {
            case ("todo"):
                String description = original.replace("todo", "");
                if (description.isEmpty()) {
                    throw new PookieException("oi todo what. todo WHATTTTTT!!!!!!!!");
                }
                Task task = new ToDo(description);
                list.addTask(task);
                break;
            case ("deadline"):
                String[] parts = original.replace("deadline", "").split(" /");
                Task deadlineTask = new Deadline(parts[0], parts[1].replace("by ", ""));
                list.addTask(deadlineTask);
                break;
            case ("event"):
                String[] parts2 = original.replace("event", "").split(" /");
                Task eventTask = new Event(parts2[0], parts2[1].replace("from ", ""), parts2[2].replace("to ", ""));
                list.addTask(eventTask);
                break;
            case ("delete"):
                int inputInt = Integer.parseInt(inputParts[1]);
                list.deleteTask(inputInt);
                break;
            default:
                throw new PookieException("harh what u talking sia walao");
        }
    }

    /**
     * Writes current task list into specified file.
     *
     * @throws PookieException If there is a problem with writing into file.
     */
    public static void writeToFile(TaskList list) throws PookieException {
        assert list != null : "Task list should not be null";
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < list.getSize(); i++) {
                fw.write(list.getTask(i).writeToFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new PookieException("omg i cant write sia :( too bad lol");
        }
    }

}
