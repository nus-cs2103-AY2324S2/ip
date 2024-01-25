import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    private static final String DIVIDER = "────────────────────────────────────────────────────────────";
    private static final String GREETING = "Hello! I'm Seiki\nHow may I assist you today?";
    private static final String FAREWELL = "Goodbye! If you ever need assistance in the future, don't hesitate to reach out. Take care!";
    private static final String LOGO = "  _____      _  _     _\n"
                                    + " / ____|    (_)| | _ (_)\n"
                                    + "| (___  ___  _ | |/ / _\n"
                                    + " \\___ \\/ _ \\| ||   / | |\n"
                                    + " ____) | __/| || | \\ | |\n"
                                    + "|_____/\\___||_||_|\\_\\|_|\n";
    protected TaskList taskList;
    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }
    public Duke() {
        taskList = new TaskList();
    }

    private void run() throws DukeException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            ArrayList<String> inputArr = new ArrayList<>(Arrays.asList(br.readLine().split(" ")));
            String input = inputArr.remove(0);
            System.out.println(DIVIDER);
            Command command;
            try {
                command = Command.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("I'm sorry, I didn't quite understand that.");
            }

            switch (command) {
                case BYE:
                    end();
                case LIST:
                    printList(inputArr);
                    break;
                case MARK:
                    markTask(inputArr);
                    break;
                case UNMARK:
                    unmarkTask(inputArr);
                    break;
                case TODO:
                    addToDo(inputArr);
                    break;
                case DEADLINE:
                    addDeadline(inputArr);
                    break;
                case EVENT:
                    addEvent(inputArr);
                    break;
                case DELETE:
                    deleteTask(inputArr);
                    break;
                default:
                    throw new DukeException("I'm sorry, I didn't quite understand that.");
            }
            System.out.println(DIVIDER);
        }
    }

    private void start() throws DukeException, IOException {
        try {
            run();
        } catch (DukeException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println(DIVIDER);
            start();
        }
    }

    private void end() {
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
        System.exit(0);
    }

    private void printList(ArrayList<String> inputArr) throws DukeException {
        if (inputArr.size() == 0) {
            taskList.printList();
        } else {
            throw new DukeException("Additional inputs have been detected.\nPlease only type 'list' to view your list.");
        }
    }

    private void markTask(ArrayList<String> inputArr) throws DukeException {
        if (taskList.getTaskCount() == 0) {
            throw new DukeException("There are currently no tasks to be marked.");
        } else {
            if (inputArr.size() == 0) {
                throw new DukeException("Please enter a task number.");
            } else {
                int taskNumber = Integer.parseInt(inputArr.get(0));
                if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
                    throw new DukeException("Please enter a valid task number.");
                } else {
                    Task task = taskList.getTaskByNumber(taskNumber);
                    task.markAsDone();
                    System.out.println("The following task has been marked.");
                    System.out.println("→ " + task);
                }
            }
        }
    }

    private void unmarkTask(ArrayList<String> inputArr) throws DukeException {
        if (taskList.getTaskCount() == 0) {
            throw new DukeException("There are currently no tasks to be unmarked.");
        } else {
            if (inputArr.size() == 0) {
                throw new DukeException("Please enter a task number.");
            } else {
                int taskNumber = Integer.parseInt(inputArr.get(0));
                if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
                    throw new DukeException("Please enter a valid task number.");
                } else {
                    Task task = taskList.getTaskByNumber(taskNumber);
                    task.markAsNotDone();
                    System.out.println("The following task has not been unmarked.");
                    System.out.println("→ " + task);
                }
            }
        }
    }

    private void addToDo(ArrayList<String> inputArr) throws DukeException {
        if (inputArr.size() == 0) {
            throw new DukeException("The task title is missing.\nPlease use the following format: todo [task title]");
        } else {
            String taskName = String.join(" ", inputArr);
            ToDo newTask = new ToDo(taskName);
            taskList.addTask(newTask);
            System.out.println("The following task has been added:");
            System.out.println("→ " + newTask);
            System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");
        }
    }

    private void addDeadline(ArrayList<String> inputArr) throws DukeException {
        if (inputArr.size() == 0 || (inputArr.subList(inputArr.indexOf("/by") + 1, inputArr.size()).size() == 0 && inputArr.subList(0, inputArr.indexOf("/by")).size() == 0)) {
            throw new DukeException("The task title and date time for the task is missing.\nPlease use the following format: deadline [task title] /by [datetime]");
        } else if (!inputArr.contains("/by") || inputArr.subList(inputArr.indexOf("/by") + 1, inputArr.size()).size() == 0) {
            throw new DukeException("The date time for the task is not found.\nPlease use the following format: deadline [task title] /by [datetime]");
        } else if (inputArr.subList(0, inputArr.indexOf("/by")).size() == 0) {
            throw new DukeException("The task title is missing.\nPlease use the following format: deadline [task title] /by [datetime]");
        } else {
            String taskName = String.join(" ", inputArr.subList(0, inputArr.indexOf("/by")));
            String dateTime = String.join(" ", inputArr.subList(inputArr.indexOf("/by") + 1, inputArr.size()));
            Deadline newTask = new Deadline(taskName, dateTime);
            taskList.addTask(newTask);
            System.out.println("The following task has been added:");
            System.out.println("→ " + newTask);
            System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");
        }
    }

    private void addEvent(ArrayList<String> inputArr) throws DukeException {
        if (inputArr.size() == 0) {
            throw new DukeException("The task title, start and end date time for the task is missing.\nPlease use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (!inputArr.contains("/from") && !inputArr.contains("/to")) {
            throw new DukeException("The date time for the task is missing.\nPlease use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (inputArr.contains("/from") && inputArr.contains("/to") && (inputArr.subList(0, inputArr.indexOf("/from")).size() == 0 && inputArr.subList(inputArr.indexOf("/to") + 1, inputArr.size()).size() == 0 && inputArr.subList(inputArr.indexOf("/from") + 1, inputArr.indexOf("/to")).size() == 0)) {
            throw new DukeException("The task title, start and end date time for the task is missing.\nPlease use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (!inputArr.contains("/to") || inputArr.subList(inputArr.indexOf("/to") + 1, inputArr.size()).size() == 0) {
            throw new DukeException("The date time for the task is missing.\nPlease use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (!inputArr.contains("/from") || inputArr.subList(inputArr.indexOf("/from") + 1, inputArr.indexOf("/to")).size() == 0) {
            throw new DukeException("The date time for the task is missing.\nPlease use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (inputArr.subList(0, inputArr.indexOf("/from")).size() == 0) {
            throw new DukeException("The task title is missing.\nPlease use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else {
            String taskName = String.join(" ", inputArr.subList(0, inputArr.indexOf("/from")));
            String startDateTime = String.join(" ", inputArr.subList(inputArr.indexOf("/from") + 1, inputArr.indexOf("/to")));
            String endDateTime = String.join(" ", inputArr.subList(inputArr.indexOf("/to") + 1, inputArr.size()));
            Event newTask = new Event(taskName, startDateTime, endDateTime);
            taskList.addTask(newTask);
            System.out.println("The following task has been added:");
            System.out.println("→ " + newTask);
            System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");
        }
    }

    private void deleteTask(ArrayList<String> inputArr) throws DukeException {
        if (taskList.getTaskCount() == 0) {
            throw new DukeException("There are currently no tasks to be deleted.");
        } else {
            if (inputArr.size() == 0) {
                throw new DukeException("Please enter a task number.");
            } else {
                int taskNumber = Integer.parseInt(inputArr.get(0));
                if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
                    throw new DukeException("Please enter a valid task number.");
                } else {
                    Task task = taskList.getTaskByNumber(taskNumber);
                    taskList.deleteTask(task);
                    System.out.println("The following task has been deleted.");
                    System.out.println("→ " + task);
                    System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
         try {
             System.out.println(DIVIDER);
             System.out.println(LOGO);
             System.out.println(GREETING);
             System.out.println(DIVIDER);
             duke.start();
        } catch (DukeException e) {
             System.out.println(e.getMessage());
        }
    }
}
