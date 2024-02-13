package dav;

import java.util.Scanner;

class Ui {

    public void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up! I'm Dav");
        System.out.println(" How may I help you?");
        System.out.println("____________________________________________________________");
    }

    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public void exit() {
        System.out.println(" Goodbye. ");
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}

