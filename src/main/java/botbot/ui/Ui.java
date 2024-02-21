package botbot.ui;

import botbot.Storage;
import botbot.exception.BotBotException;
import botbot.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Ui {

    public String getResponseAndExecute(String input, TaskList list, Storage storage) throws BotBotException, IOException {
        if (input.startsWith("bye")) {
            storage.save(list);
            return "Goodbye! See you soon!\nYour list has been saved! You may now exit the program";
        } else if (input.startsWith("mark")) {
            return "Good job on completing the task:\n" + list.mark(Integer.parseInt(input.split(" ", 2)[1]));
        } else if (input.startsWith("unmark")) {
            return "I have unmarked a task:\n" + list.unmark(Integer.parseInt(input.split(" ", 2)[1]));
        } else if (input.startsWith("list")) {
            return "These are the tasks in your list:\n" + list.printList();
        } else if (input.startsWith("delete")) {
            return list.deleteTask(Integer.parseInt(input.split(" ", 2)[1]));
        } else if (input.startsWith("find")){
            return "These are the matching tasks in your list:\n" + list.printFind(input.split(" ", 2)[1]);
        } else {
            return list.addTask(input);
        }
    }

    // Mostly obsolete code from when it was run on commandline
    /**
     * User interface loop to get user input
     * @param list
     * @param storage
     * @throws BotBotException
     */
    public void run(TaskList list, Storage storage) throws BotBotException {
        greet();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        while (scanner.hasNext()) {
            String nextTask = scanner.next();
            divider();
            // Logic sequence
            if (nextTask.startsWith("bye")) {
                exit();
                break;
            } else if (nextTask.startsWith("mark")) {
                System.out.println("Good job on completing the task:");
                System.out.println(Integer.parseInt(nextTask.split(" ", 2)[1]));
                list.mark(Integer.parseInt(nextTask.split(" ", 2)[1]));
            } else if (nextTask.startsWith("unmark")) {
                System.out.println("I have unmarked a task:");
                list.unmark(Integer.parseInt(nextTask.split(" ", 2)[1]));
            } else if (nextTask.startsWith("list")) {
                System.out.println("These are the tasks in your list:");
                list.printList();
            } else if (nextTask.startsWith("delete")) {
                list.deleteTask(Integer.parseInt(nextTask.split(" ", 2)[1]));
            } else if (nextTask.startsWith("find")){
                System.out.println("These are the matching tasks in your list:");
                list.printFind(nextTask.split(" ", 2)[1]);
            } else {
                list.addTask(nextTask);
            }
            divider();
        }
        scanner.close();
    }
    // Print functionalities
    private void print(String string) {
        System.out.println(string);
    }
    private void divider() {
        print("########################################");
    }

    /**
     * Prints the startup message
     */
    public void greet() {
        divider();
        print("I am BotBot!\nWhat can I do for you?");
        divider();
    }

    /**
     * prints the exit message
     */
    public void exit() {
        print("Goodbye! See you soon!");
        divider();
    }
}
