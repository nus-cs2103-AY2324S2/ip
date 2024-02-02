package yoda;
import yoda.task.Task;
import yoda.task.TaskList;
import yoda.storage.Storage;
import yoda.parser.Parser;

import java.io.IOException;


public class YodaUI {
    private final TaskList taskList;
    private final String chatbotName;
    private boolean isChatting;
    private final Storage storage;

    /**
     * Constructor to initialize the chatbot with a name, a TaskList, and a Storage.
     * @param chatbotName The name of the chatbot.
     * @param taskList The TaskList object to manage tasks.
     * @param storage The Storage object for handling task persistence.
     */
    public YodaUI(String chatbotName, TaskList taskList, Storage storage) {
        this.chatbotName = chatbotName;
        this.isChatting = true;
        this.taskList = taskList;
        this.storage = storage;
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
     * Delegates to TaskList to mark a task as done.
     * @param taskNumber The number of the task to mark as done.
     */
    public void markTaskAsDone(int taskNumber) {
        try {
            taskList.markTaskAsDone(taskNumber);
            Task task = taskList.getTask(taskNumber);
            printMessage("Done, this task is:\n" + task);
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    // Method to get the TaskList object
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Marks a task as undone.
     * Delegates to TaskList to mark a task as undone.
     * @param taskNumber The number of the task to mark as not done.
     */
    public void markTaskAsUndone(int taskNumber) {
        try {
            taskList.markTaskAsUndone(taskNumber);
            Task task = taskList.getTask(taskNumber);
            printMessage("Undone, this task remains:\n" + task);
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    /**
     * Deletes a task from the list.
     * Delegates to TaskList to delete a task.
     * @param taskNumber The number of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        try {
            taskList.deleteTask(taskNumber);
            printMessage("Removed, this task has been:\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    /**
     * Adds a new task to the list.
     * Delegates to TaskList to add a new task.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.addTask(task);
        printMessage("Hmm, added this task, I have:\n" + task + "\nTasks in the list, now you have " + taskList.size() + ", hmm.");
    }

    /**
     * Displays all the tasks in the list.
     * Delegates to TaskList to get the string representation of tasks.
     */
    public void showTasks() {
        printMessage(taskList.toString());
    }

    public void saveTasks(TaskList taskList) {
        try {
            storage.saveTasks(taskList);
            printMessage("Saved, your task list has been.");
        } catch (IOException e) {
            printMessage("Error saving tasks: " + e.getMessage());
        }
    }


    /**
     * Prints a message wrapped with lines for better readability.
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
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
