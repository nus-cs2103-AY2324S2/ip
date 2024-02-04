import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Parser {
    /**
     * Takes in a user query and performs the necessary reading and writing to a file on the drive.
     * The file should exist, and is by default handled by BadPingGuo
     * @param s the query the user would like to make
     * @param file the file to read or write from
     */
    public static void ProcessQuery(String s, File file) throws IOException {
        String[] tokens = s.split(" ");
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(tokens));

        switch (args.get(0).toLowerCase()) {
            case "list":
                TaskList.listTasks(new BufferedReader(new FileReader(file)));
                return;
            case "mark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = TaskList.tasks.get(taskIndex);
                    t.mark(true, taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println("Usage: mark <taskNumber>");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Hey you don't have that task!");
                }
                break;
            case "unmark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = TaskList.tasks.get(taskIndex);
                    t.mark(false, taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println("Usage: mark <taskNumber>");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Calm down! You don't have THAT many tasks!");
                }
                break;
            case "todo":
                // it is possible to relegate exception handling to addTask
                // you must use fp and implement lazy evaluation
                try {
                    Storage.addTask(Todo.extractDetails(s));
                } catch (BadAppleException be) {
                    System.out.println(be);
                }
                break;
            case "deadline":
                try {
                    Storage.addTask(Deadline.extractDetails(args));
                } catch (BadAppleException be) {
                    System.out.println(be);
                } catch (DateTimeParseException dateError) {
                    System.out.println("Date should be in YYYY-MM-DD format");
                }
                break;
            case "event":
                try {
                    Storage.addTask(Event.extractDetails(args));
                } catch (BadAppleException be) {
                    System.out.println(be);
                }
                break;
            case "delete":
                if (tokens.length <= 1) {
                    System.out.println("Kel nuked, but he missed what task you wanted to remove!");
                    break;
                }
                int taskIndex;
                try {
                    taskIndex = parseInt(tokens[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Usage: delete <taskNumber>");
                    break;
                }
                if (taskIndex >= 0 && taskIndex < TaskList.tasks.size()) {
                    Storage.removeTask(taskIndex);
                } else {
                    System.out.println("welcome to BLACK SPACE, you keyed in a non-existent task!");
                }
                break;
            default:
                System.out.println("Whatcha sayin? scream 'help!' for list of my services");
                return;
        }
        Storage.updateTasks(file);
    }
}
