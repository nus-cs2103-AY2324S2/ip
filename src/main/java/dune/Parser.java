package dune;

import dune.task.TaskList;

import java.util.Scanner;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private static String[] taskTypes = new String[] {"todo", "deadline", "event"};

    public enum Commands {
        BYE, DELETE, LIST, MARK, UNMARK, FIND, TODO, DEADLINE, EVENT, HELP
    }

    /**
     * Parses the user input and decides what to do.
     *
     * @param scanner The scanner object to read user input.
     * @param taskList An object that represents the list of tasks.
     * @param ui
     * @param storage The storage object to save the tasks to.
     * @return
     */
    public boolean parse(Scanner scanner, TaskList taskList, Ui ui, Storage storage) {
        String text = scanner.nextLine();  // Read user input
        ui.print(response(text, taskList, storage));
        if (text.equals("bye")) {
            return false;
        }
        return true;
    }
    

    /**
     * Decides what to do with the user input.
     * @param text The user input.
     * @param taskList An object that represents the list of tasks.
     * @param storage The storage object to save the tasks to.
     * @return The response to the user input.
     */
    public String response(String text, TaskList taskList, Storage storage) {
        text = text.toLowerCase();
        try {
            String firstWordText = text.split(" ")[0];
            Commands firstWord = Commands.valueOf(firstWordText.toUpperCase());
            switch(firstWord) {
                case BYE:
                    return "Bye. Hope to see you again soon!";
                case DELETE:
                    return taskList.deleteTask(text.substring(Commands.DELETE.name().length()), storage);
                case LIST:
                    return taskList.toString();
                case MARK:
                    try {
                        return markTask(text, taskList, storage);
                    } catch (IndexOutOfBoundsException i) {
                        return "Give a valid index to mark\n";
                    } catch (NumberFormatException n) {
                        return "Remaining characters do not match an integer\n";
                    } catch (DuneException d) {
                        return d.toString();
                    }
                case UNMARK:
                    try {
                        return unmarkTask(text, taskList, storage);
                    } catch (IndexOutOfBoundsException i) {
                        return "Give a valid index to unmark\n";
                    } catch (NumberFormatException n) {
                        return "Remaining characters do not match an integer\n";
                    } catch (DuneException d) {
                        return d.toString();
                    }
                case FIND:
                    return taskList.find(text.substring(Commands.FIND.name().length()).trim());
                case TODO:
                    return taskList.addTask(Commands.TODO,
                            text.substring(Commands.TODO.name().length()).trim(), storage);
                case DEADLINE:
                    return taskList.addTask(Commands.DEADLINE,
                            text.substring(Commands.DEADLINE.name().length()).trim(), storage);
                case EVENT:
                    return taskList.addTask(Commands.EVENT,
                            text.substring(Commands.EVENT.name().length()).trim(), storage);
                case HELP:
                    return "Here are the commands I understand:\n" +
                            "bye\n" +
                            "delete\n" +
                            "list\n" +
                            "mark\n" +
                            "unmark\n" +
                            "find\n" +
                            "todo\n" +
                            "deadline\n" +
                            "event\n";
                default:
                    return "I do not know of this command. Please try again.";
            }
        } catch (Exception e) {
            return "I do not know of this command. Please try again.";
        }

    }

    // Do I need to list everything it can throw? No.

    /**
     * Marks a task as done.
     * @param text The user input.
     * @param taskList An object that represents the list of tasks.
     * @param storage The storage object to save the tasks to.
     * @return The status of the task.
     * @throws DuneException
     */
    public String markTask(String text, TaskList taskList, Storage storage) throws DuneException {
        if (text.equals("mark")) {
            throw new DuneException("Give an index to mark");
        }
        String remaining = "";
        // remove all leading and trailing spaces
        remaining = text.substring("mark".length()).trim();
        // parseInt might throw NumberFormatException
        int index = Integer.parseInt(remaining);
        // Index... exception
        taskList.getTask(index - 1).mark();
        storage.saveTasks(taskList);
        String ans = "Nice! I've marked this task as done:\n";
        ans += taskList.getTask(index - 1) + "\n";
        return ans;
    }

    /**
     * Marks a task as not done.
     *
     * @param text The user input.
     * @param taskList An object that represents the list of tasks.
     * @param storage The storage object to save the tasks to.
     * @return The status of the task.
     * @throws DuneException
     */
    public String unmarkTask(String text, TaskList taskList, Storage storage) throws DuneException {
        if (text.equals("unmark")) {
            throw new DuneException("Give an index to unmark");
        }
        String remaining = "";
        // remove all leading and trailing spaces
        remaining = text.substring("unmark".length()).trim();
        // parseInt might throw NumberFormatException
        int index = Integer.parseInt(remaining);
        // Index... exception
        taskList.getTask(index - 1).unmark();
        storage.saveTasks(taskList);
        String ans = "Rippp. I've marked this task as undone:\n";
        ans += taskList.getTask(index - 1) + "\n";
        ans += "Good luck with your tasks!\n";
        return ans;
    }
}
