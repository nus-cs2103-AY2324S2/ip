package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Represents the storage of the task list.
 */
public class Storage {
    /**
     * The file path of the file to be written to.
     */
    public static final String FILE_PATH = "./data/duke.Duke.txt";

    /**
     * Constructor for the storage of the task list.
     */
    public Storage() {
    }

    /**
     * Loads the contents of the file into the task list.
     *
     * @param list The task list to be loaded into.
     * @throws DukeException If there is a problem with reading the file.
     */
    public static void loadFileContents(TaskList list) throws DukeException {
        File f = new File(FILE_PATH);
        try {
            if (f.exists()) {
                try (Scanner scanner = new Scanner(f)) {
                    while (scanner.hasNextLine()) {
                        String s = scanner.nextLine();
                        try {
                            loadLine(s, list);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            } else {
                throw new DukeException("eh walao i cant find the file sia where u put");
            }
        } catch (IOException e) {
            throw new DukeException("omg i cant read");
        }
    }

    /**
     * Loads a line from the file.
     *
     * @param original The original line from the file.
     * @param list     The task list to be loaded into.
     * @throws DukeException If there is a problem with loading the line.
     */
    public static void loadLine(String original, TaskList list) throws DukeException {
        String[] inputParts = original.split("\\s+");

        if (inputParts[0].equals("todo")) {
            //handle "todoo"
            String description = original.replace("todo", "");
            if (description.isEmpty()) {
                throw new DukeException("oi todo what. todo WHATTTTTT!!!!!!!!");
            }
            Task task = new ToDo(description);
            list.addTask(task);
        } else if (inputParts[0].equals("deadline")) {
            //handle "deadline"
            String[] parts = original.replace("deadline", "").split(" /");
            Task task = new Deadline(parts[0], parts[1].replace("by ", ""));
            list.addTask(task);
        } else if (inputParts[0].equals("event")) {
            //handle event
            String[] parts = original.replace("event", "").split(" /");
            Task task = new Event(parts[0], parts[1].replace("from ", ""), parts[2].replace("to ", ""));
            list.addTask(task);
        } else if (inputParts[0].equals("delete")) {
            //handle delete
            int inputInt = Integer.parseInt(inputParts[1]);
            list.deleteTask(inputInt);
        } else {
            throw new DukeException("harh what u talking sia walao");
        }

    }

    /**
     * Writes current task list into specified file.
     *
     * @throws DukeException If there is a problem with writing into file.
     */
    public static void writeToFile(TaskList list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < list.getSize(); i++) {
                fw.write(list.getTask(i).writeToFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("omg i cant write sia :( too bad lol");
        }
    }

}
