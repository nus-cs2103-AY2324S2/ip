import java.util.ArrayList;
import java.util.Scanner;
public class Tam {
    static String dividerText = "____________________________________________________________\n";
    static Scanner scannerObj = new Scanner(System.in);
    //static String[] taskList = new String[100];

    static ArrayList<Task>  taskList = new ArrayList<>();
    static int numTasks = 0;
    public static void main(String[] args) {
        Tam.greet();
        int status = readCommand();
        while (status == 1) {
            status = readCommand();
        }
        Tam.exit();
    }

    public static void greet() {
        String greetText = "Hello! I'm Tam the Task Manager!\nWhat can I do for you?\n";
        System.out.print(dividerText);
        System.out.print(greetText);
        System.out.print(dividerText);
    }

    public static void exit() {
        String exitText = "Bye. Hope to see you again soon!\n";
        System.out.print(exitText);
        System.out.print(dividerText);
    }

    public static int readCommand() {
        String input = scannerObj.nextLine();
        System.out.print(dividerText);
        // exit command
        if (input.toLowerCase().equals("bye") || input.toLowerCase().equals("exit")) {
            return 0;
        }
        // list tasks command
        else if (input.toLowerCase().equals("list")) {
            Tam.listTasks();
            return 1;
        }
        // mark or unmark a task
        else if (input.split(" ")[0].toLowerCase().equals("mark") ||
                input.split(" ")[0].toLowerCase().equals("unmark")) {
            String command = input.split(" ")[0].toLowerCase();
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            switch (command) {
                case "mark":
                    Tam.markTaskDone(taskNum);
                    break;
                case "unmark":
                    Tam.markTaskUndone(taskNum);
            }
            return 1;
        }
        // add task command
        else {
            Tam.addTask(input);
            return 1;
        }
    }

    public static void addTask(String taskDescription) {
        System.out.println("Added: " + taskDescription);
        taskList.add(new Task(taskDescription));
        numTasks++;
        System.out.print(dividerText);
    }

    public static void listTasks() {
        for (int i = 0; i < numTasks; i++) {
            Task thisTask = taskList.get(i);
            System.out.println((i+1) + ".[" + thisTask.getStatusIcon() + "] " + thisTask.getDescription());
        }
        System.out.print(dividerText);
    }

    public static void markTaskDone(int taskNum) {
        Task thisTask = taskList.get(taskNum-1);
        thisTask.markDone();
        String markedDoneText = "Nice! I've marked this task as done:\n";
        String taskText = "    [" + thisTask.getStatusIcon() + "] " + thisTask.getDescription() + "\n";
        System.out.print(markedDoneText);
        System.out.print(taskText);
        System.out.print(dividerText);
    }

    public static void markTaskUndone(int taskNum) {
        Task thisTask = taskList.get(taskNum-1);
        thisTask.markUndone();
        String markedUndoneText = "Ok, i've marked this task as not done yet:\n";
        String taskText = "    [" + thisTask.getStatusIcon() + "] " + thisTask.getDescription() + "\n";
        System.out.print(markedUndoneText);
        System.out.print(taskText);
        System.out.print(dividerText);
    }
}
