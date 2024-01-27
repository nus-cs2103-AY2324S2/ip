package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showCat() {
        System.out.println(" |\\ /| ");
        System.out.println("=(O O)=");
        System.out.println(" /   \\ ");
    }

    public void showWelcome() {
        showLine();
        showCat();
        System.out.println("The cat that lives in your walls pokes its head out.");
        System.out.println("Its waiting for you to ask something.");
        showLine();
    }

    public void showBye() {
        System.out.println("The cat recedes into the wall with a bored look on its face");
    }

    public void showLine() {
        for (int i = 0; i < 72; i++) {
            System.out.print('─');
        }
        System.out.print('\n');
    }

    public void showError(Exception e) {
        System.out.println("The cat tilts its head and hands you an error report:\n" + e.getMessage());
    }

    public void showNote(String str) {
        System.out.print("The cat hands a note to you, it reads:\n" + str);
    }

    public void showCommandNotFound(String command) {
        System.out.println("The cat tilts its head. It doesn't know what command \"" + command + "\" is.");
    }

    public void showAdd(Task task) {
        System.out.println("The cat scratches a mark on the wall and then hands you a receipt:\nAdded task "
                + task.describe());
    }

    public String readCommand() {
        if (!scanner.hasNextLine()) {
            return "bye";
        }
        return scanner.nextLine();
    }
}
