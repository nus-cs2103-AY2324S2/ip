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
     * @return Appropriate response to the user input.
     * @throws DukeException If the user input is invalid.
     */
    public static String parse(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case "bye":
            return ui.goodbyeMessage();
        case "list":
            return ui.showTasks(TaskList.getTasks(), TaskList.getTaskNum());
        case "mark":
            int position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String taskStr = userInput.substring(position + 1);

                int taskNumber = Integer.parseInt(taskStr) - 1; // cause array
                if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
                    tasks.markTaskAsDone(taskNumber);
                    return ui.showMarkedAsDone(TaskList.getTask(taskNumber));
                } else {
                    throw new DukeException("     Invalid task number >:((");
                }
            }
        case "unmark":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String taskStr = userInput.substring(position + 1);

                int taskNumber = Integer.parseInt(taskStr) - 1;
                if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
                    tasks.markTaskAsNotDone(taskNumber);
                    return ui.showMarkedAsNotDone(TaskList.getTask(taskNumber));
                } else {
                    throw new DukeException("     Invalid task number >:((");
                }
            }
            break;
        case "todo":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String taskStr = userInput.substring(position + 1);

                if (taskStr.isEmpty()) {
                    throw new DukeException("     Are you gonna be doing nothing?");
                }

                Task newTask = new Todo(taskStr);
                tasks.addTask(newTask);

                return ui.showAddedTask(newTask, TaskList.getTaskNum());
            } else {
                throw new DukeException("     Invalid command >:((");
            }
        case "deadline":
            position = userInput.indexOf(" ");
            int posBy = userInput.indexOf("/by");
            if (position != -1 && position + 1 < userInput.length() && posBy != -1 && posBy + 1
                    < userInput.length()) {
                String taskStr = userInput.substring(position + 1, posBy - 1);
                String taskStrBy = userInput.substring(posBy + 4);

                if (taskStr.isEmpty() || taskStrBy.isEmpty()) {
                    throw new DukeException("     Invalid command >:((");
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime deadlineDate = LocalDateTime.parse(taskStrBy, formatter);

                Task newTask = new Deadline(taskStr, deadlineDate);
                tasks.addTask(newTask);

                return ui.showAddedTask(newTask, TaskList.getTaskNum());
            } else {
                throw new DukeException("     Invalid command >:((");
            }
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
                    throw new DukeException("     Invalid command >:((");
                }

                Task newTask = new Event(taskStr, taskStrFrom, taskStrTo);
                tasks.addTask(newTask);

                return ui.showAddedTask(newTask, TaskList.getTaskNum());
            } else {
                throw new DukeException("     Invalid command >:(");
            }
        case "delete":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String taskStr = userInput.substring(position + 1);
                int taskNumber = Integer.parseInt(taskStr) - 1;

                if (taskStr.isEmpty()) {
                    throw new DukeException("     You're deleting air");
                }

                if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
                    String deleteMessage = ui.showDeleteTask(TaskList.getTask(taskNumber), taskNumber - 1);
                    tasks.removeTask(taskNumber);
                    return deleteMessage;
                } else {
                    throw new DukeException("     Invalid command >:(");
                }
            }
            break;
        case "on":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String dateStr = userInput.substring(position + 1);
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateToCheck = LocalDate.parse(dateStr, dateFormatter);

                return ui.showDeadlinesEventsOnDate(TaskList.getTasks(), TaskList.getTaskNum(), dateToCheck);
            } else {
                throw new DukeException("     Invalid command >:(");
            }
        case "find":
            position = userInput.indexOf(" ");
            if (position != -1 && position + 1 < userInput.length()) {
                String keyword = userInput.substring(position + 1);
                return ui.showMatchingTasks(keyword);
            } else {
                throw new DukeException("     Invalid command >:(");
            }
        default:
            throw new DukeException("     Gurl I'm sorry, idk what that means :-(");
        }
        return "     I'm sorry, but I don't understand that command.";
    }
}
