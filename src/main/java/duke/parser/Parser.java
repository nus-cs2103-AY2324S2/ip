package duke.parser;

import duke.DukeException;
import duke.task.*;
import duke.ui.Ui;

public class Parser {

    public static void parse(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] parts = userInput.trim().split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
                ui.showGoodbye();
                System.exit(0);
                break;
            case "list":
                tasks.listTasks();
                break;
            case "mark":
                if (parts.length < 2) throw new DukeException("Mark command needs a task number.");
                int markIndex = Integer.parseInt(parts[1]) - 1;
                tasks.markTask(markIndex + 1);
                break;
            case "unmark":
                if (parts.length < 2) throw new DukeException("Unmark command needs a task number.");
                int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                tasks.unmarkTask(unmarkIndex + 1);
                break;
            case "delete":
                if (parts.length < 2) throw new DukeException("Delete command needs a task number.");
                int deleteIndex = Integer.parseInt(parts[1]) - 1;
                tasks.deleteTask(deleteIndex + 1);
                break;
            case "todo":
            case "deadline":
            case "event":
                handleTaskCreation(command, parts, tasks, ui);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void handleTaskCreation(String command, String[] parts, TaskList tasks, Ui ui) throws DukeException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new DukeException("The description of a " + command + " cannot be empty.");
        }

        Task newTask = null;
        switch (command) {
            case "todo":
                newTask = new Todo(parts[1], false);
                break;
            case "deadline":
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    throw new DukeException("duke.task.Deadline format incorrect. Please use the format: deadline description /by yyyy-MM-dd");
                }
                newTask = new Deadline(deadlineParts[0], deadlineParts[1], false);
                break;
            case "event":
                String[] eventParts = parts[1].split(" /at ", 2);
                if (eventParts.length < 2) {
                    throw new DukeException("duke.task.Event format incorrect. Please use the format: event description /at from to");
                }
                String[] timeParts = eventParts[1].split(" to ", 2);
                if (timeParts.length < 2) {
                    throw new DukeException("duke.task.Event time format incorrect. Please include both start and end times.");
                }
                newTask = new Event(eventParts[0], timeParts[0], timeParts[1], false);
                break;

        }

        if (newTask != null) {
            tasks.addTask(newTask);
            ui.showMessage("Got it. I've added this task:\n  " + newTask + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.");
        }
    }
}
