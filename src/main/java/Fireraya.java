import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Fireraya {
    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void start() {
        System.out.println("--------------------------------");
        System.out.println("Hello, my name is Fireraya!");
        System.out.println("What can I do for you today sir?");
        System.out.println("--------------------------------");
    }

    public static void addTask(String a) {
        tasks.add(new Task(a));
        System.out.println("added: " + a);
    }

    public static void addTodo(String a) {
        tasks.add(new Todo(a));
        System.out.println("I've added this Todo:");
        Task last = tasks.get(tasks.size() - 1);
        System.out.println(last.toString());
        taskCount();
    }

    public static void taskCount() {
        int number = tasks.size();
        System.out.println("Now you have " + number + " tasks in the list");
    }

    public static void addDeadline(String task, String deadline) {
        tasks.add(new Deadline(task, deadline));
        System.out.println("I've added this Deadline:");
        Task last = tasks.get(tasks.size() - 1);
        System.out.println(last.toString());
        taskCount();
    }

    public static void addEvent(String task, String from, String to) {
        tasks.add(new Event(task, from, to));
        System.out.println("I've added this Event:");
        Task last = tasks.get(tasks.size() - 1);
        System.out.println(last.toString());
        taskCount();
    }

    public static void listTasks() {
        System.out.println("Here is a list of your tasks!");
    for (int i = 0; i < tasks.size(); i++) {
        Task current = tasks.get(i);
        int num = i + 1;
        System.out.println(num + "." + current.toString());
        }
    }

    public static void listTask(int i) {
        Task current = tasks.get(i);
        int num = i + 1;
        System.out.println( num + "." + current.toString());
    }

    public static void end() {
        System.out.println("Bye, hope to see you soon!");
    }

    public static void parse() {};

    public static void main(String[] args) throws FirerayaException {

        start();

        Scanner scanner = new Scanner(System.in);

        try {

            while (true) {
                String input = scanner.nextLine();
                String[] all = input.split(" ");
                String keyword = all[0];
                int arrLen = all.length;


                if (keyword.equals("bye")) {
                    end();
                    break;
                }

                if (keyword.equals("list")) {
                    listTasks();
                    continue;
                }

                if (keyword.equals("mark")) {
                    if (all.length > 2) {
                        throw new InvalidNumOfArgsException();
                    }
                    int curr = Integer.parseInt(all[1]) - 1;
                    if (tasks.size() <= curr || curr == -1) {
                        throw new FirerayaException("That task does not exist!");
                    }
                    tasks.get(curr).markAsDone();
                    System.out.println("Good job completing these tasks!:");
                    listTask(curr);
                    continue;
                }

                if (keyword.equals("unmark")) {
                    if (all.length > 2) {
                        throw new InvalidNumOfArgsException();
                    }
                    int curr = Integer.parseInt(all[1]) - 1;
                    if (tasks.size() <= curr || curr == -1) {
                        throw new FirerayaException("That task does not exist!");
                    }
                    tasks.get(curr).markAsUndone();
                    System.out.println("Oh no :<. Task Undone.");
                    listTask(curr);
                    continue;
                }

                if (keyword.equals("todo")) {
                    if (arrLen == 1) {
                        throw new FirerayaException("Error! Description of a todo cannot be empty");
                    }
                    String rest = String.join(" ", Arrays.copyOfRange(all, 1, arrLen));
                    addTodo(rest);
                    continue;
                }

                if (keyword.equals("deadline")) {
                    int index = -1;
                    int i = 0;
                    String breakpoint = "/by";
                    for (String element : all) {
                        if (element.equals(breakpoint)) {
                            index = i;
                            break;
                        }
                        i++;
                    }
                    if (index == -1) {
                        throw new FirerayaException("No /by detected in deadline");
                    }
                    String deadline = String.join(" ", Arrays.copyOfRange(all, index + 1, arrLen));
                    String task = String.join(" ", Arrays.copyOfRange(all, 1, index));
                    addDeadline(task, deadline);
                    continue;
                }


                if (keyword.equals("event")) {
                    int indexf = -1;
                    int indext = -1;
                    int f = 0;
                    int t = 0;
                    String fromPoint = "/from";
                    String toPoint = "/to";

                    for (String element : all) {
                        if (element.equals(fromPoint)) {
                            indexf = f;
                            break;
                        }
                        f++;
                    }
                    if (indexf == -1) {
                        throw new FirerayaException("No /from detected in deadline");
                    }
                    for (String element : all) {
                        if (element.equals(toPoint)) {
                            indext = t;
                            break;
                        }
                        t++;
                    }
                    if (indext == -1) {
                        throw new FirerayaException("No /to detected in deadline");
                    }

                    String task = String.join(" ", Arrays.copyOfRange(all, 1, indexf));
                    String from = String.join(" ", Arrays.copyOfRange(all, indexf + 1, indext));
                    String to = String.join(" ", Arrays.copyOfRange(all, indext + 1, arrLen));
                    addEvent(task, from, to);
                    continue;
                }

                throw new FirerayaException("Error: " + input + " not supported");
            }
            scanner.close();
        } catch (FirerayaException e) {
            System.out.println(e.getMessage());
        }
    }
}
