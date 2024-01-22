import java.util.*;
import java.io.*;

public class Howie {

    private static List<Task> tasks = new ArrayList<>();

    public static void printVLine() {
        System.out.println("------------------------------------");
    }

    public static void intro() {
        printVLine();
        System.out.println("Hello! I'm Howie");
        System.out.println("What can I do for you?");
        printVLine();
    }

    public static void exit() {
        printVLine();
        System.out.println("Bye! I'll see you when I see you :)");
        printVLine();
    }

    public static void addToList(Task t) {
        tasks.add(t);
        printVLine();
        System.out.println("Got it! Task has been added:\n" + t + "\nNow you have "
                + tasks.size() + " tasks in the list.");
        printVLine();
    }

    public static void printAllTask(List<Task> tasks) {
        printVLine();
        System.out.println("Here are the list of your tasks:");
        for (int i=1; i<=tasks.size(); i++) {
            Task t = tasks.get(i-1);
            System.out.println(i +"." + t);
        }
        printVLine();
    }
    public static void main(String[] args) throws Exception {
        intro();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] input = br.readLine().split(" ");
            String s1 = input[0];
            StringBuilder name;
            StringBuilder current;
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
                        System.out.println("Acknowledged!\n" + toMark.setDone());
                        printVLine();
                    } catch (IndexOutOfBoundsException e) {
                        printVLine();
                        System.out.println("Opps! I can't seem to find the task.");
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
                        System.out.println("Cannot find task");
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
                    Deadline dlTask = new Deadline(name.toString(), by.toString().trim());
                    addToList(dlTask);
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
                    Event eventTask = new Event(name.toString(), from.toString(), to.toString().trim());
                    addToList(eventTask);
                    break;
                case "todo":
                    name = new StringBuilder();
                    for (int j=1; j<input.length; j++) {
                        name.append(input[j]).append(" ");
                    }
                    Task todo = new Task(name.toString().trim());
                    addToList(todo);
                    break;
            }
            if (s1.equals("bye")) {
                System.exit(0);
            }
        }
    }
}
