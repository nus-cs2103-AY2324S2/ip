import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class YodaUI {

    private final ArrayList<Task> tasks; // List to store the tasks
    private final String chatbotName; // Name of the chatbot
    private boolean isChatting; // Flag to check if the chatbot is currently chatting

    /**
     * Constructor to initialize the chatbot with a name.
     * @param chatbotName The name of the chatbot.
     */
    public YodaUI(String chatbotName) {
        this.chatbotName = chatbotName;
        this.isChatting = true;
        this.tasks = new ArrayList<>();
    }


    /**
     * Checks if the chatbot is currently chatting.
     * @return true if chatting, false otherwise.
     */
    public boolean isChatting() {
        return this.isChatting;
    }

    /**
     * Marks a task as done.
     * @param taskNumber The number of the task to mark as done.
     */
    public void markTaskAsDone(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        printMessage("Done, this task is:\n" + task);
    }

    /**
     * Marks a task as undone.
     * @param taskNumber The number of the task to mark as not done.
     */
    public void markTaskAsUndone(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsUndone();
        printMessage("Undone, this task remains:\n" + task);
    }

    /**
     * Deletes a task from the list.
     * @param taskNumber The number of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        Task task = tasks.remove(taskNumber - 1);
        printMessage("Removed, this task has been:\n" + task + "\nTasks in the list, now you have " + tasks.size() + ", hmm.");
    }


    /**
     * Adds a new task to the list.
     * @param task The task to be added.
     */
    private void addTask(Task task) {
        tasks.add(task);
        printMessage("Hmm, added this task, I have:\n" + task + "\nTasks in the list, now you have " + tasks.size() + ", hmm.");
    }


    /**
     * Displays all the tasks in the list.
     */
    private void showTasks() {
        if (tasks.isEmpty()) {
            printMessage("Empty, your task list is.");
            return;
        }

        StringBuilder response = new StringBuilder("Tasks in your list, here they are:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        printMessage(response.toString().trim());
    }


    /**
     * Handles user input and performs actions based on the command.
     * @param input The user input string.
     * @throws Exception if there is an issue in processing the command.
     */
    public void handleUserInput(String input) throws Exception {
        String[] parts = input.split("\\s+", 2);
        Command command = Command.fromString(parts[0]);

        try {
            switch (command) {
                case BYE:
                    printMessage("Farewell. See you again, I hope!");
                    this.isChatting = false;
                    break;
                case LIST:
                    showTasks();
                    break;
                case SAVE:
                    saveTasks(); // Call to save tasks to the file
                    break;
                case DELETE:
                    performTaskOperation(parts, this::deleteTask);
                    break;
                case MARK:
                    performTaskOperation(parts, this::markTaskAsDone);
                    break;
                case UNMARK:
                    performTaskOperation(parts, this::markTaskAsUndone);
                    break;
                case TODO:
                    addTask(new Todo(parts[1]));
                    break;
                case DEADLINE:
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                    break;
                case EVENT:
                    String[] eventParts = parts[1].split(" /from ", 2);
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    addTask(new Event(eventParts[0], timeParts[0], timeParts[1]));
                    break;
                default:
                    printMessage("Sorry, I am. What that means, I do not know :-(");
            }
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    /**
     * Performs a task operation (delete, mark, unmark) based on the user input.
     * @param parts The split input containing the command and task number.
     * @param operation The operation to be performed on the task.
     * @throws Exception if the task number is invalid.
     */
    private void performTaskOperation(String[] parts, TaskOperation operation) throws Exception {
        if (parts.length > 1) {
            int taskNumber = parseTaskNumber(parts[1]);
            operation.perform(taskNumber);
        } else {
            throw new Exception("Specify a task number, you must.");
        }
    }

    /**
     * Parses the task number from the input string.
     * @param input The input string containing the task number.
     * @return The parsed task number.
     * @throws Exception if the input is not a valid number or out of range.
     */
    private int parseTaskNumber(String input) throws Exception {
        try {
            int taskNumber = Integer.parseInt(input);
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new Exception("Valid task number, provide you must.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new Exception("A number, enter you must.");
        }
    }

    /**
     * Prints a message wrapped with lines for better readability.
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        printLine();
        System.out.println("" + message);
        printLine();
    }

    /**
     * Functional interface for task operations like delete, mark, and unmark.
     */
    @FunctionalInterface
    private interface TaskOperation {
        void perform(int taskNumber) throws Exception;
    }

    /**
     * Saves the current task list to a file called yoda.txt.
     */
    public void saveTasks() {
//        String currentDir = System.getProperty("user.dir");
//        System.out.println("Current directory: " + currentDir);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./data/yoda.txt"))) {
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task) + System.lineSeparator());
            }
            printMessage("Saved, your task list has been.");
        } catch (IOException e) {
            printMessage("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a task to a formatted string suitable for file storage.
     * @param task The task to convert.
     * @return A string representing the task in the file format.
     */
    private String taskToFileFormat(Task task) {
        String status = task.isDone() ? "1" : "0";
        String type = task instanceof Todo ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Event ? "E" : "";
        String details = task.getDescription();

        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            details += " | " + deadlineTask.getBy();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            details += " | " + eventTask.getFrom() + " to " + eventTask.getTo();
        }
        return type + " | " + status + " | " + details;
    }



    /**
     * Prints a line for visual separation in the console output.
     */
    private void printLine() {
        System.out.println("________________________________________________________");
    }

    /**
     * Prints a greeting message when the chatbot starts.
     */
    public void printGreeting() {
        printLine();
        System.out.println("Greetings! " + chatbotName + ", I am\nAssist you, may I?");
        printLine();
    }

}