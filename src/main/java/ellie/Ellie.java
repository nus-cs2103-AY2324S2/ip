package ellie;

import ellie.exception.InvalidTaskInputException;
import ellie.exception.UnknownInputException;
import ellie.task.Deadline;
import ellie.task.Event;
import ellie.task.Task;
import ellie.task.Todo;

import java.util.Scanner;

public class Ellie {

    enum Command {
        MARK,
        UNMARK,
        LIST,
        DEADLINE,
        EVENT,
        TODO,
        UNKNOWN,
        HELP,
        BYE,
        DELETE,

    }


    private Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Ellie() {
        storage = new Storage("./data/toDoList.txt");
        taskList = new TaskList(storage);
        ui = new Ui();
    }

    public void start() {
        ui.hello();
        Scanner reader = new Scanner(System.in);
        Command command = Command.UNKNOWN;
        String input = reader.nextLine();

        while (true) {

            String[] inputArray = input.split(" ", 2);
            String stringHeader = inputArray[0].toLowerCase();

            switch (stringHeader) {
            case "list":
                command = Command.LIST;
                break;
            case "mark":
                command = Command.MARK;
                break;
            case "unmark":
                command = Command.UNMARK;
                break;
            case "delete":
                command = Command.DELETE;
                break;
            case "todo":
                command = Command.TODO;
                break;
            case "deadline":
                command = Command.DEADLINE;
                break;
            case "event":
                command = Command.EVENT;
                break;
            case "help":
                command = Command.HELP;
                break;
            case "bye":
                command = Command.BYE;
                break;
            default:
                command = Command.UNKNOWN;
            }

            // commands with no argument: BYE / LIST / UNKNOWN
            if (command == Command.BYE) {
                break;
            } else if (command == Command.LIST) {
                taskList.listTasks();
                input = reader.nextLine();
                continue;
            } else if (command == Command.HELP) {
                System.out.println("Here's a list of supported commands so far:" +
                        "\n help \n list \n mark/unmark [int] \n todo [task] \n " +
                        "deadline [task] /by [date]  \n event [task] /from [date] /to [date] \n bye \n");
                input = reader.nextLine();
                continue;
            } else if (command == Command.UNKNOWN) {
                try {
                    throw new UnknownInputException("Command Unknown or Missing");
                } catch (UnknownInputException e) {
                    System.out.println("Sorry! Not sure what you're referring to (╥_╥)");
                    System.out.println("Type 'help' to view the list of supported commands!\n");
                    input = reader.nextLine();
                    continue;
                }
            }

            // check for following input argument
            try {
                if (inputArray.length < 2) {
                    throw new InvalidTaskInputException("command contains no argument");
                }
            } catch (InvalidTaskInputException e) {
                System.out.println("Please input an argument! \n [command] [argument]\n");
                input = reader.nextLine();
                continue;
            }

            String stringBody = inputArray[1];

            if (command == Command.MARK) {
                if (isNumeric(stringBody)) {
                    int index = Integer.parseInt(stringBody);
                    taskList.markTaskIndex(index);
                } else {
                    System.out.println("Input a valid number to mark! \n Usage: mark [int]\n");
                }
            } else if (command == Command.UNMARK) {
                if (isNumeric(stringBody)) {
                    int index = Integer.parseInt(stringBody);
                    taskList.unmarkTaskIndex(index);
                } else {
                    System.out.println("Input a valid number to unmark! \n Usage: unmark [int]\n");
                }
            } else if (command == Command.DELETE) {
                if (isNumeric(stringBody)) {
                    int index = Integer.parseInt(stringBody);
                    taskList.deleteTaskIndex(index);
                } else {
                    System.out.println("Input a valid number to delete! \n Usage: delete [int]\n");
                }

            } else if (command == Command.TODO) {
                Task task = new Todo(stringBody);
                taskList.addTask(task);
            } else if (command == Command.DEADLINE) {
                if (!stringBody.contains("/by")) {
                    System.out.println("Please add a due date for your dateline using '/by'!");
                } else {
                    String[] deadlineArray = stringBody.split("/by");
                    String event = deadlineArray[0].trim();
                    String dueDate = deadlineArray[1].trim();
                    if (event.equals("")) {
                        System.out.println("Please add event name.");
                    } else if (dueDate.equals("")) {
                        System.out.println("Please add a deadline!");
                    } else {
                        Task task = new Deadline(event, dueDate);
                        taskList.addTask(task);
                    }
                }
            } else if (command == Command.EVENT) {
                if (!stringBody.contains("/from")) {
                    System.out.println("Please add a start date for your event using '/from'!");
                } else if (!stringBody.contains("/to")) {
                    System.out.println("Please add an end date for your event using '/to'!");
                } else {
                    String[] deadlineArray = stringBody.split("/from");
                    String event = deadlineArray[0].trim();
                    String eventDuration = deadlineArray[1];
                    if (event.equals("")) {
                        System.out.println("Please add event name.");
                    } else if (event.contains("/end")) {
                        System.out.println("Please place /end [end time] after /from [start time]!");
                    } else {
                        String[] duration = eventDuration.split("/to");
                        Task task = new Event(event, duration[0].trim(), duration[1].trim());
                        taskList.addTask(task);
                    }
                }

            }

            input = reader.nextLine();
        }

        ui.goodbye();
    }

    private static Boolean isNumeric(String string) {
        return string.matches("\\d+");
    }


}
