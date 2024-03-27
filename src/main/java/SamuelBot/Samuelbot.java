package SamuelBot;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Samuelbot class represents the main class for the SamuelBot application.
 * It allows users to manage tasks such as todos, deadlines, and events.
 * Users can add tasks, mark tasks as done, list all tasks, delete tasks, and find tasks by keyword.
 */
public class Samuelbot {
    static final String file_path = "./SamuelBot.txt";
    List<Task> taskList;
    Ui ui;
    Storage storage;

    /**
     * Constructs a new Samuelbot object.
     * Initializes the task list, user interface, and storage.
     */
    public Samuelbot() {
        this.taskList = new ArrayList<>();
        this.ui = new Ui();
        this.storage = new Storage(file_path);
        loadTasksFromFile();
//        Assertions.assertTaskListNotNull(this);
//        Assertions.assertUiNotNull(this);
//        Assertions.assertStorageNotNull(this);
//        Assertions.assertFilePathValid(this);
    }

    /**
     * Saves the tasks to the storage file.
     */
    void saveTasksToFile() {
        storage.saveTasksToFile(taskList);
    }

    /**
     * Loads tasks from the storage file.
     */
    private void loadTasksFromFile() {
        taskList = storage.loadTasksFromFile();
    }

    /**
     * Runs the Samuelbot application.
     * Displays a welcome message and processes user commands until the "bye" command is entered.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine().trim();
            processInput(input);
            saveTasksToFile();
        } while (!input.equals("bye"));
        ui.showGoodbye();
    }

    /**
     * Processes the user input command.
     * @param input The user input command.
     */
    private void processInput(String input) {
        String[] tokens = input.split("\\s+", 2);
        switch (tokens[0]) {
            case "delete":
                try {
                    deleteTask(Integer.parseInt(tokens[1]));
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e ){
                    System.out.println("Sorry, you need to input a valid integer index");
                }
                break;
            case "bye":
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                try {
                    int index = Integer.parseInt(tokens[1]);
                    markTask(index);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid command format.");
                }
                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(tokens[1]);
                    unmarkTask(index);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid command format.");
                }
                break;
            case "todo":
                if (tokens.length == 1 || tokens[1].isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    try {
                        String description = tokens[1].trim();
                        addTodoTask(description);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please provide a description for the task.");
                    }
                }
                break;
            case "deadline":
                if (tokens.length == 1 || tokens[1].isEmpty()) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                }
                else{
                    try {
                        String[] parts = tokens[1].split("/by", 2);
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        addDeadlineTask(description, by);
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("Please provide a description and deadline for the task.");
                    }
                }
                break;
            case "event":
                if (tokens.length == 1 || tokens[1].isEmpty()) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                }
                else {
                    try {
                        String[] parts = tokens[1].split("/from", 2);
                        String description = parts[0].trim();
                        String[] fromTo = parts[1].split("/to", 2);
                        String from = fromTo[0].trim();
                        String to = fromTo[1].trim();
                        addEventTask(description, from, to);
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("Please provide a description, start time, and end time for the event.");
                    }
                }
                break;
            case "find":
                if (tokens.length == 1 || tokens[1].isEmpty()) {
                    System.out.println("OOPS!!! Please provide a keyword to search for.");
                } else {
                    String keyword = tokens[1].trim();
                    findTasks(keyword);
                }
                break;
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
        }
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to mark as done.
     */
    private void markTask(int index) {
        try {
            Task task = taskList.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Marks a task as not done.
     * @param index The index of the task to mark as not done.
     */
    private void unmarkTask(int index) {
        try {
            Task task = taskList.get(index - 1);
            task.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Lists all tasks in the task list.
     */
    private void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }
    }

    /**
     * Adds a todo task to the task list.
     * @param description The description of the todo task.
     */
    private void addTodoTask(String description) {
        Task todoTask = new Todo(description);
        taskList.add(todoTask);
        ui.showTaskAddedConfirmation(todoTask, taskList.size());
    }

    /**
     * Deletes a task from the task list.
     * @param index The index of the task to delete.
     */
    private void deleteTask(int index) {
        try {
            Task removedTask = taskList.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");

            // Print the updated task list
            listTasks();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Adds a deadline task to the task list.
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    private void addDeadlineTask(String description, String by) {
        try {
            Task deadlineTask = new Deadline(description, by);
            taskList.add(deadlineTask);
            ui.showTaskAddedConfirmation(deadlineTask, taskList.size());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use the format yyyy-MM-dd.");
        }
    }

    /**
     * Adds an event task to the task list.
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    private void addEventTask(String description, String from, String to) {
        try {
            Task eventTask = new Event(description, from, to);
            taskList.add(eventTask);
            ui.showTaskAddedConfirmation(eventTask, taskList.size());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please use the format yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Finds tasks containing the specified keyword and displays them.
     * @param keyword The keyword to search for in task descriptions.
     */
    private void findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.showMatchingTasks(matchingTasks);
    }

    public String getResponse(String userInput) {
        if(userInput.equals("bye")){
            return "Bye! Samuelbot hopes to see you again soon!";
        }
        // Save the current standard output
        PrintStream originalOut = System.out;

        // Create a new stream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(outputStream);

        // Set the new output stream
        System.setOut(newOut);

        // Process the input, which will print output to the console
        processInput(userInput);

        // Reset the standard output
        System.setOut(originalOut);

        // Convert the captured output to a string
        String response = outputStream.toString();

        // Return the response
        return response;
    }



    /**
     * The main method to start the Samuelbot application.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Samuelbot bot = new Samuelbot();
        bot.run();
    }
}
