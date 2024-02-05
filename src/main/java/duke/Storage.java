package duke;

import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.tasks.Deadline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH = "./data/duke.Duke.txt";

    public Storage() {
    }

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
     * Writes current tasklist into specified file.
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
