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
        // System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    public void runChatbot() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            try {
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
                } else if (input.startsWith("delete ")) {
                    deleteTask(input);
                } else {
                    throw TaskException.forUnknownCommand();
                }
            } catch (TaskException e) {
                printErrorMessage(e.getMessage());
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

    private void mark(String input) throws TaskException {
        int taskNumber = Integer.parseInt(input.substring(5));
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.check();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            throw TaskException.forInvalidTaskNumber();
        }
    }

    private void unmark(String input) throws TaskException {
        int taskNumber = Integer.parseInt(input.substring(7).strip());
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.uncheck();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            throw TaskException.forInvalidTaskNumber();
        }
    }

    private void addTodoTask(String input) throws TaskException {
        if (input.strip().length() <= 5) {
            throw TaskException.forEmptyTaskDescription();
        }
        String description = input.substring(5).trim();
        tasks.add(new ToDo(description));
        printAddedTask();
    }

    private void addDeadlineTask(String input) throws TaskException {
        if (input.strip().length() <= 9) {
            throw TaskException.forEmptyTaskDescription();
        }
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw TaskException.forInvalidTaskFormat("deadline");
        }
        String description = input.substring(9, byIndex).trim();
        String by = input.substring(byIndex + 4).trim();
        tasks.add(new Deadline(description, by));
        printAddedTask();
    }

    private void addEventTask(String input) throws TaskException {
        if (input.strip().length() <= 6) {
            throw TaskException.forEmptyTaskDescription();
        }
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw TaskException.forInvalidTaskFormat("event");
        }
        String description = input.substring(6, fromIndex).trim();
        String start = input.substring(fromIndex + 6, toIndex).trim();
        String end = input.substring(toIndex + 4).trim();
        tasks.add(new Event(description, start, end));
        printAddedTask();
    }

    private void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private void deleteTask(String input) throws TaskException {
        try {
            int taskNumber = Integer.parseInt(input.substring(7).strip());
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new TaskException("Invalid task number. Please check and try again.");
            }
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task number format. Please enter a valid number.");
        }
    }

    private void printErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    public void farewell() {
        System.out.print("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Huyang huyang = new Huyang();
        huyang.greet();
        huyang.runChatbot();
        huyang.farewell();
    }
}
