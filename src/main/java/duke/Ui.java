package duke;
import java.util.Scanner;
/**
 * Class encapsulating the displaying and receiving of user input and output
 * @author Cedric
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);
    /**
     * Receive method simply calls scanner.nextLine() for user input
     * @return returns the user input as a String
     */
    public String receive() {
        return scanner.nextLine();
    }
    /**
     * Displays user output as a println
     * @param n Object that will be displayed in its toString() form
     */
    public void display(Object n) {
        System.out.println(n.toString());
    }
}
