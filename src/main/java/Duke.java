import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String spacer = "    ____________________________________________________________\n";
    private static ArrayList<Task> toDoList = new ArrayList<>();

    private static void startupMessage() {
        String name = "CBBW";
        botPrint("Hello! I'm " + name 
                           + "\nWhat can I do for you?");
    }

    private static void goodbyeMessage() {
        botPrint("See you again soon!");
    }

    private static void botPrint(String s) {
        s = s.replace("\n", "\n    ");
        System.out.println(spacer + "    " + s + "\n" + spacer);
    }

    private static void printList() {
        System.out.println(spacer);
        for (int i = 1; i <= toDoList.size(); i++) {
            System.out.println("    " + i + "." + toDoList.get(i - 1) + "\n");
        }
        System.out.println(spacer);
    }

    private static void createTodo(String input) {
        Todo t = new Todo(input.split(" ", 2)[1]);
        toDoList.add(t);
        botPrint("Todo Task added!\n" + t.toString() + "\n" + "You now have " + toDoList.size() + " tasks in the list.");
    }

    private static void createEvent(String input) {
        String description = input.substring(5, input.indexOf("/from")).trim();
        String from = input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).trim();
        String to = input.substring(input.indexOf("/to") + 3).trim();
        Event e = new Event(description, from, to);
        toDoList.add(e);
        botPrint("Event Task added!\n" + e.toString() + "\n" + "You now have " + toDoList.size() + " tasks in the list.");
    }

    private static void createDeadline(String input) {
        System.out.println("input: " + input);
        String description = input.substring(8, input.indexOf("/by")).trim();
        String by = input.substring(input.indexOf("/by") + 3).trim();
        Deadline d = new Deadline(description, by);
        toDoList.add(d);
        botPrint("Deadline Task added!\n" + d.toString() + "\n" + "You now have " + toDoList.size() + " tasks in the list.");
    }

    private static void markTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        if (index > 0 && index <= toDoList.size()) {
            Task t = toDoList.get(index - 1);
            t.doTask();
            botPrint("Good job on finishing your task!:\n  " + t);
        } else {
            botPrint("Invalid Index for current list");
        }
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        if (index > 0 && index <= toDoList.size()) {
            Task t = toDoList.get(index - 1);
            t.undoTask();
            botPrint("I've marked this task as undone:\n  " + t);
        } else {
            botPrint("Invalid Index for current list");
        }
    }

    public static void main(String[] args) {
        startupMessage();
        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            if (input.equals("bye")) {
                goodbyeMessage();
                break;
            }
            String action = input.split(" ")[0].toLowerCase();

            switch (action) {
                case "list":
                    printList();
                    break;
                case "todo":
                    createTodo(input);
                    break;
                case "event":
                    createEvent(input);
                    break;
                case "deadline":
                    createDeadline(input);
                    break;
                case "mark":
                    markTask(input);
                    break;
                case "unmark":
                    unmarkTask(input);
                    break;
            }
        }
    }
}
