import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    /**
     * Print out a line on the screen
     */
    public static void drawLine() {
        System.out.println("------------------------------------------------------" +
                "---------------------------------------------------");
    }

    /**
     * Display a message with two draw lines
     *
     * @param message display message
     */
    public static void displayToScreen(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    public static void printCommandList() {
        drawLine();
        System.out.println("These are the available commands :");
        System.out.println("0. Do " + CommandType.LISTCOMMANDS.toString() + " : " + CommandType.LISTCOMMANDS.getCommand());
        System.out.println("1. Add " + CommandType.TODO.toString() + " task : " + CommandType.TODO.getCommand());
        System.out.println("2. Add " + CommandType.DEADLINE.toString() + " task : " + CommandType.DEADLINE.getCommand());
        System.out.println("3. Add " + CommandType.EVENT.toString() + " task : " + CommandType.EVENT.getCommand());
        System.out.println("4. To " + CommandType.LIST.toString() +" all tasks : " + CommandType.LIST.getCommand());
        System.out.println("5. To " + CommandType.MARK.toString() + " task done : " + CommandType.MARK.getCommand());
        System.out.println("6. To " + CommandType.UNMARK.toString() + " task : " + CommandType.UNMARK.getCommand());
        System.out.println("7. To " + CommandType.DELETE.toString() + " a task : " + CommandType.DELETE.getCommand());
        System.out.println("8. Close chatbot : " + CommandType.BYE.getCommand());
        drawLine();
    }


    /**
     * Add input task into storage
     *
     * @param type Type of the task.
     * @param task The task to be done.
     * @param storage The storage to store the task
     */
    public static void addTask(String type, String task, List<Task> storage) throws DukeException {
        Task newTask;

        if (type.equals(CommandType.TODO.toString())) {
            if (task.equals("")) {
                throw new DukeException("The description is not provided. " +
                        "Write command using format: " +
                        CommandType.TODO.getCommand());
            }
            newTask = new ToDo(task);

        } else if (type.equals(CommandType.DEADLINE.toString())) {

            String[] taskArr = task.split(" /by ");

            if (taskArr.length <= 1) {
                throw new DukeException("The description or deadline is not provided. " +
                        "Write command using format: " +
                        CommandType.DEADLINE.getCommand());
            }

            String by = taskArr[1];
            String description = taskArr[0];
            newTask = new Deadline(description, by);

        } else if (type.equals(CommandType.EVENT.toString())) {
            String[] taskArr = task.split(" /from ");

            if (taskArr.length <= 1) {
                throw new DukeException("The description or event period is not provided. " +
                        "Write command using format: " +
                        CommandType.EVENT.getCommand());
            }

            String[] fromArr = taskArr[1].split(" /to ");

            if (fromArr.length <= 1) {
                throw new DukeException("The description or event period is not provided. " +
                        "Write command using format: " +
                        CommandType.EVENT.getCommand());
            }

            String by = fromArr[0];
            String to = fromArr[1];
            String description = taskArr[0];
            newTask = new Event(description, by, to);
        } else {
            throw new DukeException("This command is unavailable. Please refer to command list by using command: " +
                    CommandType.LIST.getCommand());
        }

        storage.add(newTask);

        String temp = storage.size() > 1 ? " tasks" : " task";
        displayToScreen("Got it. I've added this task:\n" + newTask + "\n" +
                "Now you have " + storage.size() + temp + " in the list.");
    }

    /**
     * List out all the task from the storage
     * @param storage where the task are kept in
     */
    public static void listTask(List<Task> storage) {
        drawLine();
        if (storage.size() == 0) {
            System.out.println("Your task list is empty. Create your first task now!");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + ". " + storage.get(i));
        }
        drawLine();
    }

    /**
     * To mark a task as done
     * @param storage where the task is kept in
     * @param input index of the task to be marked as done
     */
    public static void markDone(List<Task> storage, String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            if (index > storage.size() || index <= 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : " +
                        CommandType.LIST.getCommand() +
                        "\n" +
                        "Write command using format: " +
                        CommandType.MARK.getCommand());
            }

            Task curr = storage.get(index - 1);
            drawLine();
            curr.markDone();
            drawLine();
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: " +
                    CommandType.MARK.getCommand());
        }
    }

    /**
     * To unmark a task
     * @param storage where the task is kept in
     * @param input index of the task to be unmarked
     */
    public static void markUndone(List<Task> storage, String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
             if (index > storage.size() || index <= 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : " +
                        CommandType.LIST.getCommand() +
                        ".\n" +
                        "Write command using format: " +
                        CommandType.UNMARK.getCommand());
            }

            Task curr = storage.get(index - 1);
            drawLine();
            curr.markUndone();
            drawLine();
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: " +
                    CommandType.UNMARK.getCommand());
        }
    }

    /**
     * To delete a task
     * @param storage where the task is kept in
     * @param input index of the task to be deleted
     */
    public static void deleteTask(List<Task> storage, String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            if (index > storage.size() || index <= 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : list.\n" +
                        "Write command using format: " +
                        CommandType.DELETE.getCommand());
            }

            Task curr = storage.get(index - 1);
            storage.remove(index - 1);
            String temp = storage.size() > 1 ? " tasks" : " task";
            displayToScreen("Noted. I've removed this task:\n" + curr + "\nNow you have "
                    + storage.size() + temp + " in the list.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: " +
                    CommandType.DELETE.getCommand());
        }
    }

    /**
     * Provide commands to communicate with chatbot
     */
    public static void startChat() {
        drawLine();
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        drawLine();
        Scanner scanner = new Scanner(System.in);
        List<Task> storage = new ArrayList<>();

        while (true) {
            String command = scanner.nextLine();
            String[] commandArr = command.split(" ", 2);

            try {
                if (command.equals(CommandType.BYE.toString())) {
                    displayToScreen("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals(CommandType.LISTCOMMANDS.toString())) {
                    printCommandList();
                } else if (command.equals(CommandType.LIST.toString())) {
                    listTask(storage);
                } else if (commandArr[0].equals(CommandType.MARK.toString())) {
                    markDone(storage, commandArr.length > 1 ? commandArr[1] : "");
                } else if (commandArr[0].equals(CommandType.UNMARK.toString())) {
                    markUndone(storage, commandArr.length > 1 ? commandArr[1] : "");
                } else if (commandArr[0].equals(CommandType.DELETE.toString())) {
                    deleteTask(storage, commandArr.length > 1 ? commandArr[1] : "");
                } else {
                    addTask(commandArr[0], commandArr.length > 1 ? commandArr[1] : "", storage);
                }
            } catch (DukeException e) {
                displayToScreen(e.getMessage());
            }

        }
    }


    public static void main(String[] args) {
        startChat();
    }
}
