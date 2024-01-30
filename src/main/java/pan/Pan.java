package pan;

import java.util.Scanner;

public class Pan {
    /**
     * Main Entrypoint into the Pan application.
     */
    public static void main(String[] args) {
        Parser parser = new Parser(new Ui(), new Scanner(System.in), new TaskList(new Storage()));
        parser.parseInput();
    }

}
