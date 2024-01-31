package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    static String name = "Lunaris";
    static String indentedLine =
            "  _________________________________________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void setIndentedLine() {
        System.out.println(indentedLine);
    }

    public void welcomeMessage() {
        setIndentedLine();
        System.out.println("  " + "Hey! I'm " + name + "\n" +
                "  " + "Is there anything I can do for you?");
        setIndentedLine();
    }

    public void goodByeMessage() {
        setIndentedLine();
        System.out.println("  " +
                "Leaving so soon? Alright, have a great day ahead!");
        setIndentedLine();
    }

    public void loadErrorMessage() {
        setIndentedLine();
        System.out.println("  " +
                "Error loading file... Creating new empty file");
        setIndentedLine();
    }

    public void unmarkMessage(Task task) {
        setIndentedLine();
        System.out.println("  " + "Ok, I've marked this task " +
                "as not done yet:" + "\n" +
                "  " + task.toString());
        setIndentedLine();
    }

    public void markMessage(Task task) {
        setIndentedLine();
        System.out.println("  " + "Ok, I've marked this task " +
                "as done:" + "\n" +
                "  " + task.toString());
        setIndentedLine();
    }

    public void deleteMessage(Task task) {
        setIndentedLine();
        System.out.println("  " + "Noted. I've removed this task:" +
                "\n" +
                "  " + task.toString());
        setIndentedLine();
    }

    public void listSizeMessage(TaskList taskList) {
        System.out.println("  " +
                "Now you have " + taskList.getSize() + " tasks in the list.");
        setIndentedLine();
    }

    public String readCommandLine() {
        return scanner.nextLine();
    }

    public String readCommand() {
        return scanner.next();
    }

    public int readInt() {
        return scanner.nextInt();
    }
}
