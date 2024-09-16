package duke.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.DeadlineTask;
import duke.tasks.DoAfterTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;

/**
 * The FileParser class handles the parsing of a file containing task data.
 */
public class FileParser {
    private static final String separator = ",";

    /**
     * Parses a datetime string into a LocalDateTime object.
     *
     * @param input the datetime string to parse
     * @return the parsed LocalDateTime object
     */
    private static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return dateTime;
    }

    /**
     * Reads tasks from a file and returns them as a list of Task objects.
     *
     * @param f the file to read tasks from
     * @return the list of Task objects read from the file
     * @throws FileNotFoundException if the file is not found
     */
    public static ArrayList<Task> readFile(File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] parts = s.split(separator);
            // The first part of the line in the data file will indicate which task type it is.
            switch (parts[0]) {
            case "T":
                assert parts.length == 3 : "Todo task creation should have 3 parts";
                Task t = new TodoTask(parts[2], parts[1]);
                tasks.add(t);
                break;
            case "D":
                assert parts.length == 4 : "Deadline task creation should have 4 parts";
                Task d = new DeadlineTask(parts[2], parts[1], parseDateTime(parts[3]));
                tasks.add(d);
                break;
            case "E":
                assert parts.length == 5 : "Event task creation should have 5 parts";
                Task e = new EventTask(parts[2], parts[1], parseDateTime(parts[3]), parseDateTime(parts[4]));
                tasks.add(e);
                break;
            case "A":
                assert parts.length == 4 : "DoAfter task creation should have 4 parts";
                Task a = new DoAfterTask(parts[2], parts[1], Integer.valueOf(parts[3]));
                tasks.add(a);
                break;
            default:
                break;
            }
        }
        scanner.close();
        return tasks;
    }
}
