package charlie.ui;

import java.util.Scanner;
public class Ui {

    private Scanner scanner = new Scanner(System.in);
    public void showLoadingError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * introduces Charlie to the user
     */
    public void showWelcome(){
        System.out.println("Hello, I'm Charlie.");
        System.out.println("What can I do for you?");
    }

    /**
     * generates a line for separation of commands
     */
    public void showLine(){
        System.out.println("___________________________________________");
    }

    /**
     * @param message shows an error message in the case of an error
     */
    public void showError(String message){
        System.err.println(message);
    }

    /**
     * reads user commands, and returns as strings
     * @return user commands
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * prints whatever string output it receives
     * @param output string output
     */
    public void printOutput(String output){
        System.out.println(output);
    }
}
