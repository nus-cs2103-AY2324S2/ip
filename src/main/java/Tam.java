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
        String[] splitInput = input.split(" ");
        String command = splitInput[0].toLowerCase();
        System.out.print(dividerText);
        switch (command) {
            case "bye":
            case "exit":
                return 0;

            case "list":
                Tam.listTasks();
                return 1;

            case "mark":
            case "unmark":
                int taskNum = Integer.parseInt(splitInput[1]);
                if (command.equals("mark")) {
                    Tam.markTaskDone(taskNum);
                }
                else {
                    Tam.markTaskUndone(taskNum);
                }
                return 1;

            case "todo":
                String todoDescription = input.replace("todo ", "");
                Tam.addTodo(todoDescription);
                return 1;

            case "deadline":
                String[] deadlineDescriptionDueDate = input.replace("deadline ", "").split(" /by ");
                String deadlineDescription = deadlineDescriptionDueDate[0];
                String deadlineDueDate = deadlineDescriptionDueDate[1];
                Tam.addDeadline(deadlineDescription, deadlineDueDate);
                return 1;

            case "event":
                String[] eventDescriptionFromTo = input.replace("event ", "").split(" /from ");
                String eventDescription = eventDescriptionFromTo[0];
                String[] eventFromTo = eventDescriptionFromTo[1].split(" /to ");
                String eventFrom = eventFromTo[0];
                String eventTo = eventFromTo[1];
                Tam.addEvent(eventDescription, eventFrom, eventTo);
                return 1;

            default:
                return 1;
        }
    }

    public static void addTodo(String description) {
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        numTasks++;
        System.out.print("New todo added:\n" + newTodo.getTaskDetails()+ "\n");
        System.out.print("Tasks in list: " + numTasks + "\n");
        System.out.print(dividerText);
    }

    public static void addDeadline(String description, String dueDate) {
        Deadline newDeadline = new Deadline(description, dueDate);
        taskList.add(newDeadline);
        numTasks++;
        System.out.print("New deadline added:\n" + newDeadline.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + numTasks + "\n");
        System.out.print(dividerText);
    }

    public static void addEvent(String description, String fromDate, String toDate) {
        Event newEvent = new Event(description, fromDate, toDate);
        taskList.add(newEvent);
        numTasks++;
        System.out.print("New event added:\n" + newEvent.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + numTasks + "\n");
        System.out.print(dividerText);
    }

    public static void listTasks() {
        for (int i = 0; i < numTasks; i++) {
            Task thisTask = taskList.get(i);
            System.out.print((i+1) + ". " + thisTask.getTaskDetails() + "\n");
        }
        System.out.print(dividerText);
    }

    public static void markTaskDone(int taskNum) {
        Task thisTask = taskList.get(taskNum-1);
        thisTask.markDone();
        String markedDoneText = "Nice! I've marked this task as done:\n";
        String taskText = "    " + thisTask.getTaskDetails() + "\n";
        System.out.print(markedDoneText);
        System.out.print(taskText);
        System.out.print(dividerText);
    }

    public static void markTaskUndone(int taskNum) {
        Task thisTask = taskList.get(taskNum-1);
        thisTask.markUndone();
        String markedUndoneText = "Ok, i've marked this task as not done yet:\n";
        String taskText = "    " + thisTask.getTaskDetails() + "\n";
        System.out.print(markedUndoneText);
        System.out.print(taskText);
        System.out.print(dividerText);
    }
}
