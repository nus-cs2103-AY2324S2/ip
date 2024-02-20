package BadApple.task;

import BadApple.main.BadAppleException;
import BadApple.main.BadPingGuo;

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
     */
    public static String ProcessQuery(String s) throws IOException {
        String[] tokens = s.split(" ");
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(tokens));

        String reply = "DEFAULT";
        assert(false);
        switch (args.get(0).toLowerCase()) {
            case "list":
                return TaskList.listTasks(new BufferedReader(new FileReader(BadPingGuo.FILENAME)));
            case "mark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = TaskList.tasks.get(taskIndex);
                    reply = t.mark(true, taskIndex);
                } catch (NumberFormatException e) {
                    reply = "Usage: mark <taskNumber>";
                    System.out.println(reply);
                } catch (IndexOutOfBoundsException e) {
                    reply = "Hey you don't have that task!";
                    System.out.println(reply);
                }
                break;
            case "unmark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = TaskList.tasks.get(taskIndex);
                    reply = t.mark(false, taskIndex);
                } catch (NumberFormatException e) {
                    reply = "Usage: mark <taskNumber>";
                    System.out.println(reply);
                } catch (IndexOutOfBoundsException e) {
                    reply = "Calm down! You don't have THAT many tasks!";
                    System.out.println(reply);
                }
                break;
            case "todo":
                try {
                    int before = TaskList.tasks.size();
                    reply = Storage.addTask(Todo.extractDetails(s));
                    int after = TaskList.tasks.size();
                    assert (after == before + 1);
                } catch (BadAppleException be) {
                    System.out.println(be);
                    reply = be.toString();
                }
                break;
            case "deadline":
                try {
                    reply = Storage.addTask(Deadline.extractDetails(args));
                } catch (BadAppleException be) {
                    System.out.println(be);
                    reply = be.toString();
                } catch (DateTimeParseException dateError) {
                    reply = "Help me by phrasing the deadline in YYYY-MM-DD format";
                    System.out.println(reply);
                }
                break;
            case "event":
                try {
                    reply = Storage.addTask(Event.extractDetails(args));
                } catch (BadAppleException be) {
                    System.out.println(be);
                    reply = be.toString();
                }
                break;
            case "delete":
                if (tokens.length <= 1) {
                    reply = "Kel nuked, but he missed what task you wanted to remove!";
                    System.out.println(reply);
                    break;
                }
                int taskIndex;
                try {
                    taskIndex = parseInt(tokens[1]) - 1;
                } catch (NumberFormatException e) {
                    reply = "Usage: delete <taskNumber>";
                    break;
                }
                if (taskIndex >= 0 && taskIndex < TaskList.tasks.size()) {
                    reply = Storage.removeTask(taskIndex);
                } else {
                    reply = "welcome to BLACK SPACE, you keyed in a non-existent task!";
                }
                break;
            case "find":
                if (tokens.length <= 1) {
                    System.out.println("There is nothing here... to find");
                    break;
                }
                reply = TaskList.filterTasks(s.substring(5).trim());
                break;
            default:
                reply = "Whatcha sayin? scream 'help!' for list of my services";
        }
        Storage.updateTasks(new File(BadPingGuo.FILENAME));
        return reply;
    }
}
