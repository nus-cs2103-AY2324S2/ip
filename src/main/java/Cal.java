import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task; 

public class Cal {
    static String line = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void greet() {
        String name = "Cal";
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        //System.out.println(logo);
        System.out.println(line);
    }
    
    public static void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void echo(String input) { 
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public static void list() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String str = String.format("%d. [%s] %s", i + 1, t.getStatusIcon(), t.getDescription());
            System.out.println(str);
        }
        System.out.println(line);
    }

    public static String add(String input) {
        Task t = new Task(input);
        tasks.add(t);
        return String.format("added: %s", t.getDescription());
    }

    public static void mark(int taskNum) {
        Task t = tasks.get(taskNum - 1);
        System.out.println(line);
        t.setStatus();
        System.out.println("Nice! I've marked this task as done:");
        String str = String.format("[%s] %s", t.getStatusIcon(), t.getDescription());
        System.out.println(str);
        System.out.println(line);
    }

    public static void unmark(int taskNum) {
        Task t = tasks.get(taskNum - 1);
        t.setStatus();
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        String str = String.format("[%s] %s", t.getStatusIcon(), t.getDescription());
        System.out.println(str);
        System.out.println(line);
    }

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine().stripLeading();
            String[] tokens = input.split(" ");
            String command = tokens[0];

            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")){
                list();
            } else if (command.equalsIgnoreCase("mark")) {
                int taskNum = Integer.parseInt(tokens[1]);
                if (taskNum < 1 || taskNum > tasks.size()) {
                    System.out.println("Couldn't find task. Try again?");
                    continue;
                }
                mark(taskNum);
            } else if (command.equalsIgnoreCase("unmark")) {
                int taskNum = Integer.parseInt(tokens[1]);
                if (taskNum < 1 || taskNum > tasks.size()) {
                    System.out.println("Couldn't find task. Try again?");
                    continue;
                }
                unmark(taskNum);
            } else {
                input = add(input); 
                echo(input);
            }
        }
        sc.close();
        
        exit();
    }
}
