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
            try {
                notBye = chat(inputScanner.nextLine());
            } catch (NonsenseCommandException e) {
                System.out.println("What are you saying dear student?\n" + e.toString());
            }
        }
        inputScanner.close();
    }

    private static boolean chat(String userInput) throws NonsenseCommandException{
        String[] tokenizedUserInput = userInput.split(" ", 2);    
        String userCommand = tokenizedUserInput[0];

        try {
            commandLengthCheck(userInput);
        } catch (InvalidCommandFormatException e) {
            System.out.println("Dear student, you need to type a command followed by another word.");
            return true;
        }

        switch(userCommand) {
        case "add":
            try {
                taskPresenceCheck(tokenizedUserInput[1]);
            } catch (TaskNoNameException e) {
                System.out.println("Sorry, the description of a task cannot be empty");
                return true;
            }
            boolean isAdded;
            String[] tokenizedTaskDetails = tokenizedUserInput[1].split(" ", 2);
            try {
                isAdded = addToList(tokenizedTaskDetails[0], tokenizedTaskDetails[1]);
            } catch (InvalidTaskTypeException e) {
                System.out.println("Hey!! I don't know that task type: " + e.toString());
                return true;
            }
            if (isAdded) {
                System.out.println("This task is already in the list!");
                return true;
            } else {
                System.out.println("Task added :)");
                System.out.println("You now have " + taskList.size() + " tasks.");
                return true;
            }
        case "mark":
            String taskIndexMark = tokenizedUserInput[1];
            try {
                markCheck(taskIndexMark);
            } catch (MarkingException e) {
                System.out.println("Student, your mark index should be a number between 1 and "
                        + taskList.size());
                System.out.println(e);
                return true;
            }
            return markDone(Integer.parseInt(taskIndexMark));
        case "unmark":
            String taskIndexUnmark = tokenizedUserInput[1];
            try {
             markCheck(taskIndexUnmark);
            } catch (MarkingException e) {
                System.out.println("Student, your mark index should be a number between 1 and "
                        + taskList.size());
                System.out.println(e);
                return true;
            }
            return unmarkDone(Integer.parseInt(taskIndexUnmark));
        case "show":
            if (tokenizedUserInput.length > 1 && tokenizedUserInput[1].equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
                }
                return true;
            } else {
                throw new NonsenseCommandException(userInput);
            }
        case "bye":
            System.out.println("Ok Student, HAND.");
            return false;
        default:
            throw new NonsenseCommandException(userInput);
        }
    }
    /*
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
    */

    private static boolean addToList(String taskType, String newTask) throws InvalidTaskTypeException {
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
            throw new InvalidTaskTypeException(taskType);
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

    private static void taskPresenceCheck(String userInputString) throws TaskNoNameException {
        if (userInputString.split("\\s+").length <= 1) {
            throw new TaskNoNameException(userInputString);
        }
    }

    private static void commandLengthCheck(String userInputString) throws InvalidCommandFormatException {
        if (!(userInputString.equals("bye")) && userInputString.split("\\s+").length <= 1) {
            throw new InvalidCommandFormatException(userInputString);
        }
    }

    private static void markCheck(String userString) throws MarkingException{
        Integer index;
        try {
            index = Integer.parseInt(userString);
        } catch (NumberFormatException e) {
            throw new MarkNonNumberException(userString);
        }

        if (index > taskList.size()) {
            throw new TaskListOutOfBoundsException(index.toString());
        }
    }
}