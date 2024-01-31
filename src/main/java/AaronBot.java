import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main AaronBot class, executes the user-bot interaction task adding/deleting/marking
 */
public class AaronBot {
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        boolean isBye = true;
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Hello, I am AaronBot, please talk to me I love my students very much :)");
        System.out.println("To add a task to the list, type 'add ' followed by your task.");
        System.out.println("To add a Todo: add todo [task name]");
        System.out.println("To add a Deadline: add deadline [task name] /by [deadline]");
        System.out.println("To add an Event: add event [task name] /from [start] /to [end]");
        System.out.println("To show the list, type 'show list'.");
        System.out.println("To mark a task done: mark [index of task]");
        System.out.println("To unmark a task done: unmark [index of task]");
        while (isBye) {
            try {
                isBye = chat(inputScanner.nextLine());
            } catch (NonsenseCommandException e) {
                System.out.println("What are you saying dear student?\n" + e.toString());
            }
        }
        inputScanner.close();
    }

    /**
     * Main function that accepts an input from the user and executes a command based on it.
     * @param userInput String input entered by user
     * @return boolean value representing whether the chat should still be continued
     * @throws NonsenseCommandException if command given by user is not recognized by Aaronbot
     */
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
                indexCheck(taskIndexMark);
            } catch (IndexErrorException e) {
                System.out.println("Student, your mark index should be a number between 1 and "
                        + taskList.size());
                System.out.println(e);
                return true;
            }
            if (markDone(Integer.parseInt(taskIndexMark))) {
                System.out.println("Task has been marked successfully student \n" 
                        + taskList.get(Integer.parseInt(taskIndexMark) - 1));
            } else {
                System.out.println("Task is already marked student! \n"
                        + taskList.get(Integer.parseInt(taskIndexMark) - 1));
            }
            return true;
        case "unmark":
            String taskIndexUnmark = tokenizedUserInput[1];
            try {
                indexCheck(taskIndexUnmark);
            } catch (IndexErrorException e) {
                System.out.println("Student, your unmark index should be a number between 1 and "
                        + taskList.size());
                System.out.println(e);
                return true;
            }
            if (unmarkDone(Integer.parseInt(taskIndexUnmark))) {
                System.out.println("Task has been unmarked successfully student \n" 
                        + taskList.get(Integer.parseInt(taskIndexUnmark) - 1));
            } else {
                System.out.println("Task is already unmarked student! \n"
                        + taskList.get(Integer.parseInt(taskIndexUnmark) - 1));
            }
            return true;
        case "show":
            if (tokenizedUserInput.length > 1 && tokenizedUserInput[1].equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
                }
                return true;
            } else {
                throw new NonsenseCommandException(userInput);
            }
        case "delete":
            String taskIndexDelete = tokenizedUserInput[1];
            try {
                indexCheck(taskIndexDelete);
            } catch (IndexErrorException e) {
                System.out.println("Student, your delete index should be a number between 1 and "
                        + taskList.size());
                System.out.println(e);
                return true;
        }
        deleteTask(Integer.parseInt(taskIndexDelete));
        return true;
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

    /**
     * function that adds a task to the tasklist
     * @param taskType String representing type of task being added
     * @param newTask Name/description of the task being added
     * @return boolean value representing whether task has already been added to the list before
     * @throws InvalidTaskTypeException if the task type is invalid
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

    /**
     * function that marks a task on the tasklist as done
     * @param listIndex index of the tasklist to be marked
     * @return boolean value indicating whether the task was newly marked
     */
    private static boolean markDone(Integer listIndex) {
        Task taskFromList = taskList.get(listIndex - 1);
        return taskFromList.markDone();
    }

    /**
     * function that unmarks a task on the tasklist as done
     * @param listIndex index of the tasklist to be marked
     * @return boolean value indicating whether the task was newly unmarked
     */
    private static boolean unmarkDone(Integer listIndex) {
        Task taskFromList = taskList.get(listIndex - 1);
        return taskFromList.unmarkDone();
    }

    /**
     * function that deletes a task on the tasklist
     * @param listIndex index of the tasklist to be deleted
     */
    private static void deleteTask(int listIndex) {
        Task deletedTask = taskList.get(listIndex - 1); 
        taskList.remove(listIndex - 1);
        System.out.println("Task deleted :\n" + deletedTask);
    }

    /**
     * function that checks whether an input by the user has a task after the task type keyword
     * @param userInputString String user input
     * @throws TaskNoNameException if user input does not have a task description/name after the task type keyword
     */
    private static void taskPresenceCheck(String userInputString) throws TaskNoNameException {
        if (userInputString.split("\\s+").length <= 1) {
            throw new TaskNoNameException(userInputString);
        }
    }

    /**
     * function that checks if the user input is >1 words (unless a 'bye' command is given)
     * @param userInputString User input
     * @throws InvalidCommandFormatException if user input is <=1 words unless the input is 'bye'
     */
    private static void commandLengthCheck(String userInputString) throws InvalidCommandFormatException {
        if (!(userInputString.equals("bye")) && userInputString.split("\\s+").length <= 1) {
            throw new InvalidCommandFormatException(userInputString);
        }
    }

    /**
     * funcion that checks that the index provided by the user for a mark/unmark/delete command is valid
     * @param userTaskIndex task index given by the user
     * @throws IndexErrorException
     */
    private static void indexCheck(String userTaskIndex) throws IndexErrorException{
        Integer index;
        try {
            index = Integer.parseInt(userTaskIndex);
        } catch (NumberFormatException e) {
            throw new MarkNonNumberException(userTaskIndex);
        }
        if (index > taskList.size()) {
            throw new TaskListOutOfBoundsException(index.toString());
        }
    }
}