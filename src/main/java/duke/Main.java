package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.command.CommandParser;
import duke.command.CommandType;
import duke.commons.exceptions.DukeException;
import duke.storage.PersistentStorageHandler;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.UserInterface;

public class Main {

    private static TaskList taskList = new TaskList();

    private static PersistentStorageHandler persistentStorageHandler = new PersistentStorageHandler();

    public static void main(String[] args) {

        UserInterface.printWelcome();

        try {
            if (persistentStorageHandler.ensureTaskFileExists()) {
                taskList = persistentStorageHandler.readTaskFileFromDisc();
                int numTasks = taskList.getNumberTasks();
                UserInterface.print("Read existing tasks (" + numTasks + ") from disc");
            }
        } catch (DukeException e) {
            UserInterface.showError(e.getMessage());
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = UserInterface.getUserInput();
                CommandType commandType = CommandParser.parseCommand(userInput);

                switch (commandType) {
                    case LIST:
                        handleList();
                        break;

                    case MARK:
                        handleMark(userInput);
                        break;

                    case UNMARK:
                        handleUnmark(userInput);
                        break;

                    case DELETE:
                        handleDelete(userInput);
                        break;
                    
                    case FIND:
                        handleFind(userInput);
                        break;

                    case TODO:
                        handleToDo(userInput);
                        break;

                    case DEADLINE:
                        handleDeadline(userInput);
                        break;

                    case EVENT:
                        handleEvent(userInput);
                        break;

                    case BYE:
                        isExit = true;
                        break;

                    default:
                        throw new DukeException("Invalid Command" + commandType);
                }

                persistentStorageHandler.writeTaskFileToDisc(taskList);
            } catch (DukeException e) {
                UserInterface.showError(e.getMessage());
            }
        }

        UserInterface.printExit();
        return;
    }

    private static void handleList() throws DukeException {
        taskList.printTasks();
    }

    private static void handleMark(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.markTaskDone(idx);
        UserInterface.print(response);
    }

    private static void handleUnmark(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.markTaskUndone(idx);
        UserInterface.print(response);
    }

    private static void handleDelete(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.deleteTask(idx);
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskDeleted(response, totalTasks);
    }

    private static void handleFind(String userInput) throws DukeException {
        String[] keywords = CommandParser.parseFind(userInput);
        ArrayList<Integer> taskIndices = taskList.findTasksByKeywordsMatching(keywords);
        ArrayList<String> response = taskList.getTaskRepresentationsByIndices(taskIndices);
        UserInterface.printTasksByIndices(response);
    }

    private static void handleToDo(String userInput) throws DukeException {
        String description = CommandParser.parseToDo(userInput);
        String response = taskList.addTask(new ToDo(description));
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskAdded(response, totalTasks);
    }

    private static void handleDeadline(String userInput) throws DukeException {
        String[] deadlineDetails = CommandParser.parseDeadline(userInput);
        String description = deadlineDetails[0];
        LocalDate due = LocalDate.parse(deadlineDetails[1]);
        String response = taskList.addTask(new Deadline(description, due));
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskAdded(response, totalTasks);
    }

    private static void handleEvent(String userInput) throws DukeException {
        String[] eventDetails = CommandParser.parseEvent(userInput);
        String description = eventDetails[0];
        LocalDate start = LocalDate.parse(eventDetails[1]);
        LocalDate end = LocalDate.parse(eventDetails[2]);
        String response = taskList.addTask(new Event(description, start, end));
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskAdded(response, totalTasks);
    }
}
