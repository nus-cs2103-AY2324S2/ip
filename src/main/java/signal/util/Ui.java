package signal.util;


//import signal.DukeException;
import signal.task.*;
import signal.util.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    public Ui(ArrayList<Task> taskList) {
        this.taskList = taskList;

    }

    public String scan() {
        return this.scanner.nextLine();
    }

    public void intro() {
        System.out.println("Hello! My name is\n" + LOGO);
        System.out.println("How can I help?");
        System.out.println("Enter 'help' to see the list of commands available :D");
        System.out.println(DIV);
    }

    public void leave() {
        signalSays("Bye! Hope you come back soon :D");
    }

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
     * Adds input to the list.
     *
//     * @param input Input collected from the user.
     */
//    public void taskAdded(String input) {
//        Task t = new Task(input, false);
//        this.taskList.add(t);
//        index += 1;
//        signalSays("Added: " + input);
//    }
//
//    public void commandAddTask(String type, String input) throws DukeException {
//        if (input == "") {
//            throw new DukeException("Looks like you haven't entered a task description!");
//        }
//        if (type.equals("todo")) {
//            taskList.add(new ToDo(input, false));
//        } else {
//            String command[] = input.split(" /");
//            if (type.equals("deadline")) {
//                if (command.length < 2) {
//                    throw new DukeException("Looks like you haven't added a deadline!");
//                }
//                String deadline = command[1] != null && command[1].length() > 3 ? command[1].substring(3) : command[1];
//                taskList.add(new Deadline(command[0], deadline, false));
//            } else if (type.equals("event")){
//                if (command.length < 3) {
//                    throw new DukeException("Looks like you haven't added a start or end time!");
//                }
//                String start = command[1] != null && command[1].length() > 5 ? command[1].substring(5): command[1];
//                String end = command[2] != null && command[2].length() > 3 ? command[2].substring(3) : command[2];
//                taskList.add(new Event(command[0], start, end, false));
//            } else {
//                taskList.add(new Task(input, false));
//            }
//
//        }
//        taskAdded(taskList.get(index));
//    }



    public int finder(String checker, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(checker)) {
                return i;
            }
        }
        return -1;
    }

    public String listToString(ArrayList<String> list) {
        String result = "";
        for (String s : list) {
            result += s + "\n";
        }
        return result;
    }

    public void taskAdded(Task task) {
        int size = taskList.size();
        signalSays("Got it! I've added this task to your list: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.");
        storeFiles.writeTasks(taskList);
    }

    public void commandToDo(String[] inputParts) {
        if (inputParts.length < 2) {
            signalSays("Looks like you haven't entered a task description!");
//            throw new DukeException("Looks like you haven't entered a task description!");
        }
        String description = String.join(" ", Arrays.copyOfRange(inputParts, 1, inputParts.length));
        Task task = new ToDo(description);
        taskList.add(task);
        taskAdded(task);
    }

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
                response.add(i.toString());
            }
            signalSays(listToString(response));
        }
//        System.out.println(DIV);
//        System.out.println("Here is your tasklist!");
//        for (int i = 0; i < index; i++) {
//            System.out.println((i + 1) + ". " + taskList.get(i).toString());
//        }
//        System.out.println(DIV);
    }



//    public void commandListDate(String date) {
//        LocalDate d = LocalDate.parse(date);
//        System.out.println(DIV);
//        System.out.println("Here's what's happening on " + formatDate(d) + ":");
//        for (int i = 0; i < index; i++){
//            Task t = taskList.get(i);
//            if (t.getDue() == d) {
//                System.out.println(t.toString());
//            }
//        }
//        System.out.println(DIV);
//    }

    public void commandMark(Task current) {
        ArrayList<String> response = new ArrayList<>();
        response.add(current.checkDone()
                ? "This task is already done! Yay!\n"
                : "Nice! I've marked this task as done:\n");
        response.add("  " + current.toString());
        current.markDone();
        signalSays(listToString(response));

//        Task current = taskList.get(x);
//        String response = current.checkDone()
//                ? "Task " + (x + 1) + " is already done! Yay!\n"
//                : "Nice! I've marked this task as done:\n";
//        current.markDone();
//        return response + "  " + current.toString();
    }

    public void markTask(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        commandMark(taskList.get(index));
    }

    public void commandUnmark(Task current) {
        ArrayList<String> response = new ArrayList<>();
        response.add(current.checkDone()
                ? "This task is not yet done! Yay!\n"
                : "OK, I've marked this task as undone:\n");
        response.add("  " + current.toString());
        current.markUnDone();
        signalSays(listToString(response));

//        Task current = taskList.get(x);
//        String response = current.checkDone()
//                ? "Task " + (x + 1) + " is not done yet!\n"
//                : "OK, I've marked this task as undone:\n";
//        current.markUnDone();
//        return response + "  " + current.toString();
    }

    public void unMarkTask(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        commandUnmark(taskList.get(index));
    }



    /**
     * Deletes the task from the list and shifts the subsequent tasks forward.
     *
     * @param x The index of the task to be deleted.
//     * @throws DukeException
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
        }
        ArrayList<String> response = new ArrayList<>();
        taskList.remove(x);
        response.add("Noted, I've deleted this task from your list:");
        response.add("  " + current.toString());
        response.add("Now you have " + (initialSize - 1)
                + (initialSize - 1 == 1 ? " task" : " tasks") + " in the list.");
        storeFiles.writeTasks(taskList);
        signalSays(listToString(response));

////        if (index == 0) {
////            throw new DukeException("Looks like there's nothing here to remove. Better get on those tasks!");
////        }
//        if (x >= 0 && x <= index) {
//            taskList.remove(x);
//            index -= 1;
//            signalSays("Noted, I've deleted this task from your list: \n"
//                    + "  " + current.toString() + "\n"
//                    + "Now you have " + (index) + (index == 1 ? " task" : " tasks") + " in the list.");
//            writeTasks(taskList);
//        } else {
//            throw new DukeException("I'd say shoot for the stars but in this case there are only "
//                    + (index - 1) + ((index - 1) == 1 ? " item" : " items") + " in this list");
//        }
    }

    /**
     * Handles negative and out-of-bounds inputs for deleting tasks.
     *
     * @param x
//     * @throws DukeException
     */
    public void commandDeleteInvalid(int x) {
        if (x == 0) {
            signalSays("Looks like there's nothing here to remove. Better get on those tasks!");
//            throw new DukeException("Looks like there's nothing here to remove. Better get on those tasks!");
        }
        if (x < 0) {
            signalSays("Negative numbers might exist in maths but not in this list!");
//            throw new DukeException("Negative numbers might exist in maths but not in this list!");
        }
    }

    public void commandBlah() {
        signalSays("All words are made up, but this one seems more nonsensical than usual. Try something else!");
//        throw new DukeException("All words are made up, but this one seems more nonsensical than usual. Try something else!");
    }

    public void commandSomethingelse() {
        signalSays("Haha, very funny. Nice try my guy!");
//        throw new DukeException("Haha, very funny. Nice try my guy!");
    }

    public void commandHelp() {
        signalSays("Note: Entering any text besides the following creates a new generic task. \n" +
                "The round brackets indicate you can enter any text, square brackets indicate you should enter a number, without the brackets. \n" +
                "\nCREATING TASKS: \n" +
                "* todo () -- creates a To Do task, which has no deadline. \n" +
                "* deadline () \\by () -- creates a Deadline task, indicate its deadline after '\\by'. \n" +
                "* event () \\from () \\to () -- creates an Event task, indicate its start and end after '\\from' and '\\to'. \n" +
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
