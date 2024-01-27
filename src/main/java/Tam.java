import java.util.ArrayList;
import java.util.Scanner;
public class Tam {
    static String dividerText = "____________________________________________________________\n";
    static Scanner scannerObj = new Scanner(System.in);

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
                String todoDescription = input.replace("todo", "").trim();
                //System.out.println(todoDescription);
                if (todoDescription.equals("")) { // missing description
                    System.out.print("Missing task description!\n");
                    System.out.print(dividerText);
                }
                else if (todoDescription.contains("/by")) { // unnecessary due date
                    System.out.print("Todo task cannot have a due date!\n");
                    System.out.print(dividerText);
                }
                else if (todoDescription.contains("/from") ||
                        todoDescription.contains("/to")) { // unnecessary from and to date
                    System.out.print("Todo task cannot have a from and to date!\n");
                    System.out.print(dividerText);
                }
                else {
                    Tam.addTodo(todoDescription);
                }
                return 1;

            case "deadline":
                String deadlineDescriptionDueDate = input.replace("deadline", "").trim();
                if (!deadlineDescriptionDueDate.contains(" /by ")) { // missing '/by' keyword
                    System.out.print("Missing due date!\n");
                    System.out.print(dividerText);
                    return 1;
                }
                String[] deadlineDescriptionDueDateArray = input.replace("deadline", "")
                        .trim().split(" /by ");
                String deadlineDescription = deadlineDescriptionDueDateArray[0];
                String deadlineDueDate = deadlineDescriptionDueDateArray[1];
                if (deadlineDescription.equals("")) { // empty description
                    System.out.print("Missing task description!\n");
                    System.out.print(dividerText);
                }
                else if (deadlineDueDate.equals("")) { // empty due date
                    System.out.print("Missing task due date!\n");
                    System.out.print(dividerText);
                }
                else if (deadlineDescription.contains("/from") ||
                        deadlineDescription.contains("/to")) { // unnecessary from and to date
                    System.out.print("Deadline task cannot have a from and to date!\n");
                    System.out.print(dividerText);
                }
                else {
                    Tam.addDeadline(deadlineDescription, deadlineDueDate);
                }
                return 1;

            case "event":
                String eventDescriptionFromTo = input.replace("event", "").trim();
                if (!eventDescriptionFromTo.contains(" /from ") ||
                        !eventDescriptionFromTo.contains(" /to ")) { // missing '/from' or '/to' keywords
                    System.out.print("Missing event from and/or to date!\n");
                    System.out.print(dividerText);
                    return 1;
                }
                String[] eventDescriptionFromToArray = input.replace("event", "")
                        .trim().split(" /from ");
                String eventDescription = eventDescriptionFromToArray[0];
                String eventFromTo = eventDescriptionFromToArray[1];
                String[] eventFromToArray;
                String eventFrom = "";
                String eventTo = "";
                if (eventFromTo.contains(" /to ")) {
                    eventFromToArray = eventDescriptionFromToArray[1].trim().split(" /to ");
                    eventFrom = eventFromToArray[0];
                    eventTo = eventFromToArray[1];
                }
                if (eventDescription.equals("")) { // missing description
                    System.out.print("Missing task description!\n");
                    System.out.print(dividerText);
                }
                else if (eventFrom.equals("")) { // missing from date
                    System.out.print("Missing from date!\n");
                    System.out.print(dividerText);
                }
                else if (eventTo.equals("")) { // missing to date
                    System.out.print("Missing to date!\n");
                    System.out.print(dividerText);
                }
                else if (eventDescription.contains("/by")) { // unnecessary due date
                    System.out.print("Event task cannot have a due date!\n");
                    System.out.print(dividerText);
                }
                else {
                    Tam.addEvent(eventDescription, eventFrom, eventTo);
                }
                return 1;

            case "help":
                System.out.print("LIST OF COMMANDS\n");
                System.out.print("Create todo: todo [task name] (eg: todo borrow book)\n");
                System.out.print("Create deadline: deadline [task name] /by [due date] " +
                        "(eg: deadline homework /by Sun 6pm)\n");
                System.out.print("Create event: event [task name] /from [from date] /to [to date] " +
                        "(eg: event concert /from Mon 6pm /to Mon 8pm)\n");
                System.out.print("List current tasks: list\n");
                System.out.print("Mark a task as complete: mark [task number on list] " +
                        "(eg: mark 2)\n");
                System.out.print("Mark a task as incomplete: unmark [task number on list] " +
                        "(eg: unmark 2)\n");
                System.out.print("Close Tam the Task Manager: bye\n");
                System.out.print(dividerText);
                return 1;

            default:
                System.out.print("Invalid command entered. Type 'help' for list of commands\n");
                System.out.print(dividerText);
                return 1;
        }
    }

    public static void addTodo(String description) {
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        numTasks++;
        System.out.print("New todo added:\n");
        System.out.print("   " + newTodo.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + numTasks + "\n");
        System.out.print(dividerText);
    }

    public static void addDeadline(String description, String dueDate) {
        Deadline newDeadline = new Deadline(description, dueDate);
        taskList.add(newDeadline);
        numTasks++;
        System.out.print("New deadline added:\n");
        System.out.print("   " + newDeadline.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + numTasks + "\n");
        System.out.print(dividerText);
    }

    public static void addEvent(String description, String fromDate, String toDate) {
        Event newEvent = new Event(description, fromDate, toDate);
        taskList.add(newEvent);
        numTasks++;
        System.out.print("New event added:\n");
        System.out.print("   " + newEvent.getTaskDetails() + "\n");
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
