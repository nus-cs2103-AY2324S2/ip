import java.util.Scanner;
/**
 * Parser class parses and executes user input.
 */
public class Parser {
    private final Scanner sc;

    public Parser() {
        this.sc = new Scanner(System.in);
    }
    public String readInput() {
        return sc.nextLine();
    }
    public String parseCommand() {
        String input = readInput();
        String[] inputSplit = input.split(" ");
        String command = inputSplit[0];
        return command;
    }
    public void closeScanner() {
        sc.close();
    }
}
