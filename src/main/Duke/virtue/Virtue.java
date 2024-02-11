import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import DukeTask;

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

        public void markAsNotDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    public class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public class Deadline extends Task {
        private String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + this.by + ")";
        }
    }

    public class Event extends Task {
        private String from;
        private String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
        }
    }

    // Exceptions unique to Duke.
    public class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    public class EmptyDescriptionException extends DukeException {

        public EmptyDescriptionException(String message) {
            super(message);
        }
    }

    public class UnknownCommandException extends DukeException {
        public UnknownCommandException(String message) {
            super(message);
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

    // Checks if the last input is todo.
    private boolean isCommandTodo(String command) {
        String firstWord = getFirstWord(command);
        return Objects.equals(firstWord, "todo");
    }

    // Checks if the last input is deadline.
    private boolean isCommandDeadline(String command) {
        String firstWord = getFirstWord(command);
        return Objects.equals(firstWord, "deadline");
    }

    // Checks if the last input is event.
    private boolean isCommandEvent(String command) {
        String firstWord = getFirstWord(command);
        return Objects.equals(firstWord, "event");
    }

    // Checks if the last input is delete.
    private boolean isCommandDelete(String command) {
        String firstWord = getFirstWord(command);
        return Objects.equals(firstWord, "delete");
    }

    // Gets the index input by the user for a mark/unmark/delete command.
    private int getMarkUnmarkDeleteIndex(String command) {
        String otherThanFirstWord = getOtherThanFirstWord(command);
        return Integer.parseInt(otherThanFirstWord);
    }

    // Gets the description for a todo command.
    private String getTodoDescription(String command) throws EmptyDescriptionException {
        try {
            return getOtherThanFirstWord(command);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    // Gets the description for a deadline command.
    private String getDeadlineDescription(String command) throws EmptyDescriptionException {
        try {
            String otherThanFirstWord = getOtherThanFirstWord(command);
            return otherThanFirstWord.split(" /by ", 2)[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    // Gets the deadline for a deadline command.
    private String getDeadlineBy(String command) {
        String otherThanFirstWord = getOtherThanFirstWord(command);
        return otherThanFirstWord.split(" /by ", 2)[1];
    }

    // Gets the description for an event command.
    private String getEventDescription(String command) throws EmptyDescriptionException {
        try {
            String otherThanFirstWord = getOtherThanFirstWord(command);
            return otherThanFirstWord.split(" /from ", 2)[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("OOPS!!! The description of an event cannot be empty.");
        }
    }

    // Gets the start for a deadline command.
    private String getEventFrom(String command) {
        String otherThanFirstWord = getOtherThanFirstWord(command);
        String fromAndTo = otherThanFirstWord.split(" /from ", 2)[1];
        return fromAndTo.split(" /to ", 2)[0];
    }

    // Gets the end for a deadline command.
    private String getEventTo(String command) {
        String otherThanFirstWord = getOtherThanFirstWord(command);
        String fromAndTo = otherThanFirstWord.split(" /from ", 2)[1];
        return fromAndTo.split(" /to ", 2)[1];
    }

    // The task list.
    ArrayList<Task> taskList = new ArrayList<>();

    // Gets the number of tasks in the task list.
    private int numTasks() {
        return taskList.size();
    }

    // Gets the i-th task from the task list, where i is the index.
    private Task getTask(int index) {
        return taskList.get(index - 1);
    }

    // Adds a todo task to the task list.
    private void addTodo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        printHorizontalLine();
        printWithIndent(" Got it. I've added this task:");
        printWithIndent("   " + todo);
        printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        printHorizontalLine();
    }

    // Adds a deadline task to the task list.
    private void addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        printHorizontalLine();
        printWithIndent(" Got it. I've added this task:");
        printWithIndent("   " + deadline);
        printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        printHorizontalLine();
    }

    // Adds an event task to the task list.
    private void addEvent(String description, String from, String to) {
        Event event = new Event(description, from, to);
        taskList.add(event);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        printHorizontalLine();
        printWithIndent(" Got it. I've added this task:");
        printWithIndent("   " + event);
        printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        printHorizontalLine();
    }

    // Marks the i-th task in the task list as done, where i is the index.
    private void markTaskAsDone(int index) {
        Task task = getTask(index);
        task.markAsDone();
        printHorizontalLine();
        printWithIndent(" Nice! I've marked this task as done:");
        printWithIndent("   " + task);
        printHorizontalLine();
    }

    // Marks the i-th task in the task list as not done, where i is the index.
    private void markTaskAsNotDone(int index) {
        Task task = getTask(index);
        task.markAsNotDone();
        printHorizontalLine();
        printWithIndent(" OK, I've marked this task as not done yet:");
        printWithIndent("   " + task);
        printHorizontalLine();
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

    // Deletes the i-th task in the task list, where i is the index.
    private void deleteTask(int index) {
        Task deletedTask = taskList.remove(index - 1);
        int numTasks = numTasks();
        printHorizontalLine();
        printWithIndent(" Noted. I've removed this task:");
        printWithIndent("   " + deletedTask);
        printWithIndent(" Now you have " + numTasks + " tasks in the list.");
        printHorizontalLine();
    }

    // Handles the command input by the user.
    private void handleCommand(String command) throws UnknownCommandException {
        try {
            if (isCommandMark(command)) {
                markTaskAsDone(getMarkUnmarkDeleteIndex(command));
            } else if (isCommandUnmark(command)) {
                markTaskAsNotDone(getMarkUnmarkDeleteIndex(command));
            } else if (isCommandList(command)) {
                printTaskList();
            } else if (isCommandTodo(command)) {
                addTodo(getTodoDescription(command));
            } else if (isCommandDeadline(command)) {
                addDeadline(getDeadlineDescription(command), getDeadlineBy(command));
            } else if (isCommandEvent(command)) {
                addEvent(getEventDescription(command), getEventFrom(command), getEventTo(command));
            } else if (isCommandDelete(command)) {
                deleteTask(getMarkUnmarkDeleteIndex(command));
            } else {
                throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (EmptyDescriptionException e) {
            printHorizontalLine();
            System.out.println("     " + e.getMessage());
            printHorizontalLine();
        }
    }

    // Takes inputs from user until bye has been input.
    private void takeInputsUntilBye() {
        // Waits for command from user
        String command = sc.nextLine();

        // While user hasn't input bye, add task to task list
        while (!isCommandBye(command)) {
            // If list is input, print list, else add task to list
            try {
                handleCommand(command);
            } catch (UnknownCommandException e) {
                printHorizontalLine();
                System.out.println("     " + e.getMessage());
                printHorizontalLine();
            } finally {
                command = sc.nextLine();
            }
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
