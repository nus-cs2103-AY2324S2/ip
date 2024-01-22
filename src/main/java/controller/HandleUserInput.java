package controller;

import model.*;
import view.MarkTaskView;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;

public class HandleUserInput {
    private final String input;
    private final ArrayList<Task> taskList;
    public HandleUserInput(String input, ArrayList<Task> taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    public void execute() {
        String[] splitTask = input.split(" ", 2);
        Task task;
        AddTask addTaskController;
        switch(splitTask[0]) {
            case "list":
                ListTask listTaskController = new ListTask(taskList);
                listTaskController.execute();
                return;
            case "mark":
                try {
                    MarkTask markTaskController = parseMark(splitTask);
                    markTaskController.execute();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            case "unmark":
                try {
                    UnmarkTask unmarkTaskController = parseUnmark(splitTask);
                    unmarkTaskController.execute();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            case "todo":
                try {
                    task = parseToDo(splitTask);
                    addTaskController = new AddTask(task, taskList);
                    addTaskController.execute();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            case "event":
                try {
                    task = parseEvent(splitTask);
                    addTaskController = new AddTask(task, taskList);
                    addTaskController.execute();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            case "deadline":
                try {
                    task = parseDeadline(splitTask);
                    addTaskController = new AddTask(task, taskList);
                    addTaskController.execute();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            default:
                InvalidCommand invalidCommand = new InvalidCommand();
                invalidCommand.execute();
        }
    }

    private static ToDo parseToDo(String[] todo) throws DukeException {
        if (todo.length != 2 || todo[1].isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new ToDo(todo[1]);
    }

    private static Event parseEvent(String[] event) throws DukeException {
        if (event.length != 2 || event[1].isEmpty()) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] splitEvent = event[1].split(" /from ", 2);
        if (splitEvent.length != 2) {
            throw new DukeException("Invalid input. Use: event event_title /from start_time /to end_time");
        }
        String[] splitDuration = splitEvent[1].split(" /to ", 2);
        if (splitDuration.length != 2) {
            throw new DukeException("Invalid input. Use: event_title /from start_time /to end_time");
        }

        String title = splitEvent[0];
        String from = splitDuration[0];
        String to = splitDuration[1];

        return new Event(title, from, to);
    }

    private static Deadline parseDeadline(String[] deadline) throws DukeException {
        if (deadline.length != 2 || deadline[1].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] splitDeadline = deadline[1].split(" /by ", 2);
        if (splitDeadline.length != 2) {
            throw new DukeException("Invalid input. Use: deadline deadline_title /by date");
        }
        String title = splitDeadline[0];
        String time = splitDeadline[1];
        return new Deadline(title, time);
    }

    private MarkTask parseMark(String[] command) throws DukeException {
        try {
            int markIndex = Integer.parseInt(command[1]) - 1;
            try {
                return new MarkTask(markIndex, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index of task cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The index you've input is not an integer.");
        }
    }
    private UnmarkTask parseUnmark(String[] command) throws DukeException {
        try {
            int markIndex = Integer.parseInt(command[1]) - 1;
            try {
                return new UnmarkTask(markIndex, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index of task cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The index you've input is not an integer.");
        }
    }

}
