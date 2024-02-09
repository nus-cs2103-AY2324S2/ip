package secretaryw;

import java.util.Scanner;

public class Parser {
    private Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public String[] getNextCommand() {
        return scanner.nextLine().trim().split("\\s+", 2);
    }

    public void closeScanner() {
        scanner.close();
    }
}
