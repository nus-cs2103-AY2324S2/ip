package dune;

import dune.task.TaskList;

import java.util.Scanner;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private static String[] taskTypes = new String[] {"todo", "deadline", "event"};

    /**
     * Parses the user input and decides what to do.
     *
     * @param scanner The scanner object to read user input.
     * @param taskList
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

    public String createNewTaskAttempt(String text, TaskList taskList, Storage storage) {
        // System.out.println(text);
        boolean addedTask = false;
        String check;
        for (int i = 0; i < taskTypes.length; i++) {
            check = taskTypes[i];
            if (text.startsWith(check)) {
                addedTask = true;
                try {
                    if (text.equals(check)) {
                        throw new DuneException("The description of a to-do cannot be empty.");
                    }
                    return taskList.addTask(i, text.substring(check.length()).trim(), storage);
                } catch (DuneException d) {
                    return d.toString();
                }
            }
        }
        if (!addedTask) {
            {
                try {
                    // make it more informative LOL
                    throw new DuneException("I do not recognize your command. " +
                            "Tasks can only be of types todo, deadline, or event.");

                } catch (DuneException d) {
                    return d.toString();
                }
            }
        }
        return "";
    }

    public String response(String text, TaskList taskList, Storage storage) {
        if (text.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (text.startsWith("delete")) {
            String ans = taskList.deleteTask(text.substring(6), storage);
            return ans;
        } else if (text.startsWith("list")) {
            return taskList.toString();
        } else if (text.startsWith("mark")) {
            // abstract this into a method
            try {
                if (text.equals("mark")) {
                    throw new DuneException("Give an index to mark");
                }
                String remaining = "";
                // remove all leading and trailing spaces
                remaining = text.substring(4).trim();
                // parseInt might throw NumberFormatException
                int index = Integer.parseInt(remaining);
                // Index... exception
                taskList.getTask(index - 1).mark();
                storage.saveTasks(taskList);
                String ans = "Nice! I've marked this task as done:\n";
                ans += taskList.getTask(index - 1) + "\n";
                return ans;

            } catch (IndexOutOfBoundsException i) {
                return "Give a valid index to mark\n";
            } catch (NumberFormatException n) {
                return "Remaining characters do not match an integer\n";
            } catch (DuneException d) {
                return d.toString();
            }
        } else if (text.startsWith("find")) {
            return taskList.find(text.substring(4).trim());
        } else {
            return createNewTaskAttempt(text, taskList, storage);
        }
    }
}
