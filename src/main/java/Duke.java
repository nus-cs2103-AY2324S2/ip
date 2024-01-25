import java.util.ArrayList;

public class Duke {

    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {

        UserInterface.printWelcome();

        boolean isExit = false;
        while(!isExit) {
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

                    case TODO:
                    handleToDo(userInput);
                    break;

                    case DEADLINE:
                    handleDeadline(userInput);
                    break;

                    case EVENT:
                    handleEvent(userInput);
                    break;

                    case EXIT:
                    isExit = true;
                    break;

                    case BYE:
                    isExit = true;
                    break;

                    default:
                    throw new DukeException("Invalid Command" + commandType);
                }
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

    private static void handleToDo(String userInput) throws DukeException {
        String description = CommandParser.parseToDo(userInput);
        String response = taskList.addTask(new ToDo(description));
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskAdded(response, totalTasks);
    }


    private static void handleDeadline(String userInput) throws DukeException {
        String[] deadlineDetails = CommandParser.parseDeadline(userInput);
        String description = deadlineDetails[0];
        String due = deadlineDetails[1];
        String response = taskList.addTask(new Deadline(description, due));
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskAdded(response, totalTasks);
    }

    private static void handleEvent(String userInput) throws DukeException {
        String[] eventDetails = CommandParser.parseEvent(userInput);
        String description = eventDetails[0];
        String start = eventDetails[1];
        String end = eventDetails[2];
        String response = taskList.addTask(new Event(description, start, end));
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskAdded(response, totalTasks);
    }
}
