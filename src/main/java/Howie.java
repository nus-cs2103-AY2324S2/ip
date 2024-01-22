import java.util.*;
import java.io.*;

/**
 * The chat-bot To-Do program named Howie which keeps tracks of major major tasks such as
 * todo, event and deadlines.
 * @author Koo Zhuo Hui
 */
public class Howie {

    private static List<Task> tasks = new ArrayList<>();

    /**
     * Prints a vertical line.
     */
    public static void printVLine() {
        System.out.println("------------------------------------");
    }

    /**
     * Howie's first introduction message to user when application is started.
     */
    public static void intro() {
        printVLine();
        System.out.println("Hello! I'm Howie! My hobby is to keep track of TASKS. Let me help you!");
        System.out.println("So, what can I do for you?");
        printVLine();
    }

    /**
     * Exits the program when exit command is entered.
     */
    public static void exit() {
        printVLine();
        System.out.println("Bye! I'll see you when I see you :)");
        printVLine();
        System.exit(0);
    }

    /**
     * Stores a specific task.
     * @param t A Task.
     */
    public static void addToList(Task t) {
        tasks.add(t);
        printVLine();
        System.out.println("Got it! Task has been added:\n" + t + "\nNow you have "
                + tasks.size() + " tasks in the list.");
        printVLine();
    }

    /**
     * Prints the list of tasks that is currently stored by Howie.
     * @param tasks The collection of tasks.
     */
    public static void printAllTask(List<Task> tasks) {
        printVLine();
        if (tasks.isEmpty()) {
            System.out.println("Your list is now EMPTY! Time for you to have a break!");
            printVLine();
            return;
        }
        System.out.println("You have " + tasks.size() + " left. There are:");
        for (int i=1; i<=tasks.size(); i++) {
            Task t = tasks.get(i-1);
            System.out.println(i +"." + t);
        }
        printVLine();
    }

    /**
     * Deletes a task from Howie's list.
     * @param tasks The collection of tasks.
     * @param i 1-Based index of the task to be deleted.
     */
    public static void delete(List<Task> tasks, int i) {
        if (i > tasks.size() || i <= 0) {
            printVLine();
            throw new DukeException("Hmm...I can't delete something that isn't there :O");
        } else {
            Task t = tasks.remove(i-1);
            printVLine();
            System.out.println("Okay! The following task has been removed:\n" + t);
            printAllTask(tasks);
        }
    }

    /**
     * Prints a message when a blank task has been entered.
     */
    public static void emptyTaskMessage() {
        printVLine();
        throw new DukeException("Hey! You've just entered an unnamed task... Try to give a description/name of your task :)");
    }

    /**
     * Prints a message when an invalid format has been entered.
     */
    public static void invalidFormat() {
        printVLine();
        throw new DukeException("I see you've entered an invalid format. Type 'help' if you're unsure :)");
    }

    /**
     * Prints the list of commands available to Howie.
     */
    public static void helpMessage() {
        printVLine();
        System.out.println("Below is a list of available commands for me:\n" +
                "   todo [task_name]\n" +
                "   event [task_name] /from [date] /to [date]\n" +
                "   deadline [task_name] /by [date]\n" +
                "   list - to see all tasks\n" +
                "   mark/unmark [index]\n" +
                "   bye - to exit the programme");
        printVLine();
    }

    /**
     * Initialises the program.
     * @param args Input arguments.
     * @throws Exception Throws DukeException and IOException when invalid commands are entered
     * or input exception occurs.
     */
    public static void main(String[] args) throws Exception {
        intro();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] input = br.readLine().split(" ");
            String s1 = input[0];
            StringBuilder name;
            StringBuilder current;
            try {
                switch (s1) {
                    case "list":
                        printAllTask(tasks);
                        break;
                    case "bye":
                        exit();
                        break;
                    case "mark":
                        try {
                            Task toMark = tasks.get(Integer.parseInt(input[1])-1);
                            printVLine();
                            System.out.println("Acknowledged!!\n" + toMark.setDone());
                            printVLine();
                        } catch (IndexOutOfBoundsException e) {
                            printVLine();
                            System.out.println("Oppss...I can't seem to find the task you're looking for. Type 'list' to see the the tasks that you have!");
                            printVLine();
                        }
                        break;
                    case "unmark":
                        try {
                            Task toMark = tasks.get(Integer.parseInt(input[1])-1);
                            printVLine();
                            System.out.println("Acknowledged!\n" + toMark.setUndone());
                            printVLine();
                        } catch (IndexOutOfBoundsException e) {
                            printVLine();
                            System.out.println("Oppss...I can't seem to find the task you're looking for. Type 'list' to see the the tasks that you have!");
                            printVLine();
                        }
                        break;
                    case "deadline":
                        name =  new StringBuilder();
                        StringBuilder by =  new StringBuilder();
                        current = name;
                        int i = 1;
                        for (; i<input.length; i++) {
                            if (input[i].equals("/by")) {
                                name = current;
                                current = by;
                                continue;
                            }
                            current.append(input[i]).append(" ");
                        }
                        by = current;
                        if (name.length() == 0) {
                            emptyTaskMessage();
                        } else if (name.compareTo(by) == 0) {
                            invalidFormat();
                        } else {
                            Deadline dlTask = new Deadline(name.toString(), by.toString().trim());
                            addToList(dlTask);
                        }
                        break;
                    case "event":
                        name =  new StringBuilder();
                        StringBuilder from =  new StringBuilder();
                        StringBuilder to =  new StringBuilder();
                        current = name;
                        i = 1;
                        for (; i<input.length; i++) {
                            if (input[i].equals("/from")) {
                                name = current;
                                current = from;
                                continue;
                            } else if (input[i].equals("/to")) {
                                from = current;
                                current = to;
                                continue;
                            }
                            current.append(input[i]).append(" ");
                        }
                        if (name.length() == 0) {
                            emptyTaskMessage();
                        } else if (from.length() == 0 || to.length() == 0) {
                            invalidFormat();
                        } else {
                            Event eventTask = new Event(name.toString(), from.toString(), to.toString().trim());
                            addToList(eventTask);
                        }
                        break;
                    case "todo":
                        name = new StringBuilder();
                        for (int j=1; j<input.length; j++) {
                            name.append(input[j]).append(" ");
                        }
                        if (name.length() == 0) {
                            emptyTaskMessage();
                        } else {
                            Task todo = new Task(name.toString().trim());
                            addToList(todo);
                        }
                        break;
                    case "delete":
                        delete(tasks, Integer.parseInt(input[1]));
                        break;
                    case "help":
                        helpMessage();
                        break;
                    default:
                        printVLine();
                        throw new DukeException("Aww! I do not recognise this command. Type 'help' if you're unsure :)");
                }
            } catch (DukeException e) {
                System.out.println(e);
                printVLine();
            }
        }
    }
}
