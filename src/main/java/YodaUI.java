import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;

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
     * Stops the chatbot from chatting.
     */
    public void stopChatting() {
        this.isChatting = false;
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
    public void addTask(Task task) {
        tasks.add(task);
        printMessage("Hmm, added this task, I have:\n" + task + "\nTasks in the list, now you have " + tasks.size() + ", hmm.");
    }


    /**
     * Displays all the tasks in the list.
     */
    public void showTasks() {
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


    // You might need a method in YodaUI to get the size of the task list
    public int getTaskCount() {
        return tasks.size();
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
            details += " | " + deadlineTask.getByString();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            details += " | " + eventTask.getFromString() + " to " + eventTask.getToString();
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