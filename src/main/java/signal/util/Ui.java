package signal.util;


//import signal.DukeException;
import signal.task.*;
import signal.util.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles user interactions with the Signal chatbot.
 * Carries out instructions to create, delete, list and edit tasks.
 *
 */
public class Ui {
    private final String LOGO = " _______  __                       __ \n"
            + "|     __||__|.-----..-----..---.-.|  |\n"
            + "|__     ||  ||  _  ||     ||  _  ||  |\n"
            + "|_______||__||___  ||__|__||___._||__|\n"
            + "             |_____|                  \n";
    private final String DIV = "\n" + "~~**~~";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> taskList;
    private Storage storeFiles;

    public Ui(ArrayList<Task> taskList, Storage storage) {
        this.taskList = taskList;
        this.storeFiles = storage;

    }

    /**
     * Gets the next user input.
     *
     * @return The string input.
     */
    public String scan() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the opening message of the Signal chatbot.
     */
    public String intro() {
//        System.out.println("Hello! My name is\n" + LOGO);
//        System.out.println("How can I help?");
//        System.out.println("Enter 'help' to see the list of commands available :D");
//        System.out.println(DIV);

        ArrayList<String> response = new ArrayList<>();
        response.add("Hello! My name is\n" + LOGO);
        response.add("How can I help?");
        response.add(("Enter 'help' to see the list of commands available :D"));
        return listToString(response);

    }

    /**
     * Prints the closing message of the Signal chatbot.
     */
    public void leave() {
        signalSays("Bye! Hope you come back soon :D");
    }

    /**
     * Prints a reponse to an empty input.
     */
    public void emptyInput() {
        signalSays("Brevity is the soul of wit, but you have to tell me something still!");
    }

    /**
     * Checks with the user if the input is a typo of a command.
     *
     * @param input Input collected from the user.
     * @param command Command to check.
     * @return True if input is a typo of the command.
     */
    public boolean checkCommandTypo(String input, String command) {
        if(!input.equals(command)) {
            signalSays("Did you mean '"+ command + "'? (y/n)");
            String isCommandCheck = scanner.nextLine();
            if(isCommandCheck.equals("y")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Check if the input is a permutation of the original.
     *
     * @param input Input collected from the user.
     * @param original String to compare the input to.
     * @return True if input is a permutation of original.
     */
    public boolean isPermutationMatch(String input, String original) {
        // Check if user input is a permutation match
        char[] userInputArray = input.toCharArray();
        char[] originalArray = original.toCharArray();

        // Sort the arrays to compare
        java.util.Arrays.sort(userInputArray);
        java.util.Arrays.sort(originalArray);

        return java.util.Arrays.equals(userInputArray, originalArray);
    }


    /**
     * Finds the first string from a list of strings.
     *
     * @param checker The string to find.
     * @param list The list of strings to search in.
     * @return The index of the string to be found, otherwise returns -1.
     */
    public int finder(String checker, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(checker)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Converts an ArrayList to a String.
     *
     * @param list An ArrayList of strings to convert.
     * @return The converted String.
     */
    public String listToString(ArrayList<String> list) {
        String result = "";
        for (String s : list) {
            result += s + "\n";
        }
        return result;
    }

    /**
     * Save the new task added to the file and print a response to the user.
     *
     * @param task The task added.
     */
    public void taskAdded(Task task) {
        int size = taskList.size();
        signalSays("Got it! I've added this task to your list: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.");
        storeFiles.writeTasks(taskList);
    }

    /**
     * Create a new Task of type ToDo.
     *
     * @param inputParts The string array of the user input.
     */
    public void commandToDo(String[] inputParts) {
        if (inputParts.length < 2) {
            signalSays("Looks like you haven't entered a task description!");
//            throw new DukeException("Looks like you haven't entered a task description!");
        } else {
            String description = String.join(" ", Arrays.copyOfRange(inputParts, 1, inputParts.length));
            Task task = new ToDo(description);
            taskList.add(task);
            taskAdded(task);
        }

    }

    /**
     * Create a new Task of type Deadline.
     *
     * @param inputParts The string array of the user input.
     */
    public void commandDeadline(String[] inputParts) {
        if (inputParts.length < 2) {
            signalSays("Looks like you haven't entered a task description!");
//            throw new DukeException("Looks like you haven't entered a task description!");
        } else if (!Arrays.asList(inputParts).contains("/by")) {
            signalSays("Looks like you haven't added a deadline!");
//            throw new DukeException("Looks like you haven't added a deadline!");
        }
        int byIndex = finder("/by", inputParts);
        String description = String.join(" ", Arrays.copyOfRange(inputParts, 1, byIndex));
        String by = String.join(" ", Arrays.copyOfRange(inputParts, byIndex + 1, inputParts.length));
        Task task = new Deadline(description, by);
        taskList.add(task);
        taskAdded(task);
    }

    /**
     * Create a new Task of type Event.
     *
     * @param inputParts The string array of the user input.
     */
    public void commandEvent(String[] inputParts) {
        if (inputParts.length < 2) {
            signalSays("Looks like you haven't entered a task description!");
//            throw new DukeException("Looks like you haven't entered a task description!");
        } else if (!Arrays.asList(inputParts).contains("/from") && !Arrays.asList(inputParts).contains("/to")){
            signalSays("Please tell me the event timeframe!");
//            throw new DukeException("Please tell me the event timeframe!");
        } else if (!Arrays.asList(inputParts).contains("/from")){
            signalSays("Please tell me when the event starts.");
//            throw new DukeException("Please tell me when the event starts.");
        } else if (!Arrays.asList(inputParts).contains("/to")){
            signalSays("Please tell me when the event ends.");
//            throw new DukeException("Please tell me when the event ends.");
        }

        int fromIndex = finder("/from", inputParts);
        int toIndex = finder("/to", inputParts);
        String description = String.join(" ", Arrays.copyOfRange(inputParts, 1, fromIndex));
        String start = String.join(" ", Arrays.copyOfRange(inputParts, fromIndex + 1, toIndex));
        String end = String.join(" ", Arrays.copyOfRange(inputParts, toIndex + 1, inputParts.length));
        Task task = new Event(description, start, end);
        taskList.add(task);
        taskAdded(task);
    }

    /**
     * Prints the list of inputs collected from the user.
     *
     */
    public void commandList() {
        if (taskList.size() == 0) {
            signalSays("Oops, looks like you haven't added any tasks!");
        } else {
            ArrayList<String> response = new ArrayList<>();
            response.add("Here is your tasklist!");
            for (Task i : taskList) {
                response.add(taskList.indexOf(i) + 1 + ". " + i.toString());
            }
            signalSays(listToString(response));
        }

    }


    /**
     * Marks the task as done.
     *
     * @param current The task to mark.
     */
    public void commandMark(Task current) {
        current.markDone();
        ArrayList<String> response = new ArrayList<>();
        response.add(current.checkDone()
                ? "This task is already done! Yay!\n"
                : "Nice! I've marked this task as done:\n");
        response.add("  " + current.toString());
        signalSays(listToString(response));
    }

    /**
     * Calls the commandMark method.
     *
     * @param inputParts The string array of the user input.
     */
    public void markTask(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        commandMark(taskList.get(index -1));
    }

    /**
     * Unmarks the task as not done.
     *
     * @param current The task to unmark.
     */
    public void commandUnmark(Task current) {
        current.markUnDone();
        ArrayList<String> response = new ArrayList<>();
        response.add(current.checkDone()
                ? "This task is not yet done! Yay!\n"
                : "OK, I've marked this task as undone:\n");
        response.add("  " + current.toString());
        signalSays(listToString(response));
    }

    /**
     * Calls the commandUnark method.
     *
     * @param inputParts The string array of the user input.
     */
    public void unMarkTask(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        commandUnmark(taskList.get(index - 1));
    }



    /**
     * Deletes the task from the list and shifts the subsequent tasks forward.
     *
     * @param x The index of the task to be deleted.
     */
    public void commandDelete(int x) {
        Task current = taskList.get(x);
        int initialSize = taskList.size();
        if (initialSize == 0) {
            signalSays("Looks like there's nothing here to remove. Better get on those tasks!");
//            throw new DukeException("Looks like there's nothing here to remove. Better get on those tasks!");
        }
        if (x > initialSize) {
            signalSays("I'd say shoot for the stars but in this case there are only "
                    + initialSize + (initialSize == 1 ? " item" : " items") + " in this list");
//            throw new DukeException("I'd say shoot for the stars but in this case there are only "
//                    + initialSize + (initialSize == 1 ? " item" : " items") + " in this list");
        } else {
            ArrayList<String> response = new ArrayList<>();
            taskList.remove(x);
            response.add("Noted, I've deleted this task from your list:");
            response.add("  " + current.toString());
            response.add("Now you have " + (initialSize - 1)
                    + (initialSize - 1 == 1 ? " task" : " tasks") + " in the list.");
            storeFiles.writeTasks(taskList);
            signalSays(listToString(response));
        }
    }


    public ArrayList<String> find(String key) {
        ArrayList<String> result = new ArrayList<>();
        int count = 1;
        for (Task i : taskList) {
            if (i.getDescription().contains(key)) {
                result.add(Integer.toString(count) + ". " + i.toString());
                count++;
            }
        }
        return result;
    }

    public void commandFind(String[] inputParts) {
        String toFind = String.join(" ", Arrays.copyOfRange(inputParts, 1, inputParts.length));
        ArrayList<String> response = new ArrayList<String>();
        response.add("Sure, here are the tasks containing '" + toFind + "':");
        response.addAll(find(toFind));
        signalSays(listToString(response));
    }

    /**
     * Response to user input 'blah'
     */
    public void commandBlah() {
        signalSays("All words are made up, but this one seems more nonsensical than usual. Try something else!");
//        throw new DukeException("All words are made up, but this one seems more nonsensical than usual. Try something else!");
    }

    /**
     * Response to user input 'something else'
     */
    public void commandSomethingelse() {
        signalSays("Haha, very funny. Nice try my guy!");
//        throw new DukeException("Haha, very funny. Nice try my guy!");
    }

    /**
     * Prints a user guide.
     */
    public void commandHelp() {
        signalSays("Note:\n" +
                "The round brackets indicate you can enter any text, square brackets indicate you should enter a number, without the brackets. \n" +
                "\nCREATING TASKS: \n" +
                "* todo () -- creates a To Do task, which has no deadline. \n" +
                "* deadline () \\by () -- creates a Deadline task, indicate its deadline after '\\by'. \n" +
                "* event () \\from () \\to () -- creates an Event task, indicate its start and end after '\\from' and '\\to'. \n" +
                "Note: dates are formatted as yyyy-mm-dd. time is formatted as \n" +
                "\nCOMMANDS: \n" +
                "* list -- prints a numbered list of the tasks created, in input order.\n" +
                "* mark [] -- marks the task at index [] as completed. \n" +
                "* unmark [] -- marks the task at index [] as uncompleted. \n" +
                "* delete [] -- removes the task at index [] from the list.\n" +
                "* bye -- exits the program.");
    }

    /**
     * Prints Signal's response enclosed in the dividers.
     *
     * @param response The message that is printed.
     */
    public void signalSays(String response) {
        System.out.println(DIV);
        System.out.println(response);
        System.out.println(DIV);
    }


}
