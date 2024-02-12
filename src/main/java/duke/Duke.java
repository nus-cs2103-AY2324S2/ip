package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Duke {
    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public static void main(String[] args) {
        initialSetup();
        ui.printLogo();
        ui.printDivider();
        ui.printGreeting();
        ui.printDivider();
        while (true) {
            try {
                String input = ui.getUserInput();
                if (parser.checkValidCommand(input)) {
                    executeCommand(input);
                } else {
                    throw new DukeException("I do not recognize that command.\n"
                            + "Please enter a valid command.\n");
                }
            } catch (DukeException e) {
                ui.printDivider();
                System.out.println(e.getMessage());
                ui.printDivider();
            }
        }
    }

    private static void initialSetup() {
        parser = new Parser();
        storage = new Storage("./src/main/data",
                "./src/main/data/duke.txt");
        taskList = new TaskList();
        ui = new Ui();
        try {
            ArrayList<String> tasksFromFile = storage.readTaskListData();
            for (int i = 0; i < tasksFromFile.size(); i++) {
                Task reconstructedTask = taskList.reconstructTask(tasksFromFile.get(i));
                taskList.addTask(reconstructedTask);
            }
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundError();
        } catch (IOException e) {
            ui.printIoException();
        }
    }

    private static void executeCommand(String input) throws DukeException {
        String identifier = parser.parseCommand(input);
        String arguments = parser.parseArguments(input);
        switch (identifier) {
        case "bye":
            exitProgram();
            break;
        case "list":
            printTasks();
            break;
        case "mark":
            markTask(arguments);
            break;
        case "unmark":
            unmarkTask(arguments);
            break;
        case "todo":
            createToDoTask(arguments);
            break;
        case "deadline":
            createDeadlineTask(arguments);
            break;
        case "event":
            createEventTask(arguments);
            break;
        case "delete":
            deleteTask(arguments);
            break;
        default:
            System.out.println("なに？！");
            break;
        }
        try {
            if (!identifier.equals("list")) {
                storage.writeTaskListData(taskList);
            }
        } catch (IOException e) {
            ui.printIoException();
        }
        taskList.printTaskCount();
        ui.printDivider();
    }

    private static void exitProgram() {
        ui.printDivider();
        ui.printFarewell();
        ui.printDivider();
        System.exit(0);
    }

    private static void printTasks() {
        ui.printDivider();
        taskList.printTaskList();
        ui.printDivider();
    }

    private static void markTask(String arguments) throws DukeException {
        try {
            int taskNum = parser.parseTaskIndex(arguments);
            taskList.markTask(taskNum);
            ui.printDivider();
            ui.printMarkTaskSuccess();
            System.out.println(taskList.getTask(taskNum).toString() + "\n");
            ui.printDivider();
        } catch (IndexOutOfBoundsException e) {
            ui.printDivider();
            System.out.println("Invalid task index provided.\nPlease provide a valid task index.\n");
            ui.printDivider();
        }
    }

    private static void unmarkTask(String arguments) throws DukeException {
        try {
            int taskNum = parser.parseTaskIndex(arguments);
            taskList.unmarkTask(taskNum);
            ui.printDivider();
            ui.printUnmarkTaskSuccess();
            System.out.println(taskList.getTask(taskNum).toString() + "\n");
            ui.printDivider();
        } catch (IndexOutOfBoundsException e) {
            ui.printDivider();
            System.out.println("Invalid task index provided.\nPlease provide a valid task index.\n");
            ui.printDivider();
        }
    }

    private static void createToDoTask(String arguments) throws DukeException {
        if (!arguments.isEmpty()) {
            ToDo newToDo = new ToDo(arguments);
            taskList.addTask(newToDo);
            ui.printDivider();
            ui.printCreateTaskSuccess();
            System.out.println(newToDo.toString() + "\n");
        } else {
            throw new DukeException("todo command requires a description for the task."
                    + "\n\nPlease leave a space after 'todo' and enter"
                    + " the task description.");
        }
    }

    private static void createDeadlineTask(String arguments) throws DukeException {
        try {
            String[] deadlineArgs = parser.parseDeadlineArguments(arguments);
            if (deadlineArgs.length == 2) {
                Deadline newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                taskList.addTask(newDeadline);
                ui.printDivider();
                ui.printCreateTaskSuccess();
                System.out.println(newDeadline.toString() + "\n");
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            ui.printDivider();
            System.out.println("Invalid command given for creating Deadline task."
                    + "\nPlease ensure the command uses the following format:"
                    + "\ndeadline <Task Description> /by <Date in YYYY-MM-DD>\n");
            ui.printDivider();
        }
    }

    private static void createEventTask(String arguments) throws DukeException {
        try {
            String[] eventArgs = parser.parseEventArguments(arguments);
            if (!arguments.isEmpty()) {
                Event newEvent = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                taskList.addTask(newEvent);
                ui.printDivider();
                ui.printCreateTaskSuccess();
                System.out.println(newEvent.toString() + "\n");
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            ui.printDivider();
            System.out.println("Invalid command given for creating Event task."
                    + "\nPlease ensure the command uses the following format:"
                    + "\nevent <Task Description> /from <Date in YYYY-MM-DD> /to <Date in YYYY-MM-DD>\n");
            ui.printDivider();
        }

    }

    private static void deleteTask(String arguments) throws DukeException {
        try {
            int delIndex = parser.parseTaskIndex(arguments);
            Task toDelete = taskList.getTask(delIndex);
            taskList.deleteTask(delIndex);
            ui.printDivider();
            ui.printDeleteTaskSuccess();
            System.out.println(toDelete.toString() + "\n");
            ui.printDivider();
        } catch (IndexOutOfBoundsException e) {
            ui.printDivider();
            System.out.println("Invalid task index provided.\nPlease provide a valid task index.\n");
            ui.printDivider();
        }

    }
}