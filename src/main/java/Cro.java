import java.util.*;
public class Cro {
    static String welcomeMessage = "-----------------------------------\n"
                            + "Hello! I'm Cro!\n"
                            + "What can I do for you?\n"
                            + "-----------------------------------\n";
    static List<Task> taskList = new ArrayList<>();

    public static void addToTaskList(Task newTask) {
        taskList.add(newTask);
        System.out.println("-----------------------------------");
        System.out.println("added: " + newTask);
        System.out.println("-----------------------------------");
    }

    public static void addToDo(List<String> splitStr) {
        String description = String.join(" ", splitStr.subList(1, splitStr.size()));
        ToDo newToDo = new ToDo(description);
        addToTaskList(newToDo);
    }

    public static void addDeadline(List<String> splitStr) {
        int byIndex = splitStr.indexOf("/by");
        if (byIndex < 0) {
            System.out.println("-----------------------------------");
            System.out.println("deadline not found, please include with '/by' as an indicator.");
            System.out.println("-----------------------------------");
        } else {
            String description = String.join(" ", splitStr.subList(1, byIndex));
            String deadline = String.join(" ", splitStr.subList(byIndex + 1, splitStr.size()));
            Deadline newDeadline = new Deadline(description, deadline);
            addToTaskList(newDeadline);
        }
    }

    public static void addEvent(List<String> splitStr) {
        int fromIndex = splitStr.indexOf("/from");
        int toIndex = splitStr.indexOf("/to");
        if (fromIndex < 0 || toIndex < 0) {
            System.out.println("-----------------------------------");
            System.out.println("event timings not found, please use /from and /to to indicate.");
            System.out.println("-----------------------------------");
        } else {
            String description = String.join(" ", splitStr.subList(1, fromIndex));
            String start = String.join(" ", splitStr.subList(fromIndex + 1, toIndex));
            String end = String.join(" ", splitStr.subList(toIndex + 1, splitStr.size()));
            Event newEvent = new Event(description, start, end);
            addToTaskList(newEvent);
        }
    }
    public static void displayTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.format("%d. %s", i+1, taskList.get(i));
            System.out.println(output);
        }
    }

    public static void markTaskAsDone(int taskNo) {
        if (taskNo > taskList.size()) {
            System.out.println("-----------------------------------");
            System.out.println("Task not found!");
            System.out.println("-----------------------------------");
        } else {
            taskList.get(taskNo-1).markAsDone();
            System.out.println("-----------------------------------");
            System.out.println("Nice! I've marked this task as done!");
            System.out.println(taskList.get(taskNo-1));
            System.out.println("-----------------------------------");
        }
    }

    public static void markTaskAsUndone(int taskNo) {
        if (taskNo > taskList.size()) {
            System.out.println("-----------------------------------");
            System.out.println("Task not found!");
            System.out.println("-----------------------------------");
        } else {
            taskList.get(taskNo-1).markAsUndone();
            System.out.println("-----------------------------------");
            System.out.println("OK, I've marked this task as not done yet.");
            System.out.println(taskList.get(taskNo-1));
            System.out.println("-----------------------------------");
        }
    }
    public static void main(String[] args) {
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String inText = sc.nextLine();
            List<String> splitStr = Arrays.asList(inText.trim().split("\\s+"));
            if (splitStr.get(0).equals("bye")) {
                System.out.println("-----------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------");
                break;
            } else if (splitStr.get(0).equals("list")) {
                displayTasks();
            } else if (splitStr.get(0).equals("mark")) {
                markTaskAsDone(Integer.parseInt(splitStr.get(1)));
            } else if (splitStr.get(0).equals("unmark")) {
                markTaskAsUndone(Integer.parseInt(splitStr.get(1)));
            } else if (splitStr.get(0).equals("todo")) {
                addToDo(splitStr);
            } else if (splitStr.get(0).equals("deadline")) {
                addDeadline(splitStr);
            } else if (splitStr.get(0).equals("event")) {
                addEvent(splitStr);
            }
        }

    }
}
