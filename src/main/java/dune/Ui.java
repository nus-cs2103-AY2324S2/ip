package dune;

/**
 * Deals with the user interface.
 */
public class Ui {

    public void printWelcome() {
        System.out.println("Hello! I'm dune.Dune, your task manager.");
        System.out.println("What can I do for you?");
        System.out.println("");
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
