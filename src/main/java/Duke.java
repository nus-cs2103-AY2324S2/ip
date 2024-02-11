import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Duke {
    private static HashSet<String> validCommands;
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
                StringTokenizer st = new StringTokenizer(input);
                String identifier = st.nextToken().toLowerCase();
                if (validCommands.contains(identifier)) {
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
        storage = new Storage("./src/main/data",
                "./src/main/data/duke.txt");
        taskList = new TaskList();
        ui = new Ui();
        validCommands = new HashSet<String>();
        validCommands.addAll(Arrays.asList("bye",
                "list",
                "mark",
                "unmark",
                "todo",
                "deadline",
                "event",
                "delete"));
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

    private static void executeCommand(String command) throws DukeException {
        StringTokenizer st = new StringTokenizer(command);
        String identifier = st.nextToken().toLowerCase();
        String arguments = "";
        while(st.hasMoreTokens()) {
            arguments += st.nextToken() + " ";
        }
        arguments = arguments.trim();
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
        int taskNum = Integer.parseInt(arguments) - 1;
        taskList.markTask(taskNum);
        ui.printDivider();
        ui.printMarkTaskSuccess();
        System.out.println(taskList.getTask(taskNum).toString() + "\n");
        ui.printDivider();
    }

    private static void unmarkTask(String arguments) throws DukeException {
        int taskNum = Integer.parseInt(arguments) - 1;
        taskList.unmarkTask(taskNum);
        ui.printDivider();
        ui.printUnmarkTaskSuccess();
        System.out.println(taskList.getTask(taskNum).toString() + "\n");
        ui.printDivider();
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
        if (!arguments.isEmpty()) {
            String[] deadlineInfo = arguments.split("/");
            StringTokenizer st = new StringTokenizer(deadlineInfo[0].trim());
            String deadlineDesc = "";
            while(st.hasMoreTokens()) {
                deadlineDesc += st.nextToken() + " ";
            }
            deadlineDesc = deadlineDesc.trim();
            st = new StringTokenizer(deadlineInfo[1].trim());
            st.nextToken();
            String by = "";
            while(st.hasMoreTokens()) {
                by += st.nextToken() + " ";
            }
            by = by.trim();
            Deadline newDeadline = new Deadline(deadlineDesc, by);
            taskList.addTask(newDeadline);
            ui.printDivider();
            ui.printCreateTaskSuccess();
            System.out.println(newDeadline.toString() + "\n");
        } else {
            throw new DukeException("deadline command requires a description for the task"
                    + " and a deadline. \n\nPlease leave a space after 'deadline'"
                    + " and enter the task description, \nfollowed by a space and a"
                    + " forward slash then the deadline of the task.");
        }
    }

    private static void createEventTask(String arguments) throws DukeException {
        if (!arguments.isEmpty()) {
            String[] eventInfo = arguments.split("/");
            StringTokenizer st = new StringTokenizer(eventInfo[0].trim());
            String eventDesc = "";
            while(st.hasMoreTokens()) {
                eventDesc += st.nextToken() + " ";
            }
            eventDesc = eventDesc.trim();
            st = new StringTokenizer(eventInfo[1].trim());
            st.nextToken();
            String from = "";
            while (st.hasMoreTokens()) {
                from += st.nextToken() + " ";
            }
            from = from.trim();
            st = new StringTokenizer(eventInfo[2].trim());
            st.nextToken();
            String end = "";
            while (st.hasMoreTokens()) {
                end += st.nextToken() + " ";
            }
            end = end.trim();
            Event newEvent = new Event(eventDesc, from, end);
            taskList.addTask(newEvent);
            ui.printDivider();
            ui.printCreateTaskSuccess();
            System.out.println(newEvent.toString() + "\n");
        } else {
            throw new DukeException("event command requires a description for the task,"
                    + " start time and end time. \n\nPlease leave a space after 'event'"
                    + " and enter the task description, \nfollowed by a space and forward slash"
                    + " before the start time, \nfollowed by another space and forward slash"
                    + " before the end time.");
        }
    }

    private static void deleteTask(String arguments) throws DukeException {
        int delIndex = Integer.parseInt(arguments) - 1;
        Task toDelete = taskList.getTask(delIndex);
        taskList.deleteTask(delIndex);
        ui.printDivider();
        ui.printDeleteTaskSuccess();
        System.out.println(toDelete.toString() + "\n");
        ui.printDivider();
    }
}