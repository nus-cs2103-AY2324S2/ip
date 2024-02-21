import java.util.List;
import java.util.Scanner;

import storage.StorageManager;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo; 

public class Cal {
    static String line = "____________________________________________________________";
    private static List<Task> tasks;
    private static StorageManager storageManager;

    public static void init() {
        storageManager = new StorageManager();
        tasks = storageManager.loadTasksFromStorage();
    }

    public static void greet() {
        String name = "Cal";
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        //System.out.println(logo);
    }
    
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String str = String.format("%d. %s", i + 1, t);
            System.out.println(str);
        }
    }

    public static void add(String description) {
        Task t = new Todo(description);
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        storageManager.saveTasksToStorage(tasks);
    }

    public static void add(String description, String by) {
        Task t = new Deadline(description, by);
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        storageManager.saveTasksToStorage(tasks);
    }

    public static void add(String description, String startDate, String endDate) {
        Task t = new Event(description, startDate, endDate);
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        storageManager.saveTasksToStorage(tasks);
    }

    public static void mark(int taskNum) throws CalException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new CalException("Invalid task number!");
        }
        Task t = tasks.get(taskNum - 1);
        t.setStatus(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        storageManager.saveTasksToStorage(tasks);
    }

    public static void unmark(int taskNum) throws CalException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new CalException("Invalid task number!");
        }
        Task t = tasks.get(taskNum - 1);
        t.setStatus(false);  
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        storageManager.saveTasksToStorage(tasks);
    }

    public static void delete(int taskNum) {
        Task t = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        System.out.println("Noted. I've removed this task");
        System.out.println(t);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        storageManager.saveTasksToStorage(tasks);
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                String input = sc.nextLine().toLowerCase();
                String[] tokens = input.split(" ");
                String command = tokens[0];
                
                String description = "";

                System.out.println(line);
                switch(command) {
                    case "bye":
                        sc.close();
                        return;
                    case "list":
                        list();
                        break;
                    case "mark":
                        if (tokens.length < 2) {
                            throw new CalException("Task number not provided!");
                        }

                        mark(Integer.parseInt(tokens[1]));
                        break;
                    case "unmark":
                        if (tokens.length < 2) {
                            throw new CalException("Task number not provided!");
                        }
                        
                        unmark(Integer.parseInt(tokens[1]));
                        break;
                    case "todo":
                        try {
                            description = input.substring(5);
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new CalException("Task description not provided!");
                        }

                        add(description);
                        break;
                    case "deadline":
                        int byIndex = input.indexOf("/by");
                        String by;

                        try {
                            description = input.substring(9, byIndex).strip();
                            by = input.substring(byIndex + 4).strip();
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new CalException("Deadline Task is not in the format: " +
                                "deadline (description) /by (due date)!");
                        }
                        
                        add(description, by);
                        break;
                    case "event":
                        int fromIndex = input.indexOf("/from");
                        int toIndex = input.indexOf("/to");
                        String startDate;
                        String endDate;

                        try {
                            description = input.substring(6, fromIndex).strip();
                            startDate = input.substring(fromIndex + 5, toIndex).strip();
                            endDate = input.substring(toIndex + 3).strip();
                        } catch (StringIndexOutOfBoundsException e){
                            throw new CalException("Event Task is not in the format: " + 
                                "event (description) /from (startDate) /to (endDate)!");
                        }

                        add(description, startDate, endDate);
                        break;
                    case "delete":
                        if (tokens.length < 2) {
                            throw new CalException("Task number not provided!");
                        }
                        delete(Integer.parseInt(tokens[1]));
                        break;
                    default:
                        throw new CalException("Command not recognized.");
                }
                System.out.println(line);
            } catch(Exception e) {
                System.err.println(e);
                break;
            }
        }
    }

    public static void main(String[] args) {
        init();
        greet();
        run();
        exit();
    }
}
