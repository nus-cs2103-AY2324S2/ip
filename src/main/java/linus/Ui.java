package linus;

import java.util.ArrayList;

/**
 * Class that prints to the system
 */
public class Ui {
    private ArrayList<String> printList;

    /**
     * Constructor for Ui
     */
    public Ui() {
        this.printList = new ArrayList<String>();
    }
    
    /**
     * Adds the String to be printed
     * @param print String to be printed to the system
     */
    public void add(String print) {
        this.printList.add(print);
    }

    /**
     * 
     * @param taskType String of the task types
     * @param taskInStringFormat Task in String
     * @param sizeOfTaskList int of current size of TaskList
     */
    public void addNewTask(String taskType, String taskInStringFormat, int sizeOfTaskList) {
        add(String.format("Okay! added this %s:", taskType));
        add(taskInStringFormat);
        add(String.format("Now you have %d tasks in the list.", sizeOfTaskList));
    }

    /**
     * Prints when task is deleted
     * @param taskInStringFormat Task in String
     */
    public void deleteTask(String taskInStringFormat) {
        add("Okay! this task is now removed:");
        add(taskInStringFormat);
    }

    /**
     * Prints when task is unmarked
     * @param taskInStringFormat Task in String
     */
    public void markTaskAsNotDone(String taskInStringFormat) {
        add("Alright! this task is now unmarked:");
        add(taskInStringFormat);
    }

    /**
     * Prints when task is marked as done
     * @param taskInStringFormat Task in String
     */
    public void markTaskAsDone(String taskInStringFormat) {
        add("Great! I will mark this as done:");
        add(taskInStringFormat);
    }

    /**
     * Prints when user command = find
     */
    public void beginTofindMatchingTasks() {
        add("These tasks matches your search:");
    }

    /**
     * Prints when user command = list
     */
    public void beginToPrintTaskList() {
        add("Here are the tasks in your list:");
    }

    /**
     * Prints the task with the index attached, it is used when command = list or find
     * @param taskInStringFormat Task in String at index-1 in the TaskList
     * @param index the index of task relative to the TaskList
     */
    public void printTaskInListWithIndex(String taskInStringFormat, int index) {
        add(String.format("%d. %s", index, taskInStringFormat));
    }

    /**
     * Prints when user command = sort (method)
     * @param sortBy String of sorted method
     */
    public void printSortedMessage(String sortBy) {
        add(String.format("List has been sorted by %s", sortBy));
    }

    /**
     * Prints when user file is corrupted
     */
    public void createNewFileForUser() {
        add("Creating new file for you instead");
    }

    /**
     * Prints when file is found
     */
    public void fileFoundForUser() {
        add("Found the file... loading");
    }

    /**
     * Prints when file is saved
     */
    public void saveUserFile() {
        add("Saving your file...");
    }

    /**
     * Prints when a new directory is made 
     */
    public void makeNewDirectoryForUser() {
        add("Created data folder as none was found");
    }

    /**
     * Greets the user
     * @param name String name of chatbot
     */
    public void greeting(String name) {
        add(String.format("Hello I'm %s", name));
        add("What Can I do for you?");
    }

    /**
     * Says goodbye to the user
     */
    public void goodbye() {
        add("Goodbye. See you later!");
    }

    /**
     * Prints everything in the list with borders and clear it
     */
    @Override
    public String toString() {
        String finalString = "";
        for (int i = 0; i < printList.size(); i++) {
            if (i == printList.size() - 1) {
                finalString += String.format("%s", printList.get(i));
            } else {
                finalString += String.format("%s\n", printList.get(i));
            }
        }
        printList.clear();
        return finalString;
    }
}
