package Charlie.ui;

import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);
    public void showLoadingError(String message) {
        System.err.println("Error: " + message);
    }

    public void showWelcome(){
        System.out.println("Hello, I'm charlie.Charlie");
        System.out.println("What can I do for you?");
    }

    public void showLine(){
        System.out.println("___________________________________________");
    }

    public void showError(String message){
        System.err.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printOutput(String output){
        System.out.println(output);
    }
}
