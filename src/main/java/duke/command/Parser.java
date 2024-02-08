package duke.command;

import java.io.IOException;

import duke.tasks.Task;

/**
 * Handles reading user input and calling the relevant commands.
 */
public class Parser {

    /**
     * Parser constructor.
     */
    public Parser() {
    }

    /**
     * Parses user input and calls the relevant commands.
     *
     * @param input             User command input.
     * @param storage           Storage instance.
     * @param taskList          TaskList instance.
     * @param ui                Ui instance.
     * @return uiString         Result of parsing user input.
     * @throws IOException      If storage to file is unsuccessful.
     */
    public String parseInput(String input, Storage storage, TaskList taskList, Ui ui) throws IOException {
        input = input + " ";
        String[] command = input.split(" ", 2);

        switch (command[0].toLowerCase()) {
        case "bye":
            return ui.printExitMessage();

        case "list":
            return ui.printTaskList(taskList.printTaskList());

        case "mark":
            try {
                if (command[1].matches("")) {
                    throw new DukeException("Missing index, what to mark?");
                }

                int index = Integer.parseInt(command[1].strip());
                int numOfTasks = taskList.getNumOfTasks();
                if (((index - 1) < 0) || (index > numOfTasks)) {
                    throw new DukeException("Index out of bounds, no task found.");
                }

                Task t = taskList.markTask(index - 1);
                storage.updateTask(t, index, numOfTasks);
                return ui.printMarkTask(t.toString());

            } catch (DukeException de) {
                return ui.printErrorMessage(de.getErrorMessage());
            }

        case "unmark":
            try {
                if (command[1].matches("")) {
                    throw new DukeException("Missing index, what to unmark?");
                }

                int index = Integer.parseInt(command[1].strip());
                int numOfTasks = taskList.getNumOfTasks();
                if (((index - 1) < 0) || (index > numOfTasks)) {
                    throw new DukeException("Index out of bounds, no task found.");
                }

                Task t = taskList.unmarkTask(index - 1);
                storage.updateTask(t, index, numOfTasks);
                return ui.printUnmarkTask(t.toString());

            } catch (DukeException de) {
                return ui.printErrorMessage(de.getErrorMessage());
            }

        case "todo":
            try {
                if (command[1].matches("")) {
                    throw new DukeException("Need to check my eyesight, nothing to do.");
                }

                Task task = taskList.addTodo(command[1].strip());
                storage.addNewTask(task);
                return ui.printAddTask(task.toString(), taskList.getNumOfTasks());

            } catch (DukeException de) {
                return ui.printErrorMessage(de.getErrorMessage());
            }

        case "event":
            try {
                if (command[1].matches("")) {
                    throw new DukeException("This event is the highlight of the social \"calen-darling.\""
                            + "\r\nGot all the details?");
                }

                String[] event = command[1].split("/to | /from");
                if (event.length < 3) {
                    throw new DukeException("This event is the highlight of the social \"calen-darling.\""
                            + "\r\nGot all the details?");
                }

                Task task = taskList.addEvent(event[0].strip(), event[1].strip(), event[2].strip());
                storage.addNewTask(task);
                return ui.printAddTask(task.toString(), taskList.getNumOfTasks());

            } catch (DukeException de) {
                return ui.printErrorMessage(de.getErrorMessage());
            }

        case "deadline":
            try {
                if (command[1].matches("")) {
                    throw new DukeException("Invalid input/syntax. What is due?");
                }

                String[] d = command[1].split("/by");
                if (d.length < 2) {
                    throw new DukeException("To survive is to procrastinate death, when is this due?");
                }

                Task task = taskList.addDeadline(d[0].strip(), d[1].strip());
                storage.addNewTask(task);
                return ui.printAddTask(task.toString(), taskList.getNumOfTasks());

            } catch (DukeException de) {
                return ui.printErrorMessage(de.getErrorMessage());
            }


        case "delete":
            try {
                if (command[1].matches("")) {
                    throw new DukeException("Missing the target with your input, what to remove?");
                }

                int index = Integer.parseInt(command[1].strip());
                int numOfTasks = taskList.getNumOfTasks();
                if (((index - 1) < 0) || (index > numOfTasks)) {
                    throw new DukeException("Index out of bounds.");
                }

                Task task = taskList.deleteTask(index - 1);
                storage.deleteTask(index - 1, numOfTasks);
                return ui.printDeleteTask(task.toString(), taskList.getNumOfTasks());

            } catch (DukeException de) {
                return ui.printErrorMessage(de.getErrorMessage());
            }


        case "find":
            try {
                if (command[1].matches("")) {
                    throw new DukeException("Invalid input, missing search term");
                }

                return ui.printFindTask(taskList.findMatchingTasks(command[1].strip()));
            } catch (DukeException de) {
                return ui.printErrorMessage(de.getErrorMessage());
            }

        default:
            return ui.printUnknownCommandError(command[0]);
        }
    }
}
