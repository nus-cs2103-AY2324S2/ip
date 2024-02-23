package signal.util;


import signal.SignalException;
import signal.task.*;

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
        ArrayList<String> response = new ArrayList<>();
        response.add("Hello! My name is SIGNAL :3 \n");
        response.add("How can I help?");
        response.add(("Enter 'help' to see the list of commands available :D"));
        String reply = listToString(response);
        signalSays(reply);
        return reply;
    }

    /**
     * Prints the closing message of the Signal chatbot.
     */
    public String leave() {
        String reply = "Bye! Hope you come back soon :D";
        return reply;
    }

    /**
     * Prints a reponse to an empty input.
     */
    public String emptyInput() {
        String reply = "Brevity is the soul of wit, but you have to tell me something still!";
        return reply;
    }

    public String unknownInput() {
        String reply = "Sorry, I don't know what you're talking about. Enter 'help' to see what commands you can use!";
        return reply;
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
     * Save the new task added to the file and print a response to the user.
     *
     * @param task The task added.
     */
    public String taskAdded(Task task) {
        assert task != null : "Task added cannot be null!";

        storeFiles.writeTasks(taskList);

        int size = taskList.size();
        ArrayList<String> response = new ArrayList<String>();
        response.add("Got it! I've added this task to your list:");
        response.add("  " + task.toString());
        response.add("Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.");
        String reply = listToString(response);
        return reply;
    }

    /**
     * Create a new Task of type ToDo.
     *
     * @param inputParts The string array of the user input.
     */
    public String commandToDo(String[] inputParts) throws SignalException {
        if (inputParts.length < 2) {
            throw new SignalException("Looks like you haven't entered a task description!");
        } else {
            String description = String.join(" ", Arrays.copyOfRange(inputParts, 1, inputParts.length));
            Task task = new ToDo(description);
            taskList.add(task);
            return taskAdded(task);
        }

    }

    /**
     * Create a new Task of type Deadline.
     *
     * @param inputParts The string array of the user input.
     */
    public String commandDeadline(String[] inputParts) throws SignalException {
        if (inputParts.length < 2) {
            throw new SignalException("Looks like you haven't entered a task description!");
        } else if (!Arrays.asList(inputParts).contains("/by")) {
            throw new SignalException("Looks like you haven't added a deadline!");
        }
        int byIndex = finder("/by", inputParts);
        String description = String.join(" ", Arrays.copyOfRange(inputParts, 1, byIndex));
        String by = String.join(" ", Arrays.copyOfRange(inputParts, byIndex + 1, inputParts.length));
        Task task = new Deadline(description, by);
        taskList.add(task);
        return taskAdded(task);
    }

    /**
     * Create a new Task of type Event.
     *
     * @param inputParts The string array of the user input.
     */
    public String commandEvent(String[] inputParts) throws SignalException {
        if (inputParts.length < 2) {
            throw new SignalException("Looks like you haven't entered a task description!");
        } else if (!Arrays.asList(inputParts).contains("/from") && !Arrays.asList(inputParts).contains("/to")){
            throw new SignalException("Please tell me the event timeframe!");
        } else if (!Arrays.asList(inputParts).contains("/from")){
            throw new SignalException("Please tell me when the event starts.");
        } else if (!Arrays.asList(inputParts).contains("/to")){
            throw new SignalException("Please tell me when the event ends.");
        }

        int fromIndex = finder("/from", inputParts);
        int toIndex = finder("/to", inputParts);
        String description = String.join(" ", Arrays.copyOfRange(inputParts, 1, fromIndex));
        String start = String.join(" ", Arrays.copyOfRange(inputParts, fromIndex + 1, toIndex));
        String end = String.join(" ", Arrays.copyOfRange(inputParts, toIndex + 1, inputParts.length));
        Task task = new Event(description, start, end);
        taskList.add(task);
        return taskAdded(task);
    }

    /**
     * Prints the list of inputs collected from the user.
     *
     */
    public String commandList() throws SignalException {
        String reply = "";

        if (taskList.size() == 0) {
            reply = "Oops, looks like you haven't added any tasks!";
            throw new SignalException(reply);
        } else {
            ArrayList<String> response = new ArrayList<>();
            response.add("Here is your tasklist!");
            for (Task i : taskList) {
                response.add(taskList.indexOf(i) + 1 + ". " + i.toString());
            }
            reply = listToString(response);
        }
        return reply;
    }


    /**
     * Marks the task as done.
     *
     * @param current The task to mark.
     */
    public String commandMark(Task current) {
        ArrayList<String> response = new ArrayList<>();
        response.add(current.checkDone()
                ? "This task is already done! Yay!"
                : "Nice! I've marked this task as done:");
        current.markDone();
        response.add("  " + current.toString());
        String reply = listToString(response);
        return reply;
    }

    /**
     * Calls the commandMark method.
     *
     * @param inputParts The string array of the user input.
     */
    public String markTask(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        return commandMark(taskList.get(index -1));
    }

    /**
     * Unmarks the task as not done.
     *
     * @param current The task to unmark.
     */
    public String commandUnmark(Task current) {
        ArrayList<String> response = new ArrayList<>();
        response.add(!current.checkDone()
                ? "This task is not yet done! Best get on it :D"
                : "OK, I've marked this task as undone:");
        current.markUnDone();
        response.add("  " + current.toString());
        String reply = listToString(response);
        signalSays(reply);
        return reply;
    }

    public String commandNotDoneList() throws SignalException {
        String reply = "";

        if (taskList.size() == 0) {
            reply = "Oops, looks like you haven't added any tasks!";
            throw new SignalException(reply);
        }
        ArrayList<String> response = new ArrayList<>();
        response.add("Here are your tasks that are not yet completed!");
        for (Task i : taskList) {
            if (!i.checkDone()) {
                response.add(taskList.indexOf(i) + 1 + ". " + i.toString());
            }
        }

        if (response.size() == 1) {
            reply = "Wow! Looks like you dont have any uncompleted tasks!";
            throw new SignalException(reply);
        }

        reply = listToString(response);
        signalSays(reply);
        return reply;
    }


    /**
     * Calls the commandUnark method.
     *
     * @param inputParts The string array of the user input.
     */
    public String unMarkTask(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        return commandUnmark(taskList.get(index - 1));
    }

    public String commandPriorityList() throws SignalException {
        String reply = "";

        if (taskList.size() == 0) {
            reply = "Oops, looks like you haven't added any tasks!";
            throw new SignalException(reply);
        }
        ArrayList<String> response = new ArrayList<>();
        response.add("Here are your tasks marked as priority!");
        for (Task i : taskList) {
            if (i.checkPriority()) {
                response.add(taskList.indexOf(i) + 1 + ". " + i.toString());
            }
        }

        if (response.size() == 1) {
            reply = "Oops, looks like you haven't marked any tasks as priority!";
            throw new SignalException(reply);
        }

        reply = listToString(response);
        signalSays(reply);
        return reply;
    }

    public String commandPriority(Task current) {
        current.setPriority();
        ArrayList<String> response = new ArrayList<>();
        response.add("OK, I've marked this task as high priority!");
        response.add("  " + current.toString());
        String reply = listToString(response);

        signalSays(reply);
        return reply;
    }

    public String markPriority(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        return commandPriority(taskList.get(index - 1));
    }

    public String commandNotPriority(Task current) {
        current.setNotPriority();
        ArrayList<String> response = new ArrayList<>();
        response.add("OK, I've marked this task as not priority!");
        response.add("  " + current.toString());
        String reply = listToString(response);

        signalSays(reply);
        return reply;
    }


    public String markNotPriority(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        return commandNotPriority(taskList.get(index - 1));
    }



    /**
     * Deletes the task from the list and shifts the subsequent tasks forward.
     *
     * @param x The index of the task to be deleted.
     */
    public String commandDelete(int x) throws SignalException {
        Task current = taskList.get(x);
        int initialSize = taskList.size();
        ArrayList<String> response = new ArrayList<>();

        if (initialSize == 0) {
            response.add("Looks like there's nothing here to remove. Better get on those tasks!");
            throw new SignalException(listToString(response));
        }
        if (x > initialSize) {
            response.add("I'd say shoot for the stars but in this case there are only "
                    + initialSize + (initialSize == 1 ? " item" : " items") + " in this list");
            throw new SignalException(listToString(response));

        } else {
            taskList.remove(x);
            response.add("Noted, I've deleted this task from your list:");
            response.add("  " + current.toString());
            response.add("Now you have " + (initialSize - 1)
                    + (initialSize - 1 == 1 ? " task" : " tasks") + " in the list.");
            storeFiles.writeTasks(taskList);

        }

        String reply = listToString(response);
        return reply;
    }


    public ArrayList<String> find(String key) {
        ArrayList<String> result = new ArrayList<>();
        for (Task i : taskList) {
            if (i.getDescription().contains(key)) {
                result.add(taskList.indexOf(i) + 1 + ". " + i.toString());
            }
        }
        return result;
    }

    public String commandFind(String[] inputParts) throws SignalException {
        String toFind = String.join(" ", Arrays.copyOfRange(inputParts, 1, inputParts.length));
        if (toFind.length() == 0) {
            throw new SignalException("I don't know what you're looking for!");
        }
        ArrayList<String> response = new ArrayList<String>();
        ArrayList<String> found = find(toFind);
        if (found.size() == 0) {
            response.add("Looks like there's noting here. Try another keyword!");
        } else {
            response.add("Sure, here are the tasks containing '" + toFind + "':");
            response.addAll(find(toFind));
        }
        String reply = listToString(response);
        return reply;
    }

    public String commandHi() {
        String reply = "Hi! Enter 'help' to view the available commands!";
        return reply;
    }

    /**
     * Response to user input 'blah'
     */
    public String commandBlah() {
        String reply = "All words are made up, but this one seems more nonsensical than usual. Try something else!";
        return reply;
    }

    /**
     * Response to user input 'something else'
     */
    public String commandSomethingelse() {
        String reply = "Haha, very funny. Nice try my guy!";
        return reply;
    }

    /**
     * Prints a user guide.
     */
    public String commandHelp() {
        ArrayList<String> response = new ArrayList<String>();
        response.add("Note:");
        response.add("The round brackets indicate you can enter any text, square brackets indicate you should enter a number, without the brackets.");
        response.add("\nCREATING TASKS:");
        response.add("* todo () -- creates a To Do task, which has no deadline. ");
        response.add("* deadline () \\by () -- creates a Deadline task, indicate its deadline after '\\by'.");
        response.add("* event () \\from () \\to () -- creates an Event task, indicate its start and end after '\\from' and '\\to'.");
        response.add("Note: dates are formatted as yyyy-mm-dd. time is formatted as ");
        response.add("\nCOMMANDS: ");
        response.add("* list -- prints a numbered list of the tasks created, in input order.");
        response.add("* mark [] -- marks the task at index [] as completed. ");
        response.add("* unmark [] -- marks the task at index [] as uncompleted. ");
        response.add("* notdonelist -- show the list of tasks that are uncompleted.");
        response.add("* prioritise [] -- sets the task at index [] as priority.");
        response.add("* unprioritise [] -- sets the task at index [] as non priority");
        response.add("* prioritylist -- show the list of prioritised tasks");
        response.add("* delete [] -- removes the task at index [] from the list.");
        response.add("* bye -- exits the program.");
        String reply = listToString(response);

        return reply;
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
