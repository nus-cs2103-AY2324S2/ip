package jayne;

import java.util.Scanner;

public class Ui {
    // Read user input
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    // Display a welcome message
    public void showWelcome() {
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Jayne");
        System.out.println("What can I do for you?");
        System.out.println("___________________________________");
    }

    public String question() {
        return "What are you typing. please include either bye, list, mark, umark, todo, deadline or event in your inputs please";
    }

}
