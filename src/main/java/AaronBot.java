import java.util.Scanner;
import java.util.ArrayList;
public class AaronBot {
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        boolean notBye = true;
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Hello, I am AaronBot, please talk to me I love my students very much :)");
        System.out.println("To add a task to the list, type 'add ' followed by your task.");
        System.out.println("To add a Todo: add todo [task name]");
        System.out.println("To add a Deadline: add deadline [task name] /by [deadline]");
        System.out.println("To add an Event: add event [task name] /from [start] /to [end]");
        System.out.println("To show the list, type 'show list'.");
        System.out.println("To mark a task done: mark [index of task]");
        System.out.println("To unmark a task done: unmark [index of task]");
        while (notBye) {
            notBye = chat(inputScanner.nextLine());
        }
        inputScanner.close();
    }

    private static boolean chat(String userInput) {
        String[] tokenizedUserInput = userInput.split(" ", 2);    
        String userCommand = tokenizedUserInput[0];
        switch(userCommand) {
        case "add":
            String[] tokenizedTaskDetails = tokenizedUserInput[1].split(" ", 2);
            boolean isAdded = addToList(tokenizedTaskDetails[0], tokenizedTaskDetails[1]);
            if (isAdded) {
                System.out.println("This task is already in the list!");
                return true;
            } else {
                System.out.println("Task added :)");
                return true;
            }
        case "mark":
            String taskIndex = tokenizedUserInput[1];
            if (isInteger(taskIndex)) {
                return markDone(Integer.parseInt(taskIndex));
            } else {
                System.out.println("error, mark should be followed by the index of the task to be marked.");
                return true;
            }
        case "unmark":
            taskIndex = tokenizedUserInput[1];
            if (isInteger(taskIndex)) {
                return unmarkDone(Integer.parseInt(taskIndex));
            } else {
                System.out.println("error, mark should be followed by the index of the task to be marked.");
                return true;
            }
        case "show list":
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
            return true;
        default:
            return echo(userInput);
        }
    }

    private static boolean echo(String echoString) {
        if (echoString.equalsIgnoreCase("what is your favorite module")) {
            System.out.println("CS1231S :)");
            return true;
        } else if (echoString.equalsIgnoreCase("bye")) {
            System.out.println("Ok byebye, HAND.");
            return false;
        } else if (echoString.equalsIgnoreCase("show list")) {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
            return true;
        } else {
            System.out.println(echoString + " student.");
            return true;
        }
    }

    private static boolean addToList(String taskType, String newTask) {
        Task task;
        switch(taskType) {
        case ("todo"):
            task = new Todo(newTask);
            break;
        case ("deadline"):
            String[] tokenizedTask = newTask.split("/by", 2);
            task = new Deadline(tokenizedTask[0], tokenizedTask[1]);
            break;
        case ("event"):
            String[] taskTimingSplit = newTask.split("/from", 2);
            String[] startEndSplit = taskTimingSplit[1].split("/to", 2);
            task = new Event(taskTimingSplit[0], startEndSplit[0], startEndSplit[1]);
            break;
        default:
            System.out.println("hey, task type not specified");
            return true;
        }
        if (taskList.contains(task)) {
            return true;
        } else {
            taskList.add(task);
            return false;
        }
    } 
    private static boolean markDone(Integer listIndex) {
        Task taskFromList = taskList.get(listIndex - 1);
        taskFromList.markDone();
        return true;
    }

    private static boolean unmarkDone(Integer listIndex) {
        Task taskFromList = taskList.get(listIndex - 1);
        taskFromList.unmarkDone();
        return true;
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
