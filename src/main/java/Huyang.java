import java.util.ArrayList;
import java.util.Scanner;

public class Huyang {
//    private String logo =
//            "  ___ ___                                      \n" +
//                    " /   |   \\ __ __ ___.__._____    ____    ____  \n" +
//                    "/    ~    \\  |  <   |  |\\__  \\  /    \\  / ___\\ \n" +
//                    "\\    Y    /  |  /\\___  | / __ \\|   |  \\/ /_/  >\n" +
//                    " \\___|_  /|____/ / ____|(____  /___|  /\\___  / \n" +
//                    "       \\/        \\/          \\/     \\//_____/  ";

    private ArrayList<Task> tasks = new ArrayList<>();
    public void greet() {
        System.out.println("Hello! I'm Huyang");
//        System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    public void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                mark(input);
            } else if (input.startsWith("unmark ")) {
                unmark(input);
            } else if (input.startsWith("todo ")) {
                addTodoTask(input);
            } else if (input.startsWith("deadline ")) {
                addDeadlineTask(input);
            } else if (input.startsWith("event ")) {
                addEventTask(input);
            } else {
                System.out.println("Invalid input.");
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }

    private void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    private void mark(String input) {
        int taskNumber = Integer.parseInt(input.substring(5));
        if (taskNumber >= 1 && taskNumber <= tasks.size()) { // Index out of bounds
            Task task = tasks.get(taskNumber - 1);
            task.check();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void unmark(String input) {
        int taskNumber = Integer.parseInt(input.substring(7).strip());
        if (taskNumber >= 1 && taskNumber <= tasks.size()) { // Index out of bounds
            Task task = tasks.get(taskNumber - 1);
            task.uncheck();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void addTodoTask(String input) {
        String description = input.substring(5).trim();
        tasks.add(new ToDo(description));
        printAddedTask();
    }

    private void addDeadlineTask(String input) {
        int byIndex = input.indexOf("/by");
        String description = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 4).trim(); // Adjusted the index to include space after "/by "
        tasks.add(new Deadline(description, by));
        printAddedTask();
    }

    private void addEventTask(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String description = input.substring(6, fromIndex).trim();
        String start = input.substring(fromIndex + 6, toIndex).trim(); // Adjusted the index to include space after "/from "
        String end = input.substring(toIndex + 4).trim(); // Adjusted the index to include space after "/to "
        tasks.add(new Event(description, start, end));
        printAddedTask();
    }

    private void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void farewell() {
        System.out.print("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Huyang huyang = new Huyang();

        huyang.greet();
        huyang.echo();
        huyang.farewell();
    }
}


