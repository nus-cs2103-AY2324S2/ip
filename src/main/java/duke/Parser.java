package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser for Duke.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate response.
     * @param userInput User input.
     * @param tasks Task list.
     * @param ui User interface.
     * @param storage Storage.
     * @return Appropriate response to the user input.
     * @throws DukeException If the user input is invalid.
     */
    public static String parse(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case "bye":
            storage.saveTasksToFile(TaskList.getTasks(), TaskList.getTaskNum());
            ui.goodbyeMessage();
            return "1";
        case "list":
            ui.showTasks(TaskList.getTasks(), TaskList.getTaskNum());
            break;
        case "mark":
            int position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String taskStr = userInput.substring(position + 1);

                int taskNumber = Integer.parseInt(taskStr) - 1; // cause array
                if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
                    tasks.markTaskAsDone(taskNumber);
                    ui.showMarkedAsDone(TaskList.getTask(taskNumber));
                } else {
                    throw new DukeException("Invalid task number >:((");
                }
            }
            break;
        case "unmark":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String taskStr = userInput.substring(position + 1);

                int taskNumber = Integer.parseInt(taskStr) - 1;
                if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
                    tasks.markTaskAsNotDone(taskNumber);
                    ui.showMarkedAsNotDone(TaskList.getTask(taskNumber));
                } else {
                    throw new DukeException("Invalid task number >:((");
                }
            }
            break;
        case "todo":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String taskStr = userInput.substring(position + 1);

                if (taskStr.isEmpty()) {
                    throw new DukeException("Are you gonna be doing nothing?");
                }

                Task newTask = new Todo(taskStr);
                tasks.addTask(newTask);

                ui.showAddedTask(newTask, TaskList.getTaskNum());
            } else {
                throw new DukeException("Invalid command >:((");
            }
            break;
        case "deadline":
            position = userInput.indexOf(" ");
            int posBy = userInput.indexOf("/by");
            if (position != -1 && position + 1 < userInput.length() && posBy != -1 && posBy + 1
                    < userInput.length()) {
                String taskStr = userInput.substring(position + 1, posBy - 1);
                String taskStrBy = userInput.substring(posBy + 4);

                if (taskStr.isEmpty() || taskStrBy.isEmpty()) {
                    throw new DukeException("Invalid command >:((");
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime deadlineDate = LocalDateTime.parse(taskStrBy, formatter);

                Task newTask = new Deadline(taskStr, deadlineDate);
                tasks.addTask(newTask);

                ui.showAddedTask(newTask, TaskList.getTaskNum());
            } else {
                throw new DukeException("Invalid command >:((");
            }
            break;
        case "event":
            position = userInput.indexOf(" ");
            int posFrom = userInput.indexOf("/from");
            int posTo = userInput.indexOf("/to");

            if (position != -1 && position + 1 < userInput.length() && posFrom != -1 && posFrom + 1
                    < userInput.length() && posTo != -1 && posTo + 1 < userInput.length()) {
                String taskStr =
                        userInput.substring(position + 1, posFrom - 1);
                String taskStrFrom =
                        userInput.substring(posFrom + 6, posTo - 1);
                String taskStrTo = userInput.substring(posTo + 4);

                if (taskStr.isEmpty() || taskStrFrom.isEmpty() || taskStrTo.isEmpty()) {
                    throw new DukeException("Invalid command >:((");
                }

                Task newTask = new Event(taskStr, taskStrFrom, taskStrTo);
                tasks.addTask(newTask);

                ui.showAddedTask(newTask, TaskList.getTaskNum());
            } else {
                throw new DukeException("Invalid command >:(");
            }
            break;
        case "delete":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String taskStr = userInput.substring(position + 1);
                int taskNumber = Integer.parseInt(taskStr) - 1;

                if (taskStr.isEmpty()) {
                    throw new DukeException("You're deleting air");
                }

                if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
                    ui.showDeleteTask(TaskList.getTask(taskNumber), taskNumber);
                    tasks.removeTask(taskNumber);
                } else {
                    throw new DukeException("Invalid command >:(");
                }
            }
            break;
        case "on":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String dateStr = userInput.substring(position + 1);
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateToCheck = LocalDate.parse(dateStr, dateFormatter);

                ui.showDeadlinesEventsOnDate(TaskList.getTasks(), TaskList.getTaskNum(), dateToCheck);
            } else {
                throw new DukeException("Invalid command >:(");
            }
            break;
        case "find":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String keyword = userInput.substring(position + 1);
                ui.showMatchingTasks(keyword);
            } else {
                throw new DukeException("Invalid command >:(");
            }
            break;
        default:
            throw new DukeException("Gurl I'm sorry, idk what that means :-(");
        }
        return "I'm sorry, but I don't understand that command.";
    }
}
