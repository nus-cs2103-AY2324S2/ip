import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        private String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmarkAsNotDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    // The scanner the chatbot uses to scan users' inputs.
    Scanner sc = new Scanner(System.in);

    // A horizontal line.
    private final String horizontalLine = "____________________________________________________________";

    // Prints with an indention.
    private void printWithIndent(String str) {
        System.out.println("    " + str);
    }

    // Prints a horizontal line.
    private void printHorizontalLine() {
        printWithIndent(horizontalLine);
    }

    // Gets the first word of a string.
    private String getFirstWord(String str) {
        return str.split(" ", 2)[0];
    }

    // Gets the words of a string other than the first one.
    private String getOtherThanFirstWord(String str) {
        return str.split(" ", 2)[1];
    }

    // Greets the user.
    private void greet() {
        printHorizontalLine();
        printWithIndent("Hello! I'm Virtue \n    What can I do for you?");
        printHorizontalLine();
    }

    // Exits with a goodbye message.
    private void bye() {
        printHorizontalLine();
        printWithIndent("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    // Checks if the last input is bye.
    private boolean isCommandBye(String command) {
        return Objects.equals(command, "bye");
    }

    // Checks if the last input is list.
    private boolean isCommandList(String command) {
        return Objects.equals(command, "list");
    }

    // Checks if the last input is mark.
    private boolean isCommandMark(String command) {
        String firstWord = getFirstWord(command);
        return Objects.equals(firstWord, "mark");
    }

    // Checks if the last input is unmark.
    private boolean isCommandUnmark(String command) {
        String firstWord = getFirstWord(command);
        return Objects.equals(firstWord, "unmark");
    }

    // Gets the index input by the user.
    private int getIndex(String command) {
        String otherThanFirstWord = getOtherThanFirstWord(command);
        return Integer.parseInt(otherThanFirstWord);
    }

    // The task list.
    ArrayList<Task> taskList = new ArrayList<>();

    // Adds the task to the task list.
    private void addTask(String task) {
        taskList.add(new Task(task));
        printHorizontalLine();
        printWithIndent(" added: " + task);
        printHorizontalLine();;
    }

    // Prints the task list.
    private void printTaskList() {
        int numOfTasks = taskList.size();
        printHorizontalLine();
        printWithIndent(" Here are the tasks in your list:");

        for (int index = 1; index <= numOfTasks; index++) {
            printWithIndent(" " + index + "." + taskList.get(index - 1));
        }

        printHorizontalLine();
    }

    // Gets the i-th task from the task list, where i is the index.
    private Task getTask(int index) {
        return taskList.get(index);
    }

    // Takes inputs from user until bye has been input.
    private void takeInputsUntilBye() {
        // Waits for command from user
        String command = sc.nextLine();

        // While user hasn't input bye, add task to task list
        while (!isCommandBye(command)) {
            // If list is input, print list, else add task to list
            if (isCommandList(command)) {
                printTaskList();
            } else {
                addTask(command);
            }

            command = sc.nextLine();
        }
    }

    // Runs the chatbot.
    private void run() {
        greet();
        takeInputsUntilBye();
        bye();
    }

    public static void main(String[] args) {
        Duke virtue = new Duke();
        virtue.run();
    }
}
