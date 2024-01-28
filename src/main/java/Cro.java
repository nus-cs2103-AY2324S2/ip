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

    public static void addToDo(List<String> splitStr) throws CroException {
        String description = String.join(" ", splitStr.subList(1, splitStr.size()));
        if (description.equals("")) {
            throw new CroException("description of todo cannot be empty!");
        }
        ToDo newToDo = new ToDo(description);
        addToTaskList(newToDo);
    }

    public static void addDeadline(List<String> splitStr) throws CroException {
        int byIndex = splitStr.indexOf("/by");
        if (byIndex < 0) {
            throw new CroException("deadline not found, please include with '/by' as an indicator.");
        } else {
            String description = String.join(" ", splitStr.subList(1, byIndex));
            String deadline = String.join(" ", splitStr.subList(byIndex + 1, splitStr.size()));
            if (description.equals("") || deadline.equals("")) {
                throw new CroException("description or deadline cannot be empty!");
            }
            Deadline newDeadline = new Deadline(description, deadline);
            addToTaskList(newDeadline);
        }
    }

    public static void addEvent(List<String> splitStr) throws CroException {
        int fromIndex = splitStr.indexOf("/from");
        int toIndex = splitStr.indexOf("/to");
        if (fromIndex < 0 || toIndex < 0) {
            throw new CroException("event timings not found, please use /from and /to to indicate.");
        } else {
            String description = String.join(" ", splitStr.subList(1, fromIndex));
            String start = String.join(" ", splitStr.subList(fromIndex + 1, toIndex));
            String end = String.join(" ", splitStr.subList(toIndex + 1, splitStr.size()));
            if (description.equals("") || start.equals("") || end.equals("")) {
                throw new CroException("description, start or end cannot be empty!");
            }
            Event newEvent = new Event(description, start, end);
            addToTaskList(newEvent);
        }
    }
    public static void displayTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.format("%d. %s", i+1, taskList.get(i));
            System.out.println(output);
        }
        System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
    }

    public static void markTaskAsDone(int taskNo) throws CroException {
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo-1).markAsDone();
            System.out.println("-----------------------------------");
            System.out.println("Nice! I've marked this task as done!");
            System.out.println(taskList.get(taskNo-1));
            System.out.println("-----------------------------------");
        }
    }

    public static void markTaskAsUndone(int taskNo) throws CroException {
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo-1).markAsUndone();
            System.out.println("-----------------------------------");
            System.out.println("OK, I've marked this task as not done yet.");
            System.out.println(taskList.get(taskNo-1));
            System.out.println("-----------------------------------");
        }
    }
    public static void main(String[] args){
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String inText = sc.nextLine();
            List<String> splitStr = Arrays.asList(inText.trim().split("\\s+"));
            String command = splitStr.get(0);
            try {
                if (command.equals("bye")) {
                    System.out.println("-----------------------------------");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("-----------------------------------");
                    break;
                } else if (command.equals("list")) {
                    displayTasks();
                } else if (command.equals("mark")) {
                    markTaskAsDone(Integer.parseInt(splitStr.get(1)));
                } else if (command.equals("unmark")) {
                    markTaskAsUndone(Integer.parseInt(splitStr.get(1)));
                } else if (command.equals("todo")) {
                    addToDo(splitStr);
                } else if (command.equals("deadline")) {
                    addDeadline(splitStr);
                } else if (command.equals("event")) {
                    addEvent(splitStr);
                } else {
                    throw new CroException("Unknown command. Please try again.");
                }
            } catch (CroException e){
                System.out.println(e.getMessage());
            }
        }

    }
}
