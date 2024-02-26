package aegis;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Aegis class contains the main() method that initiates and runs the Aegis assistant program logic.
 */
public class Aegis {
    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;
    /**
     * Constructor for creating an Aegis object.
     */
    public Aegis() {
        initialSetup("","");
    }

    /**
     * Constructor for creating an Aegis object with a custom path
     * for directory and file access.
     */
    public Aegis(String directoryPath, String filePath) {
        initialSetup(directoryPath, filePath);
    }

    /**
     * Starts the execution of the Aegis assistant as a CLI program.
     * Initializes required objects and contains the main while loop that repeatedly prompts
     * the user for input and executes commands based on the input.
     *
     * @param args Default parameter for main method.
     */
    public static void main(String[] args) {
        initialSetup("","");

        while (true) {
            try {
                String input = ui.getUserInput();
                if (parser.checkValidCommand(input)) {
                    executeCommand(input);
                } else {
                    throw new AegisException("I do not recognize that command.\n"
                            + "Please enter a valid command.\n");
                }
            } catch (AegisException e) {
                ui.printDivider();
                System.out.println(e.getMessage());
                ui.printDivider();
            }
        }
    }

    /**
     * Returns an ArrayList containing Strings that contain the response of the Aegis assistant program to the user input.
     *
     * @param input User input to be processed by the program.
     * @return ArrayList of Strings containing output of the program.
     */
    public ArrayList<String> getResponse(String input) {
        ArrayList<String> response = new ArrayList<>();
        try {
            response = executeCommand(input);
        } catch (AegisException e) {
            response.add(e.getMessage());
        }
        return response;
    }

    private static void initialSetup(String dPath, String fPath) {
        parser = new Parser();

        String directoryPath = "";
        String filePath = "";
        if (dPath.isBlank() && fPath.isBlank()) {
            directoryPath = "./src/main/data";
            filePath = "./src/main/data/aegis.txt";
        } else {
            directoryPath = dPath;
            filePath = fPath;
        }
        storage = new Storage(directoryPath, filePath);

        taskList = new TaskList();
        ui = new Ui();

        try {
            ArrayList<String> tasksFromFile = storage.readTaskListData();
            for (String s : tasksFromFile) {
                Task reconstructedTask = taskList.reconstructTask(s);
                taskList.addTask(reconstructedTask);
            }
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundError();
        } catch (IOException e) {
            ui.printIoException();
        }

    }

    private static ArrayList<String> executeCommand(String input) throws AegisException {
        String identifier = parser.parseCommand(input);
        assert identifier != null : "Command identifier should not be null";
        String arguments = parser.parseArguments(input);

        ArrayList<String> commandResult = new ArrayList<>();
        switch (identifier) {
        case "bye":
            commandResult.add("bye");
            commandResult.add("Goodbye. See you next time.");
            break;
        case "list":
            commandResult.add(getTasks());
            break;
        case "mark":
            commandResult.add(markTask(arguments));
            break;
        case "unmark":
            commandResult.add(unmarkTask(arguments));
            break;
        case "todo":
            commandResult.add(createToDoTask(arguments));
            break;
        case "deadline":
            commandResult.add(createDeadlineTask(arguments));
            break;
        case "event":
            commandResult.add(createEventTask(arguments));
            break;
        case "delete":
            commandResult.add(deleteTask(arguments));
            break;
        case "find":
            commandResult.add(getIdentifiedTasks(arguments));
            break;
        case "tag":
            commandResult.add(tagTask(arguments));
            break;
        default:
            throw new AegisException("Command not recognized. Please try again.");
        }

        try {
            if (!identifier.equals("list")) {
                storage.writeTaskListData(taskList);
            }
        } catch (IOException e) {
            ui.printIoException();
        }
        return commandResult;
    }

    private static String getTasks() {
        return taskList.getTaskList();
    }

    private static String markTask(String arguments) {
        try {
            int taskNum = parser.parseTaskIndex(arguments);
            taskList.markTask(taskNum);
            return ui.getMarkTaskSuccess() + taskList.getTask(taskNum).toString();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Invalid task index provided.\nPlease provide a valid task index.\n";
        }
    }

    private static String unmarkTask(String arguments) {
        try {
            int taskNum = parser.parseTaskIndex(arguments);
            taskList.unmarkTask(taskNum);
            return ui.getUnmarkTaskSuccess() + taskList.getTask(taskNum).toString();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Invalid task index provided.\nPlease provide a valid task index.\n";
        }
    }

    private static String createToDoTask(String arguments) throws AegisException {
        if (!arguments.isEmpty()) {
            ToDo newToDo = new ToDo(arguments);
            taskList.addTask(newToDo);
            return ui.getCreateTaskSuccess() + newToDo.toString() + "\n\n"
                    + taskList.getTaskCountString();
        } else {
            throw new AegisException("Invalid command given for creating Todo task."
                    + "\nPlease provide command in the following format:"
                    + "\ntodo <Task Description>");
        }
    }

    private static String createDeadlineTask(String arguments) {
        String result = "";
        try {
            String[] deadlineArgs = parser.parseDeadlineArguments(arguments);

            if (deadlineArgs.length == 2) {
                Deadline newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                taskList.addTask(newDeadline);
                result = ui.getCreateTaskSuccess() + newDeadline.toString() + "\n\n"
                        + taskList.getTaskCountString();
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            result = "Invalid command given for creating Deadline task."
                    + "\nPlease provide command in the following format:"
                    + "\ndeadline <Task Description> /by <Date in YYYY-MM-DD>\n";
        }
         return result;
    }

    private static String createEventTask(String arguments) {
        String result = "";
        try {
            String[] eventArgs = parser.parseEventArguments(arguments);

            if (!arguments.isEmpty()) {
                Event newEvent = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                taskList.addTask(newEvent);
                result = ui.getCreateTaskSuccess() + newEvent.toString() + "\n\n"
                        + taskList.getTaskCountString();
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            result = "Invalid command given for creating Event task."
                    + "\nPlease provide command in the following format:"
                    + "\nevent <Task Description> /from <Date in YYYY-MM-DD> /to <Date in YYYY-MM-DD>\n";
        }
        return result;
    }

    private static String deleteTask(String arguments) {
        String result = "";
        try {
            int delIndex = parser.parseTaskIndex(arguments);
            Task toDelete = taskList.getTask(delIndex);
            taskList.deleteTask(delIndex);
            result = ui.getDeleteTaskSuccess() + toDelete.toString() + "\n\n"
                    + taskList.getTaskCountString();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            result = "Invalid task index provided."
                    + "\nPlease provide a valid task index.\n";
        }
        return result;
    }

    private static String getIdentifiedTasks(String keyword) {
        if (keyword.isBlank()) {
            return "Invalid command given for finding tasks."
                    + "\nPlease provide command in the following format:"
                    + "\nfind <keyword>\n";
        }
        return taskList.getTasksWithKeyword(keyword);
    }

    private static String tagTask(String arguments) {
        try {
            int taskNum = parser.parseTaskIndex(arguments);
            String tag = parser.parseTagArguments(arguments);

            if (!taskList.isWithinTaskListBounds(taskNum)) {
                return "Invalid task index provided.\nPlease provide a valid task index.\n";
            }

            if (tag.isBlank()) {
                return "Invalid command given for tagging task."
                        + "\nPlease provide command in the following format:"
                        + "\ntag <Task Index> #<Tag>";
            }

            taskList.tagTask(taskNum, tag);
            return ui.getTagTaskSuccess(tag) + taskList.getTask(taskNum).toString();
        } catch (NoSuchElementException | NumberFormatException e) {
            return "Invalid command given for tagging task."
                    + "\nPlease provide command in the following format:"
                    + "\ntag <Task Index> #<Tag>";
        } catch (AegisException e) {
            return e.getMessage();
        }
    }
}