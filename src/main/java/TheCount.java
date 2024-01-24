import java.util.Scanner;

public class TheCount {
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
        while (!commandName.equals("BYE")) {
            // Checks for switch case conditions
            commandName = userInput.split("\\s+")[0].toUpperCase();
            switch (commandName) {
                case "BYE":
                    // Prints goodbye for exit
                    goodbye.displayMessage();
                    scanner.close();
                    System.exit(0);
                    break;
                case "LIST":
                    tasks.printList();
                    break;
                case "MARK":
                    try {
                        taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
                        tasks.markTask(taskNumber);
                    } catch (TheCountException e) {
                        Reply errorMsg = new Reply("Invalid task number. I can't count that!");
                        errorMsg.displayMessage();
                    }
                    break;
                case "UNMARK":
                    try {
                        taskNumber = Integer.parseInt(userInput.split("\\s+")[1]);
                        tasks.unmarkTask(taskNumber);
                    } catch (TheCountException e) {
                        Reply errorMsg = new Reply("Invalid task number. I can't count that!");
                        errorMsg.displayMessage();
                    }
                    break;
                case "TODO":
                    try {
                        info = userInput.split("\\s+", 2)[1];
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
                case "DEADLINE":
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
                case "EVENT":
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
                default:
                    try {
                        throw new TheCountException("WHAT?! I can't count that! Try another command!");
                    } catch (TheCountException e) {
                        Reply errorMsg = new Reply(e.getMessage());
                        errorMsg.displayMessage();
                    }
                    break;
            }
            userInput = scanner.nextLine();
        }
        scanner.close();
    }
}
