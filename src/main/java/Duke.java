import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private final String name = "Duke";
    private final String exitMessage = "bye";
    private final FileHandler fileHandler = new FileHandler();
    private List<Task> tasks;

    public Duke() {
        tasks = fileHandler.readFromFile();
    }

    public void welcomeMessage() {
        divider();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        divider();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        divider();
    }

    public boolean isBye(String str) {
        return str.equalsIgnoreCase(exitMessage);
    }

    public boolean checkList(String str) {
        return str.equalsIgnoreCase("list");
    }

    public boolean checkMark(String str) {
        return str.startsWith("mark");
    }

    public boolean checkUnMark(String str) {
        return str.startsWith("unmark");
    }

    public boolean checkTodo(String str) {
        return str.startsWith("todo");
    }

    public boolean checkDeadline(String str) {
        return str.startsWith("deadline");
    }

    public boolean checkEvent(String str) {
        return str.startsWith("event");
    }

    public boolean checkDelete(String str) {
        return str.startsWith("delete");
    }

    public void typeMessage() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!isBye(input)) {
            divider();
            try {
                if (checkList(input)) {
                    listTasks();
                } else if (checkMark(input)) {
                    markTask(input);
                } else if (checkUnMark(input)) {
                    unmarkTask(input);
                } else if (checkTodo(input)) {
                    addTodo(input);
                } else if (checkDeadline(input)) {
                    addDeadline(input);
                } else if (checkEvent(input)) {
                    addEvent(input);
                } else if (checkDelete(input)) {
                    deleteTask(input);
                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            divider();
            input = sc.nextLine();
        }

        exit();
        sc.close();
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markTask(String input) {
        int index = Integer.parseInt(input.substring(5).trim()) - 1;
        tasks.get(index).markAsDone();
        try {
            fileHandler.saveInFile(tasks);
        } catch (FileIOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public void unmarkTask(String input) {
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        tasks.get(index).unmarkAsDone();
        try {
            fileHandler.saveInFile(tasks);
        } catch (FileIOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public void addTodo(String input) {
        String description = input.substring(5).trim();
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        try {
            fileHandler.saveInFile(tasks);
        } catch (FileIOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public void addDeadline(String input) {
        String[] parts = input.substring(9).trim().split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        try {
            fileHandler.saveInFile(tasks);
        } catch (FileIOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public void addEvent(String input) {
        String[] parts = input.substring(6).trim().split("/at", 2);
        String description = parts[0].trim();
        String[] times = parts[1].trim().split(" to ", 2);
        String start = times[0].trim();
        String end = times[1].trim();
        Event newEvent = new Event(description, start, end);
        tasks.add(newEvent);
        try {
            fileHandler.saveInFile(tasks);
        } catch (FileIOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public void deleteTask(String input) {
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        tasks.remove(index);
        try {
            fileHandler.saveInFile(tasks);
        } catch (FileIOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public void divider() {
        System.out.println("-----------------------------------------------------");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.welcomeMessage();
        duke.typeMessage();
    }
}

// Task, Todo, Deadline, Event are assumed to be separate classes.
// FileIOException is assumed to be a custom exception class.
