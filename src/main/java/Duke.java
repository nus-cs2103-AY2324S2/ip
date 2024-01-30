import Exceptions.DukeException;
import Exceptions.InvalidTaskNameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String[] COMMANDS = {"help", "list", "todo", "deadline", "event",
            "mark", "unmark", "delete", "bye"};
    protected File list = new File("data/list.txt");
    protected static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = "                             -------,\n"
                + "                            |   --  |\n"
                + "                            |  |  | |\n"
                + " ---- -   -  ---  ---  ---  |   --  |\n"
                + "|     |   | |   |  |  |   | |  ,----'\n"
                + "|     |---| |---   |   `-_  |  |     \n"
                + "|     |   | | \\    |  |   | |  |     \n"
                + " ---- -   - -  -  ---  ---   --      \n";
        String horizontalLine = "_____________________________________________________";
        String introduction = "\nOink Oink!\nI'm Chris P Bacon but you can call me ChrisP! Oink!\n"
                + "What can I do for you today? :D\n"
                + "~Type 'help' for more command info~\n";
        String help = "Oink! Here are the Command Words:\n'list' - displays the list of task\n"
                + "'todo ...' - to add new task\n'deadline ... /by ...' - to add task with deadline\n"
                + "'event ... /from ... /to ...' - to add an event\n"
                + "'mark <task no.>' - to mark a task done\n'unmark <task no.>' - to unmark a task\n"
                + "'delete <task no.>' - to delete a task\n'bye' - to exit the chatbot";

        // Entering the chatbot.
        SaveAndLoad saveAndLoad = new SaveAndLoad();
        try {
            saveAndLoad.loadList();
        } catch (FileNotFoundException e) {
            System.out.println("Oink! File not found :(");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println(logo + horizontalLine
                + introduction + horizontalLine);

        // Get user's first input.
        String userInput = "";
        userInput = scan.nextLine();

        while (!userInput.equals(COMMANDS[8])) {
            try {
                System.out.println(horizontalLine);

                int len = userInput.length();
                boolean isTodo = userInput.startsWith(COMMANDS[2]);
                boolean isDeadline = userInput.startsWith(COMMANDS[3]);
                boolean isEvent = userInput.startsWith(COMMANDS[4]);

                if (userInput.startsWith(COMMANDS[0])) {
                    // If input = "help", print the command words.
                    System.out.println(help);

                } else if (userInput.startsWith(COMMANDS[1])) {
                    // If input = "list", print out the list.
                    if (taskList.isEmpty()) {
                        System.out.println("Oink! There are no tasks! Yeehaww");

                    } else {
                        System.out.println("Oink! Here are the tasks:");
                        for (int i = 1; i <= taskList.size(); i++) {
                            System.out.println(i + ". " + taskList.get(i - 1));
                        }
                    }
                } else if (userInput.startsWith(COMMANDS[5])) {
                    if (userInput.length() < 6) {
                        throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                                + " >> mark <task no.>");
                    }
                    int num = userInput.charAt(5) - '0';
                    taskList.get(num - 1).markDone();

                } else if (userInput.startsWith(COMMANDS[6])) {
                    if (userInput.length() < 8) {
                        throw new InvalidTaskNameException("Ooink oink! Please follow the format >.<\n"
                                + " >> unmark <task no.>");
                    }
                    int num = userInput.charAt(7) - '0';
                    taskList.get(num - 1).markUndone();

                } else if (userInput.startsWith(COMMANDS[7])) {
                    int num = userInput.charAt(7) - '0';
                    taskList.get(num - 1).printDeleteTask(taskList.size());
                    taskList.remove(num - 1);

                } else if (isTodo || isDeadline || isEvent) {
                    if (isTodo) {
                        // Adds a new todo task to the list.
                        if (len < 6) {
                            // If user did not input task name.
                            throw new InvalidTaskNameException("Ooink oink! What's the name of your task?\n"
                                    + " >> todo ...");
                        }
                        Todo t = new Todo(userInput.substring(5));
                        taskList.add(t);
                        t.printAddTask(taskList.size());
                    } else if (isDeadline) {
                        // Add a new deadline task to the list.
                        int idx = userInput.lastIndexOf("/by");
                        boolean isWrongInput = len < 10 || idx < 0 || len < idx + 4;
                        if (isWrongInput) {
                            // If user did not input task description.
                            throw new InvalidTaskNameException("Ooink oink! Please describe your deadline >.<\n"
                                    + " >> deadline ... /by ...");
                        }
                        String name = userInput.substring(9, idx - 1);
                        String date = userInput.substring(idx + 4);
                        Deadline d = new Deadline(name, date);
                        taskList.add(d);
                        d.printAddTask(taskList.size());
                    } else {
                        // Adds a new event to the list.
                        int fromIdx = userInput.lastIndexOf("/from");
                        int toIdx = userInput.lastIndexOf("/to");
                        boolean isWrongInput = len < 7 || fromIdx < 0 || toIdx < 0
                                || len < fromIdx + 6 || len < toIdx + 4;
                        if (isWrongInput) {
                            // If user did not input task description.
                            throw new InvalidTaskNameException("Ooink oink! Please describe your event >.<\n"
                                    + " >> event ... /from ... /to ...");
                        }
                        String name = userInput.substring(6, userInput.lastIndexOf("/from") - 1);
                        String from = userInput.substring(userInput.lastIndexOf("/from") + 6,
                                userInput.lastIndexOf("/to") - 1);
                        String to = userInput.substring(userInput.lastIndexOf("/to") + 4);
                        Event e = new Event(name, from, to);
                        taskList.add(e);
                        e.printAddTask(taskList.size());
                    }
                } else {
                    // if user entered input that cannot be recognised.
                    throw new DukeException("Ooink oink! I'm sorry, I don't understand.\n"
                            + "Type 'help' for command info!");
                }
            } catch (DukeException | InvalidTaskNameException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(horizontalLine);
            userInput = scan.nextLine(); // Scan for next input.
        }

        // if user entered "bye", close scanner, save list and exit chatbot.
        scan.close();
        try {
            saveAndLoad.saveList();
        } catch (IOException e) {
            System.out.println("Oink! Something went wrong... I can't save it!");
        }
        System.out.println(horizontalLine + "\nOink! Okie byee... See you soon! :)\n"
                + horizontalLine);
    }
}
