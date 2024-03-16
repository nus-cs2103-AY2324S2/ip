package BadApple.task;

import BadApple.main.BadPingGuo;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static BadApple.task.Parser.CMD_LIST;

/*
@@author Lim Zheng Ting,
GitHub source : https://github.com/AL-ZT/ip/blob/branch-A-Assertions/src/main/java/duke/Command.java
Adapted idea of using a command class to abstract away Long Parser methods.
 */

/**
 * A standard Command class to capture valid input contents
 * allowing for execution when needed
 */
public class Command {
    private final String command;
    private final String query;
    private final String[] args;

    Command(String c, String q) {
        command = c;
        query = q;
        args = query.split(" ",2);
    }


    /**
     * Depending on the string in this command, execute it
     * including necessary read, write operations to the whiteSpace file
     * @return the reply to give to the user
     * @throws IOException if the file can't be read or written to
     */
    public String execute() throws IOException {
        String reply;
        switch (command.toLowerCase()) {
            case "list":
                reply = TaskList.listTasks();
                break;
            case "mark":
                reply = TaskList.markTask(true, args[1]);
                break;
            case "unmark":
                reply = TaskList.markTask(false, args[1]);
                break;
            case "delete":
                reply = TaskList.removeTask(args[1].trim());
                break;
            case "find":
                reply = TaskList.filterTasks(args[1].trim());
                break;
            case "!help":
                reply = "try one of these " + CMD_LIST.keySet();
                break;
            case "bye":
                reply = "Can't wait to see you again in headspace!";
                break;
            case "update":
                try {
                    reply = TaskList.updateTask(args[1].trim());
                } catch (DateTimeParseException d) {
                    reply = "Your date should be in YYYY-MM-DD format, let's try again!";
                }
                break;
            default:
                try {
                    reply = TaskList.addTask(command, query);
                } catch (DateTimeParseException d) {
                    reply = "Your date should be in YYYY-MM-DD format, let's try again!";
                }
        }

        Storage.updateTasks(new File(BadPingGuo.FILENAME));
        return reply;
    }
}
