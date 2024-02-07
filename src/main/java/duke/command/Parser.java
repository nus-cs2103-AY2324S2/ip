package duke.command;

import duke.tasks.Task;

import java.io.IOException;

public class Parser {

    public Parser() {}

    public int parseInput(String input, Storage storage, TaskList taskList, Ui ui) throws DukeException, IOException {
        input = input + " ";
        String[] command = input.split(" ", 2);

        switch (command[0].toLowerCase()) {
            case "bye":
                return 0;

            case "list":
                ui.printTaskList();
                taskList.printTaskList();
                return -1;

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
                    ui.printMarkTask(t.toString());

                } catch (DukeException de) {
                    ui.printErrorMessage(de.getErrorMessage());
                }
                return -1;

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
                    ui.printUnmarkTask(t.toString());

                } catch (DukeException de) {
                    ui.printErrorMessage(de.getErrorMessage());
                }
                return -1;

            case "todo":
                try {
                    if (command[1].matches("")) {
                        throw new DukeException("Need to check my eyesight, nothing to do.");
                    }

                    Task task = taskList.addTodo(command[1].strip());
                    storage.addNewTask(task);
                    ui.printAddToDo(task.toString(), taskList.getNumOfTasks());

                } catch (DukeException de) {
                    ui.printErrorMessage(de.getErrorMessage());
                }

                return -1;

            case "event":
                try {
                    if (command[1].matches("")) {
                        throw new DukeException("This event is the highlight of the social \"calen-darling.\"" +
                                "\r\nGot all the details?");
                    }

                    String[] event = command[1].split("/to | /from");
                    if (event.length < 3) {
                        throw new DukeException("This event is the highlight of the social \"calen-darling.\"" +
                                "\r\nGot all the details?");
                    }

                    Task task = taskList.addEvent(event[0].strip(), event[1].strip(), event[2].strip());
                    storage.addNewTask(task);
                    ui.printAddEvent(task.toString(), taskList.getNumOfTasks());

                } catch (DukeException de) {
                    ui.printErrorMessage(de.getErrorMessage());
                }

                return -1;

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
                    ui.printAddDeadline(task.toString(), taskList.getNumOfTasks());

                } catch (DukeException de) {
                    ui.printErrorMessage(de.getErrorMessage());
                }

                return -1;

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
                    ui.printDeleteTask(task.toString(), taskList.getNumOfTasks());

                } catch (DukeException de) {
                    ui.printErrorMessage(de.getErrorMessage());
                }

                return -1;

            default:
                ui.printUnknowCommandError(command[0]);
                return -1;
        }
    }
}