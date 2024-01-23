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
        System.out.println("0. List all commands : list command");
        System.out.println("1. Add todo task : todo [your task]");
        System.out.println("2. Add deadline task : deadline [your task] /by [deadline");
        System.out.println("3. Add event task : event [your task] /from [start time] /to [end time]");
        System.out.println("4. List all tasks : list");
        System.out.println("5. Mark task done : mark [task number (integer)]");
        System.out.println("6. Mark task undone : unmark [task number (integer)]");
        System.out.println("7. Close chatbot : bye");
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

        if (type.equals("todo")) {
            if (task.equals("")) {
                throw new DukeException("The description is not provided. " +
                        "Write command using format: " +
                        "todo [your task]");
            }
            newTask = new ToDo(task);

        } else if (type.equals("deadline")) {

            String[] taskArr = task.split(" /by ");

            if (taskArr.length <= 1) {
                throw new DukeException("The description or deadline is not provided. " +
                        "Write command using format: " +
                        "deadline [your task] /by [deadline]");
            }

            String by = taskArr[1];
            String description = taskArr[0];
            newTask = new Deadline(description, by);

        } else if (type.equals("event")) {
            String[] taskArr = task.split(" /from ");

            if (taskArr.length <= 1) {
                throw new DukeException("The description or event period is not provided. " +
                        "Write command using format: " +
                        "event [your task] /from [start time] /to [end time]");
            }

            String[] fromArr = taskArr[1].split(" /to ");

            if (fromArr.length <= 1) {
                throw new DukeException("The description or event period is not provided. " +
                        "Write command using format: " +
                        "event [your task] /from [start time] /to [end time]");
            }

            String by = fromArr[0];
            String to = fromArr[1];
            String description = taskArr[0];
            newTask = new Event(description, by, to);
        } else {
            throw new DukeException("This command is unavailable. Please refer to command list by using command: " +
                    "list command");
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
            if (index == 0) {
                throw new DukeException("I don't know which task to mark done. Write command using format: " +
                        "mark [task number (integer)]");
            } else if (index > storage.size() || index < 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : list\n" +
                        "Write command using format: " +
                        "mark [task number (integer)]");
            }

            Task curr = storage.get(index - 1);
            drawLine();
            curr.markDone();
            drawLine();
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: " +
                    "mark [task number (integer)]");
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
            if (index == 0) {
                throw new DukeException("I don't know which task to mark undone. Write command using format: " +
                        "mark [task number (integer)]");
            } else if (index > storage.size() || index < 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : list.\n" +
                        "Write command using format: " +
                        "mark [task number (integer)]");
            }

            Task curr = storage.get(index - 1);
            drawLine();
            curr.markUndone();
            drawLine();
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: " +
                    "mark [task number (integer)]");
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
                if (command.equals("bye")) {
                    displayToScreen("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list command")) {
                    printCommandList();
                } else if (command.equals("list")) {
                    listTask(storage);
                } else if (commandArr[0].equals("mark")) {
                    markDone(storage, commandArr.length > 1 ? commandArr[1] : "0");
                } else if (commandArr[0].equals("unmark")) {
                    markUndone(storage, commandArr.length > 1 ? commandArr[1] : "0");
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
