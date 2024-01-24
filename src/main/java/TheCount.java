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
        // Get first word for CommandType
        String commandName = userInput.split("\\s+")[0].toUpperCase();
        int taskNumber = 0;
        String info = null;
        // Checks for BYE command
        while (!getCommandType(userInput).equals(CommandType.BYE)) {
            switch (getCommandType(userInput)) {
                case BYE:
                    // Prints goodbye for exit
                    goodbye.displayMessage();
                    scanner.close();
                    System.exit(0);
                    break;
                case LIST:
                    tasks.printList();
                    break;
                case MARK:
                    try {
                        taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
                        tasks.markTask(taskNumber);
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        Reply errorMsg = new Reply("Please put a number. I can't count that!");
                        errorMsg.displayMessage();
                    } catch (TheCountException e) {
                        Reply errorMsg = new Reply(e.getMessage());
                        errorMsg.displayMessage();
                    }
                    break;
                case UNMARK:
                    try {
                        taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
                        tasks.unmarkTask(taskNumber);
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        Reply errorMsg = new Reply("Please put a number. I can't count that!");
                        errorMsg.displayMessage();
                    } catch (TheCountException e) {
                        Reply errorMsg = new Reply(e.getMessage());
                        errorMsg.displayMessage();
                    }
                    break;
                case TODO:
                    try {
                        try {
                            info = userInput.split("\\s+", 2)[1];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new TheCountException("Description of activity cannot be empty.");
                        }
                        if (info.trim().isEmpty()) {
                            // Throw an exception if task information is not provided
                            throw new TheCountException("Description of activity cannot be empty.");
                        }
                        ToDo todo = new ToDo(info);
                        tasks.add(todo);
                        todo.displayMessage(tasks.length());
                    } catch (TheCountException e) {
                        // Throw an exception if task information is not provided
                        Reply errorMsg = new Reply(e.getMessage());
                        errorMsg.displayMessage();
                    }
                    break;
                case DEADLINE:
                    String deadlineTime = null;
                    try {
                        // Handles Info
                        try {
                            info = userInput.split("\\s+", 2)[1].split("/by")[0].trim();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new TheCountException("Please fill in description.");
                        }

                        // Handles Deadline Time
                        try {
                            deadlineTime = userInput.split("/by")[1].trim();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new TheCountException("Please fill in deadline.");
                        }

                        Deadline deadline = new Deadline(info, deadlineTime);
                        tasks.add(deadline);
                        deadline.displayMessage(tasks.length());

                    } catch (TheCountException e) {
                        Reply errorMsg = new Reply(e.getMessage() +
                                "\n      Example: deadline assignment /by 2pm");
                        errorMsg.displayMessage();
                    }
                    break;
                case EVENT:
                    String startTime = null;
                    String endTime = null;
                    try {
                        // Handles Info
                        try {
                            info = userInput.split("\\s+", 2)[1].split("/from")[0].trim();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new TheCountException("Please fill in description.");
                        }

                        // Handles Start Time
                        try {
                            startTime = userInput.split("/from")[1].trim()
                                    .split("/to")[0].trim();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new TheCountException("Please fill in start time.");
                        }

                        // Handles End Time
                        try {
                            endTime = userInput.split("/to")[1].trim();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new TheCountException("Please fill in end time.");
                        }

                        Event event = new Event(info, startTime, endTime);
                        tasks.add(event);
                        event.displayMessage(tasks.length());

                    } catch (TheCountException e) {
                        Reply errorMsg = new Reply(e.getMessage() +
                                "\n      Example: event meeting /from 2pm /to 4pm");
                        errorMsg.displayMessage();
                    }
                    break;
                case DELETE:
                    try {
                        taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
                        tasks.deleteTask(taskNumber);
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        Reply errorMsg = new Reply("Please put a number. I can't count that!");
                        errorMsg.displayMessage();
                    } catch (TheCountException e) {
                        Reply errorMsg = new Reply(e.getMessage());
                        errorMsg.displayMessage();
                    }
                    break;
                case INVALID:
                    handleInvalidCommand();
                    break;
                default:
                    break;
            }
            userInput = scanner.nextLine();
        }
        scanner.close();
    }

    private static CommandType getCommandType(String userInput) {
        String commandName = userInput.split("\\s+")[0].toUpperCase();
        try {
            return CommandType.valueOf(commandName);
        } catch (IllegalArgumentException e) {
            return CommandType.INVALID;
        }
    }

    private static void handleInvalidCommand() {
        try {
            throw new TheCountException("WHAT?! I can't count that! Try another command!");
        } catch (TheCountException e) {
            Reply errorMsg = new Reply(e.getMessage());
            errorMsg.displayMessage();
        }
    }
}
