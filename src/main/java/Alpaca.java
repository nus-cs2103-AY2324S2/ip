import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Alpaca {
    static String name = "Alpaca";
    static Scanner scanner;
    static ArrayList<Task> list = new ArrayList<Task>();

    private static void divider() {
        System.out.println("____________________________________________________________");
    }

    private static String printTask(Task task) {
        return "[" + (task.isDone() ? "X" : " ") + "]" + task.getName();
    }

    private static void greeting() {
        divider();
        System.out.println("Hihi! I'm " + Alpaca.name + "\nWhat can I do for you?");
        divider();
    }

    private static void bye() {
        divider();
        System.out.println("cucu");
        divider();
    }

    private static void list() {
        divider();
        int counter = 1;
        for (Task i : list) {
            System.out.println(counter + ". " + printTask(i));
            counter++;
        }
        divider();
    }

    private static void add(String item) {
        list.add(new Task(item));
        divider();
        System.out.println("added: " + item);
        divider();
    }

    private static Boolean isValidPrefixNumber(String prefix, String input) {
        Pattern pattern = Pattern.compile("^" + prefix + " " + "[0-9]+");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private static int getPrefixNumber(String input) {
        return Integer.parseInt(input.replaceAll("[^0-9]", ""));
    }

    private static Boolean isValidIndex(int index) {
        if (index > 0 && index <= list.size()) {
            return true;
        }
        divider();
        System.out.println("Sorry, that is not a valid index.");
        divider();
        return false;
    }

    private static void mark(int index) {
        if (!isValidIndex(index)) return;
        Task task = list.get(index-1);
        task.setDone();
        divider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(printTask(task));
        divider();
    }

    private static void unmark(int index) {
        if (!isValidIndex(index)) return;
        Task task = list.get(index-1);
        task.setUndone();
        divider();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(printTask(task));
        divider();
    }

    private static void processInput() {
        String line = scanner.nextLine();
        if (line.equals("bye")) {
            bye();
            return;
        } else if (line.equals("list")) {
            list();
        } else if (isValidPrefixNumber("mark", line)) {
            mark(getPrefixNumber(line));
        } else if (isValidPrefixNumber("unmark", line)) {
            unmark(getPrefixNumber(line));
        } else {
            add(line);
        }
        processInput();
    }
    
    public static void main(String[] args) {
        greeting();
        scanner = new Scanner(System.in);
        processInput();
        scanner.close();
    }
}