package duke;

import java.util.Scanner;

public class Ui {

    public Ui() {}

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public void printGreeting() {
        System.out.println("Greetings! I am Aegis.\n"
                + "How can I assist you?\n");
    }

    public void printFarewell() {
        System.out.println("Goodbye! Have a pleasant day!\n");
    }

    public void printDivider() {
        String divider = "-----------------------------------------------\n";
        System.out.println(divider);
    }

    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printFileNotFoundError() {
        System.out.println("An error has occurred locating the file.");
    }

    public void printIoException() {
        System.out.println("An error has occurred editing the file.");
    }

    public void printMarkTaskSuccess() {
        System.out.println("Well done, task marked as completed.\n");
    }

    public void printUnmarkTaskSuccess() {
        System.out.println("Understood, task marked as uncompleted.\n");
    }

    public void printCreateTaskSuccess() {
        System.out.println("Confirmed. New task added:\n");
    }

    public void printDeleteTaskSuccess() {
        System.out.println("Acknowledged. The following task has been removed:\n");
    }
}
