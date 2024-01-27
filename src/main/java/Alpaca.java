import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Tasks.Task;
import Tasks.ToDo;
import Tasks.Deadline;
import Tasks.Event;

import java.util.ArrayList;

public class Alpaca {
    static String name = "Alpaca";
    static Scanner scanner;
    static ArrayList<Task> list = new ArrayList<Task>();

    private static void divider() {
        System.out.println("____________________________________________________________\n");
    }

    private static String printTask(Task task) {
        String type = "[" + task.getType() + "]";
        String mark = "[" + (task.isDone() ? "X" : " ") + "]";
        return type + mark + " " + task.getName();
    }

    private static void greeting() {
        divider();
        System.out.println("Hihi! I'm " + Alpaca.name + "\nWhat can I do for you?");
        divider();
    }

    private static void bye() {
        System.out.println(" Here are the tasks in your list:");
        System.out.println("cucu");
    }

    private static void list() {
        int counter = 1;
        for (Task i : list) {
            System.out.println(counter + "." + printTask(i));
            counter++;
        }
    }

    private static void add(String item) {
        Task task;
        if (isValidPrefix("todo", item)) {
            task = new ToDo(processParams(removePrefix(item, "todo ")));
        } else if (isValidPrefix("deadline", item)) {
            task = new Deadline(processParams(removePrefix(item, "deadline ")));
        } else if (isValidPrefix("event", item)) {
            task = new Event(processParams(removePrefix(item, "event ")));
        } else {
            System.out.println("That is not a valid input sorry :)");
            return;
        }
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + printTask(task));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private static String processParams(String input) {
        Pattern pattern = Pattern.compile("^[^\\/]+");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) return input;
        String result = matcher.group();
        String tmp = input.replaceFirst("^[^/]+", "");
        if (tmp.equals("")) return input;
        tmp = tmp.replaceAll("(/)([^ ]+)", "$2:");
        result = result + "(" + tmp + ")";
        return result;
    }

    private static Boolean isValidPrefix(String prefix, String input) {
        Pattern pattern = Pattern.compile("^" + prefix + " " + ".+");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private static int getPrefixNumber(String input) {
        return Integer.parseInt(input.replaceAll("[^0-9]", ""));
    }

    private static String removePrefix(String input, String prefix) {
        return input.replaceFirst("^" + prefix, "");
    }

    private static Boolean isValidIndex(int index) {
        if (index > 0 && index <= list.size()) {
            return true;
        }
        System.out.println("Sorry, that is not a valid index.");
        return false;
    }

    private static void mark(int index) {
        if (!isValidIndex(index)) return;
        Task task = list.get(index-1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(printTask(task));
    }

    private static void unmark(int index) {
        if (!isValidIndex(index)) return;
        Task task = list.get(index-1);
        task.setUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(printTask(task));
    }

    private static void processInput() {
        String line = scanner.nextLine();
        divider();
        if (line.equals("bye")) {
            bye();
            return;
        } else if (line.equals("list")) {
            list();
        } else if (isValidPrefix("mark", line)) {
            mark(getPrefixNumber(line));
        } else if (isValidPrefix("unmark", line)) {
            unmark(getPrefixNumber(line));
        } else {
            add(line);
        }
        divider();
        processInput();
    }
    
    public static void main(String[] args) {
        greeting();
        scanner = new Scanner(System.in);
        processInput();
        scanner.close();
    }
}