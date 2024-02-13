package duke;
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
     * Prints everything in the list
     */
    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Adds the String to tbe printed
     * @param print String to be added then printed immediately to the system
     */
    public void print(String print) {
        this.add(print);
        this.print();
    }

    /**
     * 
     * @param taskType String of the task types
     * @param taskInStringFormat Task in String
     * @param sizeOfTaskList int of current size of TaskList
     */
    public void addNewTask(String taskType, String taskInStringFormat, int sizeOfTaskList) {
        this.add(String.format("Okay! added this %s:", taskType));
        this.add(taskInStringFormat);
        this.add(String.format("Now you have %d tasks in the list.", sizeOfTaskList));
    }

    /**
     * prints when task is deleted
     * @param taskInStringFormat Task in String
     */
    public void deleteTask(String taskInStringFormat) {
        this.add("Okay! this task is now removed:");
        this.add(taskInStringFormat);
    }

    /**
     * prints when task is unmarked
     * @param taskInStringFormat Task in String
     */
    public void markTaskAsNotDone(String taskInStringFormat) {
        this.add("Alright! this task is now unmarked:");
        this.add(taskInStringFormat);
    }

    /**
     * prints when task is marked as done
     * @param taskInStringFormat Task in String
     */
    public void markTaskAsDone(String taskInStringFormat) {
        this.add("Great! I will makr this as done:");
        this.add(taskInStringFormat);
    }

    /**
     * prints when user command = find
     */
    public void beginTofindMatchingTasks() {
        this.add("These tasks matches your search:");
    }

    /**
     * Prints when user command = list
     */
    public void beginToPrintTaskList() {
        this.add("Here are the tasks in your list:");
    }

    /**
     * Prints the task with the index attached, it is used when command = list or find
     * @param taskInStringFormat Task in String at index-1 in the TaskList
     * @param index the index of task relative to the TaskList
     */
    public void printTaskInListWithIndex(String taskInStringFormat, int index) {
        this.add(String.format("%d. %s", index, taskInStringFormat));
    }

    /**
     * Greets the user
     * @param name String name of chatbot
     */
    public void greeting(String name) {
        this.add(String.format("Hello I'm %s", name));
        this.add("What Can I do for you?");
    }

    /**
     * Says goodbye to the user
     */
    public void goodbye() {
        this.add("Goodbye. See you later!");
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
