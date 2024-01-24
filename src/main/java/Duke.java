import task.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        greet();
        handleInput();
        exit();
    }

    public static void greet() {
        String logo = "   _____ __  ______  ____  ___    _   __\n"
                    + "  / ___// / / / __ \\/ __ \\/   |  / | / /\n"
                    + "  \\__ \\/ /_/ / / / / / / / /| | /  |/ / \n"
                    + " ___/ / __  / /_/ / /_/ / ___ |/ /|  /  \n"
                    + "/____/_/ /_/\\____/_____/_/  |_/_/ |_/   \n";
        System.out.println(logo);
        System.out.println("Greetings, human.");
        System.out.println("What can I do for you?");
    }
    public static void handleInput() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                System.out.print("> ");
                String line = sc.nextLine().stripLeading();
                if (line.isEmpty()) continue;
                String[] tokens = line.split(" ");
                String input = tokens[0];
                if (input.equalsIgnoreCase("bye")) {
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.print(i + 1 + ". ");
                        System.out.println(taskList.get(i));
                    }
                } else if (input.equalsIgnoreCase("mark")) {
                    int taskNum = Integer.parseInt(tokens[1]);
                    if (taskNum < 1 || taskNum > taskList.size()) {
                        System.out.println("Couldn't find task. Try again?");
                        continue;
                    }
                    Task selectedTask = taskList.get(taskNum - 1);
                    selectedTask.done();
                    System.out.println("Task set to done: " + selectedTask);
                } else if (input.equalsIgnoreCase("unmark")) {
                    int taskNum = Integer.parseInt(tokens[1]);
                    if (taskNum < 1 || taskNum > taskList.size()) {
                        System.out.println("Couldn't find task. Try again?");
                        continue;
                    }
                    Task selectedTask = taskList.get(taskNum - 1);
                    selectedTask.undone();
                    System.out.println("Task has been set as not done yet: " + selectedTask);
                } else {
                    taskList.add(new Task(line));
                    System.out.println("added: " + line);
                }
            } catch (Exception e) {
                System.out.println("Something went wrong. Did you type your command correctly?");
            }
        }
        sc.close();
    }
    public static void exit() {
        System.out.println("Goodbye.");
    }
}
