import java.util.Scanner;

public class TheCount {

    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID
    }

    public static void main(String[] args) {
        // Creates a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Creates a list of tasks
        TaskList tasks = new TaskList();

        // Creates standard replies
        Reply greeting = new Greeting();
        Reply goodbye = new Goodbye();

        // Prints greeting
        greeting.displayMessage();

        // Wait for user input
        String userInput = scanner.nextLine();
        // Checks for BYE command
        while (true) {
            switch (getCommandType(userInput)) {
                case BYE:
                    goodbye.displayMessage();
                    scanner.close();
                    System.exit(0);
                    break;
                case LIST:
                    tasks.printList();
                    break;
                case MARK:
                    handleMarkTask(userInput, tasks);
                    break;
                case UNMARK:
                    handleUnmarkTask(userInput, tasks);
                    break;
                case TODO:
                    handleTodoTask(userInput, tasks);
                    break;
                case DEADLINE:
                    handleDeadlineTask(userInput, tasks);
                    break;
                case EVENT:
                    handleEventTask(userInput, tasks);
                    break;
                case DELETE:
                    handleDeleteTask(userInput, tasks);
                    break;
                case INVALID:
                    handleInvalidCommand();
                    break;
                default:
                    break;
            }
            userInput = scanner.nextLine();
        }
    }

    private static CommandType getCommandType(String userInput) {
        String commandName = userInput.split("\\s+")[0].toUpperCase();
        try {
            return CommandType.valueOf(commandName);
        } catch (IllegalArgumentException e) {
            return CommandType.INVALID;
        }
    }

    private static void handleMarkTask(String userInput, TaskList tasks) {
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
            tasks.markTask(taskNumber);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            handleException("Please put a number. I can't count that!");
        } catch (TheCountException e) {
            handleException(e);
        }
    }

    private static void handleUnmarkTask(String userInput, TaskList tasks) {
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
            tasks.unmarkTask(taskNumber);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            handleException("Please put a number. I can't count that!");
        } catch (TheCountException e) {
            handleException(e);
        }
    }

    private static void handleTodoTask(String userInput, TaskList tasks) {
        try {
            String info = getTaskInfo(userInput, " ");
            ToDo todo = new ToDo(info);
            tasks.add(todo);
            todo.displayMessage(tasks.length());
        } catch (TheCountException e) {
            handleException(e);
        }
    }

    private static void handleDeadlineTask(String userInput, TaskList tasks) {
        try {
            String info = getTaskInfo(userInput, "/by");
            String deadlineTime = getTaskTime(userInput, "/by", "deadline");
            Deadline deadline = new Deadline(info, deadlineTime);
            tasks.add(deadline);
            deadline.displayMessage(tasks.length());
        } catch (TheCountException e) {
            handleException(e, "Example: deadline assignment /by 2pm");
        }
    }

    private static void handleEventTask(String userInput, TaskList tasks) {
        try {
            String info = getTaskInfo(userInput, "/from");
            String startTime = getStartTime(userInput);
            String endTime = getTaskTime(userInput, "/to", "end time");
            Event event = new Event(info, startTime, endTime);
            tasks.add(event);
            event.displayMessage(tasks.length());
        } catch (TheCountException e) {
            handleException(e, "Example: event meeting /from 2pm /to 4pm");
        }
    }

    private static void handleDeleteTask(String userInput, TaskList tasks) {
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
            tasks.deleteTask(taskNumber);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | TheCountException e) {
            handleException(e);
        }
    }

    private static void handleInvalidCommand() {
        try {
            throw new TheCountException("WHAT?! I can't count that! Try another command!");
        } catch (TheCountException e) {
            handleException(e);
        }
    }

    private static String getTaskInfo(String userInput, String delimiter) throws TheCountException {
        try {
            String info = userInput.split("\\s+", 2)[1].split(delimiter)[0].trim();
            if (info.isEmpty()) {
                // Throw an exception if task information is not provided
                throw new TheCountException("Description of activity cannot be empty.");
            }
            return info;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TheCountException("Description of activity cannot be empty.");
        }
    }

    private static String getTaskTime(String userInput, String delimiter, String timeType) throws TheCountException {
        try {
            return userInput.split(delimiter)[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TheCountException("Please fill in " + timeType + ".");
        }
    }

    private static String getStartTime(String userInput) throws TheCountException {
        try {
            return userInput.split("/from")[1].split("/to")[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TheCountException("Please fill in start time.");
        }
    }

    private static void handleException(Exception e) {
        Reply errorMsg = new Reply(e.getMessage());
        errorMsg.displayMessage();
    }

    private static void handleException(Exception e, String additionalMessage) {
        Reply errorMsg = new Reply(e.getMessage() + "\n      " + additionalMessage);
        errorMsg.displayMessage();
    }

    private static void handleException(String additionalMessage) {
        Reply errorMsg = new Reply(additionalMessage);
        errorMsg.displayMessage();
    }
}
