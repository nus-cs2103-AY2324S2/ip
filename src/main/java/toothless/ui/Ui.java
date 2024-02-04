package toothless.ui;

import toothless.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    
    public void printMessage(String message) {
        System.out.println("\t" + message);
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }
    
    public void printWelcome() {
        printLine();
        String introMessage = " /\\_/\\\n" +
                "\t( o.o )\n" +
                "\t > ^ <\n" +
                "\tNya-ice to meet you! I'm Toothless :D\n" +
                "\tWhat can I do for you?";
        printMessage(introMessage);
        printLine();
    }

    /**
     * Prints list for finding keyword.
     * 
     * @param tasks The keyword tasklist to be printed.
     */
    public void printKeywordList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.print("\tOops! Looks like there are no tasks matching the keyword!");
        } else {
            String listString = "\tHere are the meow-tching tasks in your list:\n";
            for (int i = 1; i <= tasks.size(); i++) {
                listString += "\t";
                listString += i + ". " + tasks.get(i - 1);
                if (i < tasks.size()) {
                    listString += "\n";
                }
            }
            System.out.println(listString);
        }
    }
}
